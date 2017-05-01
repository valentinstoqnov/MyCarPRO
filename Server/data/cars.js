var Car = require('mongoose').model('Car');

module.exports = {
    create: function (room, callback) {
        Car.create(room, callback);
    },

    find: function (args, callback) {
        Car.find(args, callback);
    },

    findOne: function (id, callback) {
        Car.findOne({"_id": id},callback)
    },
    delete:function (id, callback) {
        Car.findOneAndRemove({_id:id},function (err) {
            callback;
        })
    }
};
