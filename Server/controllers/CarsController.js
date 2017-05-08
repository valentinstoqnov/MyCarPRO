var cars = require('../data/cars');

module.exports = {
    create: function (req, res, next) {
        console.log("cretae")
        var car = req.body;
        car.carOwner = req.user.username;
        cars.create(car, function (err, car) {
            if (err) {
                res.status(400);
                return res.send({reason: err.toString()});
            }

            return res.send(car);
        });
    },

    getCars: function (req, res, next) {
        console.log(req.user)
        cars.find({"carOwner": req.user.username}, function (err, cars) {
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
        cars.findOne(req.params.id, function (err, car) {
            car.refuelings
                .push(fuel);
            car.save(function (err) {
                console.log(err)
            });
            console.log(fuel)
            res.send(fuel);
        })
    },
    putEditFuel: function (req, res, next) {
        var fuel = req.body;
        cars.findOne(req.params.id, function (err, car) {

            car.refuelings
                .pop();
            car.refuelings.push(fuel);
            car.save();
            console.log(fuel)

            res.send(fuel);
        })
    },
    postAddService: function (req, res, next) {
        var service = req.body;
        console.log(service);
        cars.findOne(req.params.id, function (err, car) {
            car.services
                .push(service);
            car.save();
            console.log(car);

            res.send(service);

        })
    },
    putEditService: function (req, res, next) {
        var service = req.body;
        console.log(service);
        cars.findOne(req.params.id, function (err, car) {
            console.log(car)
            car.services
                .pop();
            car.services
                .push(service);
            car.save();

            res.send(service);

        })
    },
    postAddInsurance: function (req, res, next) {
        var insurence = req.body;

        cars.findOne(req.params.id, function (err, car) {
            car.insurances.push(insurence);
            car.save();

            res.send(insurence);
        })
    },
    putEditInsurance: function (req, res, next) {
        var insurance = req.body;

        cars.findOne(req.params.id, function (err, car) {
            car.insurances
                .pop();
            car.insurances
                .push(insurance);
            res.send(insurance);
            car.save();
        })
    },
    delete: function (req, res, next) {
        cars.delete(req.params.id);
        res.send(req.params.id);
    },
    getCar: function (req, res, next) {
        cars.findOne(req.params.id, function (err, car) {
            if (car){
                res.send(car)
            }else {
                res.send({"reason" : "Car no found!!!!"})
            }

        })
    }
};
