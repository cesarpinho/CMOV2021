///////////////////
// CONFIGURATION //
///////////////////

const express = require('express')
const app = express()
const uuid = require('uuid')
const crypto = require('crypto')
const bcrypt = require('bcrypt')
const bodyParser = require('body-parser')
const validator = require('./validator.js')
const sequelize = require('sequelize')
const nano = require('nanoid')

const PORT = process.env.PORT || 3000
const SALT_ROUNDS = 10
const FAULT_TOLERANCE = 2750

const db = require('./models/index.js')
const receipt = require('./models/receipt.js')
const alpha = nano.customAlphabet('1234567890ABCDEFGHIJKLMNOPQRSTUVXZ', 9)

app.use(bodyParser.json())
app.use(bodyParser.urlencoded({ extended: true }))

///////////////
// ENDPOINTS //
///////////////

app.get('/', (req, res) => {
  res.send('ACME REST SERVICE')
})

/**
 * @description 
 * Endpoint responsible for retrieving the products
 * present on the database, making sure the local one
 * is up-to-date.
 * 
 * @returns
 * Returns all the products present in the database if the local
 * on does not have any product or has older versions that need
 * to be updated. Otherwise returns a BAD_REQUEST which means that
 * the local database is up-to-date.
 */
app.get('/products', async (req, res) => {
  // Retrieves the version of oldest product present the app local database
  const version = req.query.version == undefined ? null: req.query.version
  
  // Retrieve the most recent product instance from the database
  const newestProduct = await db.Product.findOne({
    order: [
      ['updatedAt', 'DESC']
    ]
  })

  // No need for update
  if(newestProduct == null)
    return res.status(400).send({description: "Products table up-to-date!"})

  // Convert dates to date objects for comparison
  let versionDate = new Date(version)
  let newestProductDate = new Date(newestProduct.dataValues.updatedAt)

  // Check if there is the need to update the app local database
  if(newestProductDate.getTime() - versionDate.getTime() > 0) {
    db.Product.findAll({
      attributes: ['type', 'icon', 'name', 'price']
    })
    .then((products) => {
      res.send(products)
    })
  } 
  else 
    return res.status(400).send({description: "Products table up-to-date!"})
})

/**
 * @description 
 * Endpoint responsible for authenticate a
 * customer.
 * 
 * @returns
 * Returns the needed user information for 
 * local storage on success. Otherwise, returns
 * an empty response with the 400 status code.
 */
app.post('/login', async (req, res) => {
  // Validate request body
  if(req.body.nickname == null || req.body.password == null)
    return res.status(400).send({description: "Both nickname and password must have a value."})
  
  db.Customer.findOne({ where: { nickname: req.body.nickname }})
      .then((customer) => {
        if(customer == null)
          return res.status(400).send({description: "Invalid credentials!"})
        else {
          if(!bcrypt.compareSync(req.body.password, customer.dataValues.password))
            return res.status(400).send({description: "Invalid credentials!"})
          else {
            if(req.body.certificate != null)
              db.Certificate.create(  {
                certificate: req.body.certificate,
                id_customer: customer.dataValues.id
              })          
            
            res.send({
              "uuid": customer.dataValues.uuid,
              "name": customer.dataValues.name,
              "card": customer.dataValues.card,
              "nif": customer.dataValues.nif,
              "nickname": customer.dataValues.nickname
            })
          }
        }
      })

})

/**
 * @description 
 * Endpoint responsible for registering new
 * customers into the server.
 * 
 * @returns
 * Returns the needed user information for 
 * local storage on success. Otherwise, returns
 * an empty response with the 400 status code.
 */
app.post('/register', (req, res) => {
  // Validate request body
  const reqBodyValidation = validator.endpointRegisterBody(req.body)

  if(!reqBodyValidation.status)
    return res.status(400).send({description: reqBodyValidation.description})

  // Generate uuid and hashed password
  const customerID = uuid.v4()
  const hash = bcrypt.hashSync(req.body.password, SALT_ROUNDS)
  
  // Store information on server database
  db.Customer.findOrCreate( { where: {
                                nickname: req.body.nickname
                              }, 
                              defaults: {
                                uuid: customerID, 
                                name: req.body.name,
                                card: req.body.card, 
                                nif: req.body.nif, 
                                nickname: req.body.nickname, 
                                password: hash
                              }
                            }
                          )
  .then(([customer, created]) => {
    if(!created)
      res.status(400).send({description: "Nickname chosen is already taken."})
    else {
      db.Certificate.create(  {
                                certificate: req.body.certificate,
                                id_customer: customer.dataValues.id
                              }
                            )
      .then((_) => {
        res.send({
          "uuid": customer.dataValues.uuid,
          "name": customer.dataValues.name,
          "card": customer.dataValues.card,
          "nif": customer.dataValues.nif,
          "nickname": customer.dataValues.nickname
        })
      })
    }      
  })
})

