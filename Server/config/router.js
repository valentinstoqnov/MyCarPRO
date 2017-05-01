var controllers = require('../controllers/controllers'),
    auth = require('./auth');

module.exports = function functionName(app) {
    app.get('/', function (req, res, next) {
        res.send("HELLO");
    });

    app.post('/users', controllers.users.postRegister);
    app.post('/users/login', auth.login);
    app.post('/users/logout', auth.logout);

    app.get('/cars', auth.isAuthenticated, controllers.cars.getCars);
    app.post('/cars', auth.isAuthenticated, controllers.cars.create);
    app.delete('/cars/:id', auth.isAuthenticated, controllers.cars.delete);


    app.post('/cars/fuil/:id', auth.isAuthenticated, controllers.cars.postAddFuil);
    app.put('/cars/fuil/:id', auth.isAuthenticated, controllers.cars.putEditFuel);

    app.post('/cars/services/:id', auth.isAuthenticated, controllers.cars.postAddService);
    app.put('/cars/services/:id', auth.isAuthenticated, controllers.cars.putEditService);

    app.post('/cars/insurance/:id', auth.isAuthenticated, controllers.cars.postAddInsurance);
    app.put('/cars/insurance/:id', auth.isAuthenticated, controllers.cars.putEditInsurance)


};
