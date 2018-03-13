'use strict';
var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var TaskSchema = new Schema({
titre:       { type: String },
id:       { type: Number },
date:       { type: String },
trajectory : [{ lat:Number, long:Number, alt:Number, photo:Boolean}]
});


module.exports = mongoose.model('Tasks', TaskSchema);