'use strict';

var mongoose = require('mongoose')
var Task = mongoose.model('Tasks');
var socketPomdAPI = null;

exports.onClientConnected = function(socketClient) {
    console.info("new client connected to the socket");
    socketPomdAPI = socketClient;
};

exports.onClientSendData = function (socketClient, data) {
    console.info("new data from client : " + data);
    socketClient.write("ok " + data);


};

exports.onClientDisconnected = function (socketClient) {
    console.info("client disconnected");
};