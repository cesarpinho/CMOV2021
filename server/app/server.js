///////////////////
// CONFIGURATION //
///////////////////

const express = require('express')
const app = express()
const uuid = require('uuid')
const bcrypt = require('bcrypt')
const bodyParser = require('body-parser')

const PORT = process.env.PORT || 3000
const SALT_ROUNDS = 10

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
 * < Description >
 * Endpoint responsible for registering new
 * customers into the server.
 * 
 * < Return >
 * Returns the needed user information for 
 * local storage on success. Otherwise, returns
 * an empty response with the 400 status code.
 */
app.post('/register', (req, res) => {
  // TODO - Deal with the certificate

  // Generate uuid and hashed password
  const customerID = uuid.v4()
  const hash = bcrypt.hashSync(req.body.password, SALT_ROUNDS);
  
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
    else
      res.send({
        "uuid": customer.dataValues.uuid,
        "name": customer.dataValues.name,
        "card": customer.dataValues.card,
        "nif": customer.dataValues.nif,
        "nickname": customer.dataValues.nickname
      })
  })
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
