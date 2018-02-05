var express = require('express'),
    app = express(),
    Task = require('./api/models/todoListModel'), //created model loading here
    mongoose = require('mongoose'),
    port = 8080;

var routes = require('./api/routes/todoListRoutes'); //importing route
routes(app); //register the route

mongoose.Promise = global.Promise;
mongoose.connect('mongodb://localhost/LePommier');







app.use(function(req, res) {
    res.status(404).send({url: req.originalUrl + ' not found'})
});

app.listen(port);


