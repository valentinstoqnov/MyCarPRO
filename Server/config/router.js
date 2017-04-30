var controllers = require('../controllers/controllers'),
    auth = require('./auth');

module.exports = function functionName(app) {
    app.get('/', function(req, res, next) {
        res.send("HELLO");
    });

    app.post('/users', controllers.users.postRegister);
    app.post('/users/login', auth.login);

    app.get('/cars/:user', controllers.cars.getCars);
    app.post('/cars/:carOwner', controllers.cars.create);

    app.post('/cars/fuil', controllers.cars.postAddFuil);
    app.put('/cars/fuil', controllers.cars.putEditFuel);

    app.post('/cars/services' , controllers.cars.postAddService);
    app.put('/cars/services' , controllers.cars.putEditService);

    app.post('/cars/insurance' , controllers.cars.postAddInsurance);
    app.put('/cars/insurance' , controllers.cars.putEditInsurance)




};
