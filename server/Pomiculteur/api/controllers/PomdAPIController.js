'use strict';

var mongoose = require('mongoose'),
    Task = mongoose.model('Tasks');

exports.onClientConnected = function(socketClient) {
    console.log("new client connected to the socket");
};

exports.onClientSendData = function (socketClient, data) {
    console.log("new data from client : " + data);
    socketClient.write("ok " + data);
};

exports.onClientDisconnect = function (socketClient) {
    console.log("client disconected");
};