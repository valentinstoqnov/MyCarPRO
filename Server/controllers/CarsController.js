var cars = require('../data/cars');

module.exports = {
    create: function (req, res, next) {

        var car = req.body;
        car.carOwner = req.body.carOwner;
        cars.create(car, function (err, room) {
            if (err) {
                res.status(400);
                return res.send({reason: err.toString()});
            }

            return res.send(room);
        });
    },

    getCars: function (req, res, next) {
        cars.find({"carOwner": req.query.user}, function (err, cars) {
            if (err) {
                res.status(400);
                console.log(err)
                return res.send({reason: "Bad car!"});
            }

            return res.send(cars);
        });
    },
    postAddFuil: function (req, res, next) {
        var fuel = req.body;
        console.log()
        cars.findOne(req.body._id, function (err, car) {
            car.fueling
                .push(fuel);
            car.save();
            console.log(car);

            res.send(fuel);
        })
    },
    putEditFuel: function (req, res, next) {
        var fuel = req.body;
        cars.findOne(req.body._id, function (err, car) {
            car.fueling
                .pop();
            car.fueling.push(fuel);
            car.save();

            res.send(fuel);
        })
    },
    postAddService: function (req, res, next) {
        var service = req.body;
        cars.findOne(req.body._id, function (err, car) {
            car.services
                .push(service);
            car.save();

            res.send(service);

        })
    },
    putEditService: function (req, res, next) {
        var service = req.body;
        console.log(service)
        cars.findOne(req.body._id, function (err, car) {
            console.log(car)
            car.services
                .pop();
            car.services
                .push(service);
            car.save();

            res.send(service);

        })
    }
};
