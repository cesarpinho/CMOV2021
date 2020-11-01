const express = require('express')
const app = express()
const PORT = process.env.PORT || 3000

const db = require('./models/index.js')

app.use(express.urlencoded({ extended: true }))

app.get('/', (req, res) => {
  res.send('Hello World!')
})

/* app.get('/url', function(req, res) {
  db.Url.findOrCreate({where: {url: "url", shortUrl: "shortUrl"}})
    .then(([urlObj, created]) => {
      res.send(urlObj)
    })
})


app.get('/test/api', (req, res) => {
  db.Url.findAll().then(([urlObj, created]) => {
    res.send(urlObj)
  })
}) */

// For test purposes only
app.get('/url', function(req, res) {
  db.Customer
  db.Customer.findOrCreate({where: {id: 1, name: "", nif: "", nickname: "", password: ""}})
    .then(([urlObj, created]) => {
      res.send(urlObj)
    })
})

app.get('/posts', (req, res) => {
  res.send([{'user_id': 5, 'id': 34, 'title': "", 'body': ""}])
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
