'use strict';

const uuid = require('uuid')
const bcrypt = require('bcrypt')

module.exports = {
  up: async (queryInterface, Sequelize) => {
   return queryInterface.bulkInsert('Customers', [
     {name: "admin", card: 4231312312312311, nif: 123456789, nickname: "admin", password: bcrypt.hashSync("a1234", 10), uuid: uuid.v4(), createdAt: new Date(), updatedAt: new Date() }
   ])
  },

  down: async (queryInterface, Sequelize) => {
    return queryInterface.bulkDelete('Customers', null, {});
  }
};
