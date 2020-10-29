const express = require('express')
const app = express()
const PORT = process.env.PORT || 3000

app.use(express.urlencoded({ extended: true }))

app.get('/', (req, res) => {
  res.send('Hello World!')
})

app.get('/test/api', (req, res) => {
  res.send('Api is working!')
})

app.listen(PORT, () => {
  console.log(`Our app is running on port ${ PORT }`)
});

