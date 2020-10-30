# ACME Server

## Getting started

### Environment

* Windows 10 64bits
* Heroku
* Node JS
    * Express
    * Postgres
    * [Sequelize](https://sequelize.org/master/manual/getting-started.html)


### Setup 

* Run: `npm install` at the root directory level. It will automatically install all the needed dependencies and build the project.
* Install the [heroku-cli](https://devcenter.heroku.com/articles/heroku-cli).
* Install [postgres](https://www.postgresql.org/download/) in your machine. After installation, create two local databases:
    * acme_dev 
    * acme_test
* Your database information must comply with the information presented on the `config/config.json` file.

### Heroku Deployment

* Make sure you are logged in.
    * `heroku login`
* Commit all the modifications by executing the following commands:
    * `git add .`
    * `git commit -am <message>`
* Deploy the modifications to heroku
    * `git push heroku master`


### Database Managment

#### Development 

* Run `CREATE DATABASE <db_name>` to create a local database.
* Run `DROP DATABASE "<db_name>"` to delete a local database.
* Run `psql -U postgres` to acess the database managment environment
* Run `psql -U postgres -p 5432 "<db_name>"` to acess the database managment environment of a specific database.
* Migrations 
    * `npx sequelize-cli db:migrate`
    * `npx sequelize-cli db:migrate:undo`
    * `npx sequelize-cli db:migrate:undo:all`
* Seeders
    * `npx sequelize-cli seed:generate --name <model_name>`

#### Production

* Run `heroku run bash` to access the production environment
* The migrations and seeders commands are the same for the production environment. However, instead of `npx sequelize-cli` you should only use `sequelize`. 

**Note:** The sequelize commands must be executed inside the `app` directory.

### Running

#### Development 

* Run: `heroku local` or `npm start` at the root directory level.

#### Production

* Run: `heroku open` at the root directory level.
* Run `heroku logs --tail` to trace all the app logs.