/**
 * @description 
 * Endpoint responsible for retrieving the 
 * receipts for a certain customer.
 * 
 * @returns
 * Returns the customer receipts for
 * local storage on success. Otherwise returns a 
 * BAD_REQUEST.
 */
app.post('/receipts', async (req, res) => {
  // Validate request body
  if(req.body.uuid == null || req.body.signature == null || req.body.timestamp == null)
    return res.status(400).send({description: "Both the <uuid>, <signature> and <timestamp> fields cannot be empty"})
  
  // Date variables definition
  let timestamp = new Date(req.body.timestamp)
  let currentDate = new Date()
  let diffDate = Math.abs(currentDate.getTime() - timestamp.getTime())
  
  // Check for timestamp fault tolerance
  if(diffDate < 0)
    return res.status(400).send({description: "Invalid timestamp."})
  else if(diffDate >= FAULT_TOLERANCE)
    return res.status(400).send({description: "Timestamp not within the defined fault tolerance."})
  
  // Retrieve and validate customer 
  const customer = await db.Customer.findOne({ where: { uuid: req.body.uuid }})

  if(customer == null)
    return res.status(400).send({description: "Customer with uuid <" + req.body.uuid + "> does not exist."})
  
  let signatureValidity = await validator.validSignature(customer, uuid.stringify(uuid.parse(customer.uuid)), req.body.signature)
  
  if(!signatureValidity)
    return res.status(400).send({description: "Signature invalid."})

  // Retrieve and send receipts
  let receipts = await db.Receipt.findAll({ 
    where: {id_customer: customer.id },
    attributes: ['id', 'code', 'date', 'total']
  })

  for(let receipt of receipts) {
    receipt.dataValues["products"] = await db.sequelize.query('SELECT type, name, quantity, icon, price FROM "Quantities" INNER JOIN "Products" ON id_product = "Products".id WHERE id_receipt = :id_receipt', 
    { 
      replacements:  {id_receipt: receipt.id},
      type: sequelize.QueryTypes.SELECT
    })

    delete receipt.dataValues.id
  }
  res.send(receipts)
})

/**
 * @description 
 * Endpoint responsible for handling the 
 * customer orders.
 * 
 * @returns
 * Returns the validation result of the purchase.
 */
