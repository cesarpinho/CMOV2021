'use strict';

module.exports = {
  up: async (queryInterface, Sequelize) => { 
    return queryInterface.bulkInsert('Products', [
      {type: 'coffee', icon: '', name: 'COFFEE A', price: 0.5, createdAt: new Date(), updatedAt: new Date()},
      {type: 'coffee', icon: '', name: 'COFFEE B', price: 0.75, createdAt: new Date(), updatedAt: new Date()},
      {type: 'coffee', icon: '', name: 'COFFEE C', price: 1.5, createdAt: new Date(), updatedAt: new Date()},
      {type: 'drink', icon: '', name: 'DRINK A', price: 1.5, createdAt: new Date(), updatedAt: new Date()},
      {type: 'drink', icon: '', name: 'DRINK B', price: 2.5, createdAt: new Date(), updatedAt: new Date()},
      {type: 'drink', icon: '', name: 'DRINK C', price: 3.5, createdAt: new Date(), updatedAt: new Date()},
      {type: 'snack', icon: '', name: 'SNACK A', price: 2.5, createdAt: new Date(), updatedAt: new Date()},
      {type: 'snack', icon: '', name: 'SNACK B', price: 3.5, createdAt: new Date(), updatedAt: new Date()},
      {type: 'snack', icon: '', name: 'SNACK C', price: 5.0, createdAt: new Date(), updatedAt: new Date()}
    ]); 
  },

  down: async (queryInterface, Sequelize) => {
    return queryInterface.bulkDelete('Products', null, {});
  }
};
