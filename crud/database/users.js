const mongoose = require("./connect");
const USERSCHEMA = {
  name         : String,
  apellidop    : String,
  apellidom    : String,
  username     : String,
  password     : String,
  email        : String,
  registerDate : Date,
}
const USERS = mongoose.model("Users", USERSCHEMA);
module.exports = {model: USERS, schema: USERSCHEMA};
