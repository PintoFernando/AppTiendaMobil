var express = require('express');
var router = express.Router();
var COMPRADOR = require('../database/users');
/* GET home page. */
router.get('/', function(req, res, next) {
  res.status(200).json({
    msn: "GAAA"
  })
});
router.post('/comprador', async(req, res)=> {
  var params = req.body;
  params["registerDate"] = new Date();
  var compradores = new COMPRADOR(params);
  var result = await compradores.save();
  res.status(200).json(result);
});
router.get('/comprador', (req, res)=>{
  COMPRADOR.find({}, (err, docs) => {
    res.status(200).json(docs);
  });
});
module.exports = router;
