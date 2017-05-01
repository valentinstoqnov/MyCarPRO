var passport = require('passport');
var HashMap = require('hashmap');
var hashMap = new HashMap();
const uuidV1 = require('uuid/v1'),
    User = require('mongoose').model('User');
var user1;


module.exports = {
    login: function (req, res, next) {
        var token = uuidV1();
        User.findOne({"username": req.body.username}, function (err, user) {
            user1 = user;
            hashMap.set(token, user1);
            res.setHeader("token", token);
            res.send(user);
        });
    },
    logout: function (req, res, next) {
        hashMap.set(req.headers.token, undefined);
        //hashMap.set(req.header.token , undefined);
        res.status(200);
        res.send();
    },
    isAuthenticated: function (req, res, next) {
        console.log(req.headers.token)
        if (!hashMap.get(req.headers.token)) {
            res.status(400);
            console.log("0")
            res.send({reason: "User is not logged in."});
        }
        else {
            console.log("1")
            req.user = hashMap.get(req.headers.token)
            next();
        }
    }
};
