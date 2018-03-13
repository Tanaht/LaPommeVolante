'use strict';

var mongoose = require('mongoose');
    //Task = mongoose.model('Tasks');
//var pomDAPIController = require('./api/controllers/pomdAPIController.js');

exports.report = function(req, res) {
    console.log(req.params.param);
    res.send("report "+req.params.param);
};

exports.mission_list = function(req, res) {
    console.log(req.params.param);
    res.send("mission_list "+req.params.param);
};

exports.mission_order = function(req, res) {
    console.log(req.body);
    res.send("mission_order "+req.params.param);
};



