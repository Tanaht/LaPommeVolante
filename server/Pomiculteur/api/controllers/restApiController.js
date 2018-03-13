'use strict';

var mongoose = require('mongoose');
var TaskMission = mongoose.model('MissionTask');
var TaskCR = mongoose.model('CRTask');
var pomDAPIController = require('./pomdAPIController.js');

exports.report = function(req, res) {
    console.log(req.body);
    res.send(req.body);
};

exports.mission_list = function(req, res) {
    console.log(req.body);
    res.send(req.body);
};

exports.mission_order = function(req, res) {
    console.log(req.body);
    pomDAPIController.sendMsgToPasserel(JSON.stringify(req.body));
};



