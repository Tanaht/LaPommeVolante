var express = require('express');
var app = express();
var mongoose = require('mongoose');
var port = 8080;

var Tasks = require("./api/models/todoListModel.js");

var http = require('http').Server(app);

var net = require('net');


var routes = require('./api/routes/todoListRoutes'); //importing route
routes(app); //register the route

mongoose.Promise = global.Promise;
mongoose.connect('mongodb://localhost/LePommier');


var serverSocket = net.createServer(function (socket) {
    socket.write("Welcome \n");

}).listen(5000);





app.use(function(req, res) {
    res.status(404).send({url: req.originalUrl + ' not found'})
});

app.listen(port);


