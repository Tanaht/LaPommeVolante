'use strict';
var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var TaskSchema = new Schema({
titre:       { type: String },
id:       { type: int },
date:       { type: String },
trajectory : [{ lat:Float, long:Float, alt:Float, photo:Boolean}]
});

/*
var TaskSchema = new Schema({
    name: {
        type: String,
        required: 'Kindly enter the name of the task'
    },
    Created_date: {
        type: Date,
        default: Date.now
    },
    status: {
        type: [{
            type: String,
            enum: ['pending', 'ongoing', 'completed']
        }],
        default: ['pending']
    }
});
*/




module.exports = mongoose.model('Tasks', TaskSchema);