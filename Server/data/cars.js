var Car = require('mongoose').model('Car');

module.exports = {
    create: function (room, callback) {
        Car.create(room, callback);
    },

    find: function (args, callback) {
        Car.find({"creator": args.query.user}, callback);
    },

    findOne: function (username, callback) {
        Car.findOne({"carOwner": username}, callback);
    }
};
