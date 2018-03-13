'use strict';

var mongoose = require('mongoose');
var TaskMission = mongoose.model('MissionTask');
var TaskCR = mongoose.model('CRTask');
var pomDAPIController = require('./pomdAPIController.js');

var report =
    {
        'type': 'report',
        'data': {
            'id': 1, // ID du compte-rendu
            'mission': 1, // ID de la mission
            'status': true, // réussite de la mission ? true: oui, false: non
            'error': "", // message d'erreur si besoin ("" sinon)
            'photos': [ // liste des photos
                {
                    'lat': 27.00, // latitude
                    'lon': 28.00, // longitude
                    'alt': 19., // altitude
                    'url': "http://wwww.jeSaisPasOuJeSuis.com" // URL menant à la photo
                }
            ]
        }
    };

var list =
    {
        'type': 'mission_list',
        'data': [
            {
                'mission': {
                    'id': 3, // ID de la mission
                    'date': "Jesus Carlos's Birthday",
                    'title': "Good Enough", // nom de la mission
                    'trajectory': [ // liste des points d'observation
                        {
                            'lat': 42.00, // latitude
                            'lon': 42.00, // longitude
                            'alt': 42.00, // altitude
                            'photo': true // prendre une photo ? true: oui; false: non
                        }
                    ]
                },

                'status': "en cours" // "en cours" ou "terminée"
            },
            {
                'mission': {
                    'id': 2, // ID de la mission
                    'date': "El famoso date",
                    'title': "Mendes", // nom de la mission
                    'trajectory': [ // liste des points d'observation
                        {
                            'lat': 42.00, // latitude
                            'lon': 42.00, // longitude
                            'alt': 42.00, // altitude
                            'photo': true // prendre une photo ? true: oui; false: non
                        }
                    ]
                },

                'status': "en cours" // "en cours" ou "terminée"
            }
        ]
    };

exports.report = function(req, res) {
    console.log(req.body);
    res.send(report);
};

exports.mission_list = function(req, res) {
    console.log(req.body);
    res.send(list);
};

exports.mission_order = function(req, res) {
    console.log(req.body);
    pomDAPIController.sendMsgToPasserel(JSON.stringify(req.body));
};



