///////////////////
// CONFIGURATION //
///////////////////

const express = require('express')
const app = express()
const uuid = require('uuid')
const bcrypt = require('bcrypt')
const forge = require('node-forge')
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

var crypto = require('crypto');

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
  // TODO - Validate request body
  // TODO - Deal with the certificate to retrieve the user public key
  // TODO - Add certificate/public key to server database depending on the differente possibilities
  // TODO - Depending on the applicability of the previous TODO, create a public key table to boost app robustness

  // Generate uuid and hashed password
  const customerID = uuid.v4()
  const hash = bcrypt.hashSync(req.body.password, SALT_ROUNDS);

  
  var object = forge.asn1.fromDer(forge.util.decode64(req.body.certificate));

  const cert = forge.pki.certificateFromAsn1(object)

  console.log(forge.pki.publicKeyToPem(cert.publicKey))

  var verifier = crypto.createVerify('sha256WithRSAEncryption');
  verifier.update("Robert22o");
  var ver = verifier.verify(forge.pki.publicKeyToPem(cert.publicKey), req.body.name, 'base64');
  console.log(ver);//<--- always false!

  res.send({error: "asdasasd"})
  /* // Store information on server database
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
  }) */
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
