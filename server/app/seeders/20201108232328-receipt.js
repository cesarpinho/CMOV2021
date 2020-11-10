'use strict';

const nano = require('nanoid')
const alpha = nano.customAlphabet('1234567890ABCDEFGHIJKLMNOPQRSTUVXZ', 9)

module.exports = {
  up: async (queryInterface, Sequelize) => {
    return queryInterface.bulkInsert('Receipts', [
      {date: new Date(), total: 2.0, id_customer: 1, code: alpha(), createdAt: new Date(), updatedAt: new Date()},
      {date: new Date(), total: 2.25, id_customer: 1, code: alpha(), createdAt: new Date(), updatedAt: new Date()},
      {date: new Date(), total: 7.0, id_customer: 1, code: alpha(), createdAt: new Date(), updatedAt: new Date()}
    ])
  },

  down: async (queryInterface, Sequelize) => {
    return queryInterface.bulkDelete('Receipts', null, {});
  }
};
