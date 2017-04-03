var cars = require('../data/cars');

module.exports = {
    create: function (req, res, next) {
        if (!req.body.name) {
            res.status(400);
            return res.send({reason: "Bad car!"});
        }

        var car = {
            name: req.body.name,
            creator: req.body.creator,
            players: [req.body.creator]
        };

        cars.create(car, function (err, room) {
            if (err) {
                res.status(400);
                return res.send({reason: err.toString()});
            }

            return res.send(room);
        });
    },

    get: function (req, res, next) {
        cars.find(req, function (err, rooms) {
            if (err) {
                res.status(400);
                return res.send({reason: "Bad room!"});
            }

            return res.send(rooms);
        });
    }
};
