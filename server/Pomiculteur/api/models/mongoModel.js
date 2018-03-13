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

cr.save = function (crNumber, crId, crErreur, crStatus, crPhotos){

var crvar = new cr({
mission : crNumber,
id : crId,
erreur: crErreur,
status: crStatus,
photos : crPhotos
})

crvar.save(function(err) {
  if (err) throw err;
  console.log('CR saved successfully!');
})
}

mission.save = function (missionTitre, missionId, missionDate, missionStatus, missionTrajectory){

var missionvar = new mission({
titre : missionTitre,
id : missionId,
date: missionDate,
status: missionStatus,
trajectory : missionTrajectory
})

missionvar.save(function(err) {
  if (err) throw err;
  console.log('CR saved successfully!');
})
}

module.exports.cr = cr;
module.exports.mission = mission;