app.post('/purchase', async (req, res) => {
  // Validate request body
  if(req.body.order == null)
    return res.status(400).send({description: "The request <order> field cannot be null."})
  
  // Parse order to JSON
  let body = JSON.parse(req.body.order)

  // Validate body object
  if(body.products == null || body.signature == null || body.uuid == null || body.total == null)
  return res.status(400).send({description: "The request <products>, <signature>, <uuid> and <total> fields cannot be null."})

  // Validate products objects
  for(let elem of body.products) {
    if(elem.name == null || elem.quantity == null)
      return res.status(400).send({description: "The request <product.name> and <product.quantity> fields cannot be null."})
  }
  
  // Retrieve signature and delete it from body
  const signature = body.signature
  delete body.signature

  // Generate body hash
  const hash = crypto.createHash('sha256').update(JSON.stringify(body), 'utf8').digest('base64');
  
  // Retrieve and validate customer 
  const customer = await db.Customer.findOne({ where: { uuid: body.uuid }})

  if(customer == null)
    return res.status(400).send({description: "Customer with uuid <" + body.uuid + "> does not exist."})

  // Validates request through hash and signature
  await validator.validSignature(customer, hash, signature)
  
  // Voucher validation and applicability
  let total = body.total
  let voucher = await db.Voucher.findOne({ where: { code: body.voucherCode == null ? "" : body.voucherCode }})
  
  // Define voucher info variables
  let voucherCode = voucher == null ? null : voucher.dataValues.code
  let voucherType = voucher == null ? null : voucher.dataValues.type

  // Apply discount voucher and delete instance from database
  if(voucher != null) {
    if(voucher.type) 
      total = total*0.95
    
    // Delete voucher from db
    await db.Voucher.destroy({ where: { code: voucherCode }})
  }

  // Retrieve data before any insertion
  const totalBeforePurchase = Math.floor((await db.Receipt.sum('total', { where: {id_customer: customer.id}}))/100)
  const coffeeBeforePurchase = (await db.sequelize.query('SELECT sum(quantity) FROM "Quantities" INNER JOIN "Products" ON id_product = "Products".id INNER JOIN "Receipts" ON id_receipt = "Receipts".id WHERE "Receipts".id_customer = :customerId AND "Products".type = :type' , 
  { 
    replacements:  {customerId: customer.id, type: "coffee"},
    type: sequelize.QueryTypes.SELECT
  }))[0].sum

  // Generate receipt and quantity instances
  const receipt = await db.Receipt.create({ code: alpha(), total: total, date: new Date(), id_customer: customer.id, createdAt: new Date(), updatedAt: new Date() })

  // Generate quantity instances
  for(let elem of body.products) {
    const product = await db.Product.findOne({ where: { name: elem.name }})
    db.Quantity.create({id_receipt: receipt.id, id_product: product.id, quantity: elem.quantity,  createdAt: new Date(), updatedAt: new Date()})
  }
  
  // Retrieve data after any insertion
  const totalAfterPurchase = Math.floor((await db.Receipt.sum('total', { where: {id_customer: customer.id}}))/100)
  const coffeeAfterPurchase = (await db.sequelize.query('SELECT sum(quantity) FROM "Quantities" INNER JOIN "Products" ON id_product = "Products".id INNER JOIN "Receipts" ON id_receipt = "Receipts".id WHERE "Receipts".id_customer = :customerId AND "Products".type = :type' , 
  { 
    replacements:  {customerId: customer.id, type: "coffee"},
    type: sequelize.QueryTypes.SELECT
  }))[0].sum
  
  // Calculate differences
  let diffTotal = totalAfterPurchase - totalBeforePurchase
  let diffCoffees = Math.floor(coffeeAfterPurchase/3) - Math.floor(coffeeBeforePurchase/3)

  // Generate discount vouchers
  while(diffTotal > 0) {
    db.Voucher.create({type: true, date: new Date(), id_customer: customer.id, code: alpha(), createdAt: new Date(), updatedAt: new Date()})
    diffTotal--
  }

  // Generate free coffee vouchers
  while(diffCoffees > 0) {
    db.Voucher.create({type: false, date: new Date(), id_customer: customer.id, code: alpha(), createdAt: new Date(), updatedAt: new Date()})
    diffCoffees--
  }

  res.send({orderId: receipt.id, voucherCode: voucherCode, voucherType: voucherType, total: total})
})

/**
 * @description 
 * Endpoint responsible for retrieving the 
 * vouchers for a certain customer.
 * 
 * @returns
 * Returns the customer vouchers for
 * local storage on success. Otherwise returns a 
 * BAD_REQUEST.
 */
app.post('/vouchers', async (req, res) => {
  // Validate request body
  if(req.body.uuid == null || req.body.signature == null || req.body.timestamp == null)
    return res.status(400).send({description: "Both the <uuid>, <signature> and <timestamp> fields cannot be empty"})

  // Date variables definition
  let timestamp = new Date(req.body.timestamp)
  let currentDate = new Date()
  let diffDate = Math.abs(currentDate.getTime() - timestamp.getTime())
  
  // Check for timestamp fault tolerance
  if(diffDate < 0)
    return res.status(400).send({description: "Invalid timestamp."})
  else if(diffDate >= FAULT_TOLERANCE)
    return res.status(400).send({description: "Timestamp not within the defined fault tolerance."})
  
  // Retrieve and validate customer 
  const customer = await db.Customer.findOne({ where: { uuid: req.body.uuid }})

  if(customer == null)
    return res.status(400).send({description: "Customer with uuid <" + req.body.uuid + "> does not exist."})
  
  let signatureValidity = await validator.validSignature(customer, uuid.stringify(uuid.parse(customer.uuid)), req.body.signature)
  
  if(!signatureValidity)
    return res.status(400).send({description: "Signature invalid."})

  // Retrieve and send vouchers
  db.Voucher.findAll({ 
    where: {id_customer: customer.id },
    attributes: ['type', 'code', 'date']
  })
  .then((vouchers) => {
    res.send(vouchers)
  })
})



// Development
/* const ADDRESS = '192.168.1.166' // Run ipconfig to check your IPv4 Address 

app.listen(PORT, ADDRESS, () => {
  console.log(`App is running on http://${ ADDRESS }:${ PORT }`)
}); */

// Production
app.listen(PORT, () => {
  console.log(`App is running on port ${ PORT }`)
});

