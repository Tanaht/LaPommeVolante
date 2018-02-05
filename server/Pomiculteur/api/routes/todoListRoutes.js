'use strict';
module.exports = function(app) {
    var todoList = require('../controllers/todoListController');

    // todoList Routes
    app.route('/test')
        .get(todoList.test);

    app.route('/test/:param')
        .get(todoList.testParam);
};