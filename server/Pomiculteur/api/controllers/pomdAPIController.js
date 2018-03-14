'use strict';

var mongoose = require('mongoose');
var TaskMission = mongoose.model('MissionTask');
var TaskCR = mongoose.model('CRTask');
var socketPomdAPI = null;


exports.onClientConnected = function(socketClient) {
    console.info("new client connected to the socket");
    socketPomdAPI = socketClient;
};

exports.onClientSendData = function (socketClient, data) {
    console.info("new data from client : " + data);
};

exports.onClientDisconnected = function (socketClient) {
    console.info("client disconnected");
    socketPomdAPI = null;
};


exports.sendMsgToPasserel = function(msg){
    if(socketPomdAPI !== null){
        socketPomdAPI.write(msg);
    }

};
