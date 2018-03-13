'use strict';

var mongoose = require('mongoose'),
    Task = mongoose.model('Tasks');

exports.list_all_tasks = function(req, res) {
    Task.find({}, function(err, task) {
        if (err)
            res.send(err);
        res.json(task);
    });
};

exports.test = function(req, res) {
    res.send("test");
};

exports.testParam = function(req, res) {
    res.send("test "+req.params.param);
};

exports.report = function(req, res) {
    res.send("report "+req.params.param);
};

exports.mission_list = function(req, res) {
    res.send("mission_list "+req.params.param);
};

exports.mission_list = function(req, res) {
    res.send("mission_list "+req.params.param);
};

exports.mission_order = function(req, res) {
    res.send("mission_order "+req.params.param);
};



