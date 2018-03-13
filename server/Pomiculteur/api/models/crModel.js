'use strict';
var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var TaskSchema = new Schema({
mission:       { type: Number },
id:       { type: Number },
erreur:       { type: Boolean },
status:       { type: String },
photos : [{ lat:Number, long:Number, alt:Number, url:String}]
});


module.exports = mongoose.model('Tasks', TaskSchema);