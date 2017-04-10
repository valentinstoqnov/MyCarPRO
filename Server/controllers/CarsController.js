var cars = require('../data/cars');

module.exports = {
    create: function (req, res, next) {
        if (!req.body.name) {
            res.status(400);
            return res.send({reason: "Bad car!"});
        }
        var car = req.body;
        car.carOwner = req.body.username;
        cars.create(car, function (err, room) {
            if (err) {
                res.status(400);
                return res.send({reason: err.toString()});
            }

            return res.send(room);
        });
    },

    getCars: function (req, res, next) {
        cars.find({"carOwner":req.query.carOwner}, function (err, cars) {
            if (err) {
                res.status(400);
                return res.send({reason: "Bad car!"});
            }

            return res.send(cars);
        });
    },
    postAddFuil:function (req, res, next) {
        cars.findOne(req.body.username,function (err,car) {
            car.fueling
                .push(req.body.fuel)
                .save();

            res.send(req.body.fuel);
        })
    },
    postEditFuel: function (req,res,next) {
        var fuel = req.body.fuel;
        cars.findOne(req.body.username,function (err,car) {
            car.fueling.pop()
                .push(fuel)
                .save();

            res.send(fuel);

        })
    }
};
