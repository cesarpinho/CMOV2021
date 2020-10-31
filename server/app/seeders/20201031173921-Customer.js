'use strict';

module.exports = {
  up: async (queryInterface, Sequelize) => {
    return queryInterface.bulkInsert('Customers', [
      {
        uuid: 'a12378ec-1ba0-11eb-adc1-0242ac120002',
        name: 'John',
        card: 123456789,
        nif: 123456789,
        nickname: 'Robertnick',
        password: 'asdasd21312das2',
        createdAt: new Date(),
        updatedAt: new Date()
      }
    ]);
  },

  down: async (queryInterface, Sequelize) => {
    /**
     * Add commands to revert seed here.
     *
     * Example:
     * await queryInterface.bulkDelete('People', null, {});
     */
    return queryInterface.bulkDelete('Customers', null, {})
  }
};
