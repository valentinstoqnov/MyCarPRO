var mongoose = require('mongoose');

module.exports.init = function () {
    var carSchema = mongoose.Schema({
        carOwner: {type: String, required: true},
        name: {type: String, required: true},
        mark: {type: String, required: true},
        model: {type: String, required: true},
        color: {type: String},
        date: {type: String},
        fueling:[{
            kilometers: Number,
            date: String,
            price:Number
        }],
        services: [{
            type: String,
            date: String
        }],
    });

    var Room = mongoose.model('Car', carSchema);
};
