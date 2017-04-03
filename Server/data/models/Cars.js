var mongoose = require('mongoose');

module.exports.init = function () {
    var carSchema = mongoose.Schema({
        name: {type: String, required: true},
        mark: {type: String, required: true},
        model: {type: String, required: true},
        color: {type: String, required: true},
        date: {type: String, required: true},
        services: {type: [{

        }], required: true},

        creator: {type: String, required: true}
    });

    var Room = mongoose.model('Car', carSchema);
};
