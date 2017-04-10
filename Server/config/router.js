var controllers = require('../controllers/controllers'),
    auth = require('./auth');

module.exports = function functionName(app) {
    app.get('/', function(req, res, next) {
        res.send("HELLO");
    });

    app.post('/users/register', controllers.users.postRegister);
    app.post('/users/login', auth.login);

    app.get('/cars/get', controllers.cars.getCars);
    app.post('/cars/create', controllers.cars.create);
};
