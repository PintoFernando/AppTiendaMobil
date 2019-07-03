const mongoose = require('mongoose');
mongoose.connect("mongodb://182.18.0.2:27017/tiendamov");
module.exports = mongoose;
