var mongoose = require('mongoose'),
    config = require('./config.js'),
    UserModel = require('../data/models/User'),
    CarModel = require('../data/models/Cars');

module.exports = function(config) {
    mongoose.connect(config.db);
    var db = mongoose.connection;

    db.once('open', function(err) {
        if (err) {
            console.log('Database could not be opened: ' + err);
            return;
        }

        console.log('Database up and running...');
    });

    db.on('error', function(err){
        console.log('Database error: ' + err);
    });

    UserModel.init();
    CarModel.init();
};
