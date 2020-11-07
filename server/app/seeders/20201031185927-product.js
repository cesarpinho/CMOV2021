'use strict';

module.exports = {
  up: async (queryInterface, Sequelize) => { 
    return queryInterface.bulkInsert('Products', [
      {type: 'coffee', icon: 'https://images.freeimages.com/images/premium/small-comps/2380/23805331-coffee-art.jpg', name: 'COFFEE A', price: 0.5, createdAt: new Date(), updatedAt: new Date()},
      {type: 'coffee', icon: 'https://pbs.twimg.com/profile_images/703891712427425793/KF1zgVqx.jpg', name: 'COFFEE B', price: 0.75, createdAt: new Date(), updatedAt: new Date()},
      {type: 'coffee', icon: 'http://i.imgur.com/DvpvklR.png', name: 'COFFEE C', price: 1.5, createdAt: new Date(), updatedAt: new Date()},
      {type: 'drink', icon: 'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRBHJ0huLjA-K31IVui7sPVZj_nC4acZbfgWQ&usqp=CAU', name: 'DRINK A', price: 1.5, createdAt: new Date(), updatedAt: new Date()},
      {type: 'drink', icon: 'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRYME2L4Hcncff5cQiafc8tz0h-H76PK-PE1w&usqp=CAU', name: 'DRINK B', price: 2.5, createdAt: new Date(), updatedAt: new Date()},
      {type: 'drink', icon: 'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQhS6WbFgcxW0Gs7fwuvOWLO0OsoiPpr6jRaw&usqp=CAU', name: 'DRINK C', price: 3.5, createdAt: new Date(), updatedAt: new Date()},
      {type: 'snack', icon: 'https://pbs.twimg.com/profile_images/2731599222/efcd6910b2b007029726ec62fda265cf.jpeg', name: 'SNACK A', price: 2.5, createdAt: new Date(), updatedAt: new Date()},
      {type: 'snack', icon: 'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSs9LGR5IsTy2UQC5nxD87v-XvSDBNqy0LCBg&usqp=CAU', name: 'SNACK B', price: 3.5, createdAt: new Date(), updatedAt: new Date()},
      {type: 'snack', icon: 'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ5_0iP5YSQfydRwdXjkfLYC8HG5T0evJXG7Q&usqp=CAU', name: 'SNACK C', price: 5.0, createdAt: new Date(), updatedAt: new Date()}
    ]); 
  },

  down: async (queryInterface, Sequelize) => {
    return queryInterface.bulkDelete('Products', null, {});
  }
};
