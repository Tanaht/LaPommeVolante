/*'use strict';
var mongoose = require('mongoose');
var Schema = mongoose.Schema;
var app = express();

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

mission.save(function(error) { //This saves the information
    console.log("Your mission has been saved.");
    if (error) {
    console.error(error);
  }
});

cr.save(function(error) { //This saves the information
    console.log("Your CR has been saved.");
    if (error) {
    console.error(error);
  }
});


app.get('/save/:query', cors(), function(req, res) {
	var query = req.params.query;

	var savedata = new Model({
		'request': query,
		'time': Math.floor(Date.now() / 1000)
	}).save(function(err, result) {
		if (err) throw err;

		if(result) {
			res.json(result)
		}
	})
});

module.exports.app = app;
module.exports.cr = cr;
module.exports.mission = mission;
*/
