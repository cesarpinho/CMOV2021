'use strict';

module.exports = {
  up: async (queryInterface, Sequelize) => {
    return queryInterface.bulkInsert('Vouchers', [
      {type: true, date: new Date(), id_customer: 1, createdAt: new Date(), updatedAt: new Date()},
      {type: false, date: new Date(), id_customer: 1, createdAt: new Date(), updatedAt: new Date()},
      {type: true, date: new Date(), id_customer: 1, createdAt: new Date(), updatedAt: new Date()}
    ])
  },

  down: async (queryInterface, Sequelize) => {
    return queryInterface.bulkDelete('Vouchers', null, {});
  }
};
