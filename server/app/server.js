const express = require('express')
const app = express()
const PORT = process.env.PORT || 3000

// const db = require('./models/index.js')

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


// Development
const ADDRESS = '192.168.0.100' // Run ipconfig to check your IPv4 Address 

app.listen(PORT, ADDRESS, () => {
  console.log(`App is running on http://${ ADDRESS }:${ PORT }`)
});

// Production
/* app.listen(PORT, () => {
  console.log(`App is running on port ${ PORT }`)
});
 */