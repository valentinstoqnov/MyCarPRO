var Car = require('mongoose').model('Car');

module.exports = {
    create: function (room, callback) {
        Car.create(room, callback);
    },

    find: function (args, callback) {
        console.log(args.query.creator)
        Car.find({"creator": args.query.creator}, callback);
    },

    findOne: function (args, callback) {
        Car.findOne(args, callback);
    }
};
