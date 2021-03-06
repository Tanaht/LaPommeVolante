var express = require('express');
var bodyParser = require('body-parser');
var app = express();
var net = require('net');
var mongoose = require('mongoose');

var portApiRest = 8080;
var portSocket = 5000;

var Tasks = require("./api/models/mongoModel.js");

var pomDAPIController = require('./api/controllers/pomdAPIController.js');

var routes = require('./api/routes/restApiRoutes'); //importing route

app.use(bodyParser.json());

routes(app); //register the route

mongoose.Promise = global.Promise;
mongoose.connect('mongodb://0.0.0.0:27017/');

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


