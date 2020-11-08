'use strict';

module.exports = {
  up: async (queryInterface, Sequelize) => {
    return queryInterface.bulkInsert('Receipts', [
      {date: new Date(), total: 2.0, id_customer: 1, createdAt: new Date(), updatedAt: new Date()},
      {date: new Date(), total: 2.25, id_customer: 1, createdAt: new Date(), updatedAt: new Date()},
      {date: new Date(), total: 7.0, id_customer: 1, createdAt: new Date(), updatedAt: new Date()}
    ])
  },

  down: async (queryInterface, Sequelize) => {
    return queryInterface.bulkDelete('Receipts', null, {});
  }
};
