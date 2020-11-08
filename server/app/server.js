///////////////////
// CONFIGURATION //
///////////////////

const express = require('express')
const app = express()
const uuid = require('uuid')
const bcrypt = require('bcrypt')
const bodyParser = require('body-parser')
const validator = require('./validator.js')


const PORT = process.env.PORT || 3000
const SALT_ROUNDS = 10
const FAULT_TOLERANCE = 1000

const db = require('./models/index.js')

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
 * vouchers for a certain customer.
 * 
 * @returns
 * Returns the customer vouchers for
 * local storage on success. Otherwise returns a 
 * BAD_REQUEST.
 */
app.post('/vouchers', async (req, res) => {
  // Validate request body
  if(req.body.nickname == null || req.body.uuid == null || req.body.timestamp == null)
    return res.status(400).send({description: "Both the <uuid> and <timestamp> fields cannot be empty"})

  // Date variables definition
  let timestamp = new Date(req.body.timestamp)
  let currentDate = new Date()
  let diffDate = currentDate.getTime() - timestamp.getTime()
  
  // Check for timestamp fault tolerance
  if(diffDate < 0)
    return res.status(400).send({description: "Invalid timestamp."})
  else if(diffDate >= FAULT_TOLERANCE)
    return res.status(400).send({description: "Timestamp not within the defined fault tolerance."})
  
  // Retrieve and validate customer 
  const customer = await db.Customer.findOne({ where: { nickname: req.body.nickname }})

  if(customer == null)
    return res.status(400).send({description: "Customer with nickname <" + req.body.nickname + "> does not exist."})
  
  let signatureValidity = await validator.validSignature(customer, req.body.uuid)

  if(!signatureValidity)
    return res.status(400).send({description: "Signature invalid,"})
  
  // Retrieve and send vouchers
  db.Voucher.findAll({ where: {id_customer: customer.id }})
  .then((vouchers) => {
    res.send(vouchers)
  })
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

// Development
const ADDRESS = '192.168.0.101' // Run ipconfig to check your IPv4 Address 

app.listen(PORT, ADDRESS, () => {
  console.log(`App is running on http://${ ADDRESS }:${ PORT }`)
});

// Production
/* app.listen(PORT, () => {
  console.log(`App is running on port ${ PORT }`)
});
 */
