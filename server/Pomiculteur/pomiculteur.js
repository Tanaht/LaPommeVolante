var express = require('express');
var app = express();
var Task = require('./api/models/todoListModel'); //created model loading here
var mongoose = require('mongoose');
var port = 8080;

var http = require('http').Server(app);
var io = require('socket.io')(http);

var routes = require('./api/routes/todoListRoutes'); //importing route
routes(app); //register the route

mongoose.Promise = global.Promise;
mongoose.connect('mongodb://localhost/LePommier');







app.use(function(req, res) {
    res.status(404).send({url: req.originalUrl + ' not found'})
});

app.listen(port);


