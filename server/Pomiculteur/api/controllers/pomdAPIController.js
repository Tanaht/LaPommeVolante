'use strict';

var mongoose = require('mongoose');
//var Task = mongoose.model('Tasks');
exports.socketPomdAPI = null;


var sample = {
    'type': 'mission_order',
    'data': {
        'title': "highway to hell",
        'trajectory': [
            {'lat': 48.1148383, 'lon': -1.6388297, 'alt': 10, 'photo': false},
            {'lat': 48.1153379, 'lon': -1.6391757, 'alt': 10, 'photo': false},
            {'lat': 48.1161849, 'lon': -1.6390014, 'alt': 10, 'photo': false},
            {'lat': 48.1164571, 'lon': -1.6373706, 'alt': 10, 'photo': false},
            {'lat': 48.1155689, 'lon': -1.6360724, 'alt': 10, 'photo': false},
            {'lat': 48.1152322, 'lon': -1.6378534, 'alt': 10, 'photo': false}]
    }
};


exports.onClientConnected = function(socketClient) {
    console.info("new client connected to the socket");
    exports.socketPomdAPI = socketClient;
    socketClient.write(JSON.stringify(sample));
};

exports.onClientSendData = function (socketClient, data) {
    console.info("new data from client : " + data);
};

exports.onClientDisconnected = function (socketClient) {
    console.info("client disconnected");
};