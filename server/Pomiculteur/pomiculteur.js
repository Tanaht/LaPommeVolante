var express = require('express');
var app = express();
var net = require('net');
var mongoose = require('mongoose');

var portApiRest = 8080;
var portSocket = 5000;

var Tasks = require("./api/models/todoListModel.js");
var routes = require('./api/routes/todoListRoutes'); //importing route

routes(app); //register the route

mongoose.Promise = global.Promise;
mongoose.connect('mongodb://localhost/LePommier');



var serverSocket = net.createServer(function (socket) {
    console.log("new client connected to the socket");

    socket.on('data', function (data) {
        console.log("new data from client : "+data);
        socket.write("ok"+data);
    });

    socket.on('end', function () {
        console.log("client disconected");
    });

}).listen(portSocket);



app.use(function(req, res) {
    res.status(404).send({url: req.originalUrl + ' not found'})
});

app.listen(portApiRest);


