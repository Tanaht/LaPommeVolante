'use strict';
var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var TaskSchema = new Schema({
mission:       { type: int },
id:       { type: int },
status:       { type: Boolean },
erreur:       { type: Boolean },
status:       { type: String },
photos : [{ lat:Float, long:Float, alt:Float, url:String}]
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