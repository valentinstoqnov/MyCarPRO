var mongoose = require('mongoose');

module.exports.init = function () {
    var carSchema = mongoose.Schema({
        carOwner: {type: String, required: true},
        name: {type: String, required: true},
        make: {type: String, required: true},
        model: {type: String, required: true},
        color: {type: String, required: true},
        horse_power: {type: Number, required: true},
        manufacture_date: {type: String, required: true},
        fuel_tank_capacity: {type: Number, required: true},
        fuel_consumption: {type: Number, required: true},
        fuel_type: {type: String, required: true},
        services: [{
            type: {type: String, required: true},
            price: {type: Number, required: true},
            odometer: {type: Number, required: true},
            note: {type: String, required: true},
            date_time: {type: String, required: true}
        }],
        insurances: [{
            company_name: {type: String, required: true},
            price: {type: Number, required: true},
            odometer: {type: Number, required: true},
            note: {type: String, required: true},
            date: {type: String, required: true},
            expiration_date: {type: String, required: true}
        }],
        refuelings: [{
            gas_station_name: {type: String, required: true},
            quantity: {type: Number, required: true},
            price: {type: Number, required: true},
            odometer: {type: Number, required: true},
            note: {type: String, required: true},
            date_time: {type: String, required: true}
        }],
        odometer: {type: Number, required: true},
        note: {type: String, required: true}
    });

    var Room = mongoose.model('Car', carSchema);
};
