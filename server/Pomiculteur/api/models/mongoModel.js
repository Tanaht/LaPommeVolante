'use strict';
var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var CRTaskSchema = new Schema({
mission:       { type: Number },
id:       { type: Number },
erreur:       { type: Boolean },
status:       { type: String },
photos : [{ lat:Number, long:Number, alt:Number, url:String}]
});

var MissionTaskSchema = new Schema({
titre:       { type: String },
id:       { type: Number },
date:       { type: String },
trajectory : [{ lat:Number, long:Number, alt:Number, photo:Boolean}]
});


var mission = mongoose.model('MissionTask', MissionTaskSchema);
var cr = mongoose.model('CRTask', CRTaskSchema);

module.exports.cr = cr;
module.exports.mission = mission;

