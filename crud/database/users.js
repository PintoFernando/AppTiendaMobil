const mongoose = require("./connect");
const COMPRADORSCHEMA = {
  username     : String,
  name         : String,
  email        : String,
  password     : String,
  registerDate : Date,
  sex          : String,
  age          : Number
}
const COMPRADORES = mongoose.model("users", COMPRADORSCHEMA);
module.exports = COMPRADORES;
