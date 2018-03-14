'use strict';
module.exports = function(app) {
    var todoList = require('../controllers/restApiController');

    // todoList Routes
    app.route('/report/:id')
        .get(todoList.report);

    app.route('/mission_list')
        .get(todoList.mission_list);

    app.route('/mission/order')
        .post(todoList.mission_order);

};