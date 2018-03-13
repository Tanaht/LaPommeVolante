var express = require('express');
var app = express();
var net = require('net');
var mongoose = require('mongoose');

var portApiRest = 8080;
var portSocket = 5000;

var Tasks = require("./api/models/crModel.js");

var pomDAPIController = require('./api/controllers/PomdAPIController.js');

var routes = require('./api/routes/todoListRoutes'); //importing route
routes(app); //register the route

mongoose.Promise = global.Promise;
mongoose.connect('mongodb://localhost/LePommier');

net.createServer(function (socket) {
    pomDAPIController.onClientConnected(socket);
    socket.on('data', function (data) {
        pomDAPIController.onClientSendData(socket,data);
    });

    socket.on('end', function () {
        pomDAPIController.onClientDisconnected(socket);
    });

}).listen(portSocket);



app.use(function(req, res) {
    res.status(404).send({url: req.originalUrl + ' not found'})
});

app.listen(portApiRest);


