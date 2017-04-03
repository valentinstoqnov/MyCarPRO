var mongoose = require('mongoose');

module.exports.init = function () {
    var carSchema = mongoose.Schema({
        name: {type: String, required: true},
        creator: {type: String, required: true}
    });

    var Room = mongoose.model('Car', carSchema);
};
