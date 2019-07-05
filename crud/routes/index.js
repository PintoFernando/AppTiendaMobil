var express = require('express');
var router = express.Router();
var crypto = require('crypto');
var USER = require('../database/users');

router.post('/registeruser', async(req, res) => {
  var params = req.body;
  params["registerDate"] = new Date();
  var compradores = new USER(params);
  var result = await compradores.save();
  res.status(200).json(result);
});

router.post('/login', (req, res) => {
  var params = req.body;
  if(USER.findOne({username : params["username"]}, params, (err, docs) => {
    res.status(200).json(docs)
  }) != null){

  }
});

/*router.get('/', function(req, res, next) {
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
  var params=req.query;
  var limit=3;
  if(params.limit!=null){
    limit=parseInt(params.limit);
  }
  var order=-1;
  if(params.order != null){
    if(params.order == "desc"){
      order = -1;
    }
    else{
      if(params.order == "asc"){
        order = 1;
      }
    }
    order = params.order;
  }
  var skip = 0;
  if(params.skip != null){
    skip = parseInt(params.skip);
  }
  COMPRADOR.find({}).limit(limit).sort({_id : order}).skip(skip).exec((err, docs) => {
    res.status(200).json(docs);
  });
  //COMPRADOR.find({}, (err, docs) => {
    //res.status(200).json(docs);
  //});
});
router.patch("/comprador", (req, res) => {
  if(req.query.id == null){
    res.status(300).json({
    });
    msn : "Error no existe id"
    return;
  }
  var id = req.query.id;
  var params = req.body;
  COMPRADOR.findOneAndUpdate({_id : id}, params, (err, docs) => {
    res.status(200).json(docs);
  });
});
router.delete("/comprador", async(req, res) => {
  if(req.query.id == null){
    res.status(300).json({
      msn : "Error id no identificado"
    });
    return;
  }
  var r = await COMPRADOR.remove({_id : req.query.id});
  res.status(300).json(r);
});*/
module.exports = router;
