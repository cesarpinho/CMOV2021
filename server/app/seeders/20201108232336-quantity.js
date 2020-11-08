'use strict';

module.exports = {
  up: async (queryInterface, Sequelize) => {
    return queryInterface.bulkInsert('Quantities', [
      {id_receipt: 1, id_product: 1, quantity: 1,  createdAt: new Date(), updatedAt: new Date()},
      {id_receipt: 1, id_product: 4, quantity: 1,  createdAt: new Date(), updatedAt: new Date()},
      {id_receipt: 2, id_product: 2, quantity: 1,  createdAt: new Date(), updatedAt: new Date()},
      {id_receipt: 2, id_product: 4, quantity: 1,  createdAt: new Date(), updatedAt: new Date()},
      {id_receipt: 3, id_product: 1, quantity: 1,  createdAt: new Date(), updatedAt: new Date()},
      {id_receipt: 3, id_product: 4, quantity: 1,  createdAt: new Date(), updatedAt: new Date()},
      {id_receipt: 3, id_product: 9, quantity: 1,  createdAt: new Date(), updatedAt: new Date()}
    ])
  },

  down: async (queryInterface, Sequelize) => {
    return queryInterface.bulkDelete('Quantities', null, {});
  }
};
