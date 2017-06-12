var mongoose = require('mongoose'),
    encryption = require('../../utils/encryption.js');

module.exports.init = function () {
    var userSchema = mongoose.Schema({
        username: {type: String, required: true, unique: true},
        first_name: {type: String, required: true},
        last_name: {type: String, required: true},
        email : {type: String, required: true},
        hashPass: String,
        salt: String
    });

    userSchema.method({
        authenticate: function (password) {
            if (encryption.generateHashedPassword(this.salt, password) === this.hashPass) {
                return true;
            }
            else {
                return false;
            }
        }
    });

    var User = mongoose.model("User", userSchema);
};
