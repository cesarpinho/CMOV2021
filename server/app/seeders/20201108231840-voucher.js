'use strict';

const nano = require('nanoid')
const alpha = nano.customAlphabet('1234567890ABCDEFGHIJKLMNOPQRSTUVXZ', 9)

module.exports = {
  up: async (queryInterface, Sequelize) => {
    return queryInterface.bulkInsert('Vouchers', [
      {type: true, date: new Date(), id_customer: 1, code: alpha(), createdAt: new Date(), updatedAt: new Date()},
      {type: false, date: new Date(), id_customer: 1, code: alpha(), createdAt: new Date(), updatedAt: new Date()},
      {type: true, date: new Date(), id_customer: 1, code: alpha(), createdAt: new Date(), updatedAt: new Date()}
    ])
  },

  down: async (queryInterface, Sequelize) => {
    return queryInterface.bulkDelete('Vouchers', null, {});
  }
};
