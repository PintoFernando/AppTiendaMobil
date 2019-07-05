const mongoose = require("./connect");
const USERSCHEMA = {
  username     : {type: String, unique: true},
  password     : {type: String},
  email        : String,
  registerDate : Date,
}
const USERS = mongoose.model("Users", USERSCHEMA);
module.exports = USERS;
