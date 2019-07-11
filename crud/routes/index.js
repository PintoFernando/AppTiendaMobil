var express = require('express');
var router = express.Router();
var crypto = require('crypto');
var user = require('../database/users');
var valid = require('../utils/valid');
var USERSCHEMA = user.schema;
var USER = user.model;
var jwt = require('jsonwebtoken');
router.get('/user', (req, res)=>{
  var params=req.query;
  var limit=30;
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
  USER.find({}).limit(limit).sort({_id : order}).skip(skip).exec((err, docs) => {
    res.status(200).json(docs);
  });
  //COMPRADOR.find({}, (err, docs) => {
    //res.status(200).json(docs);
  //});
});

router.post('/registeruser', async(req, res, next) => {
  var params = req.body;
  var docs = await USER.find({email : params.email});
  if(docs.length >= 1){
    res.status(300).json({
      msn : "El correo ya se encuentra registrado"
    });
    return;
  }
  var docs = await USER.find({username : params.username});
  if(docs.length >= 1){
    res.status(300).json({
      msn : "El nombre de usuario ya esta en uso GAAA"
    });
    return;
  }
  if(!valid.checkParams({
        "name": String,
        "apellidop": String,
        "apellidom": String,
        "username": String,
        "password": String,
        "email": String
    }, params)){
    res.status(300).json({
      msn : "Parametros Incorrectos"
    });
    return;
  }
  if(!valid.checkPassword(params.password)){
    res.status(300).json({
      msn : "Password Invalido"
    });
  }
  if(!valid.checkEmail(params.email)){
    res.status(300).json({
      msn : "Email Invalido"
    });
  }
  params["registerDate"] = new Date();
  var compradores = new USER(params);
  var result = await compradores.save();
  res.status(200).json(result);
});

router.post('/login', async(req, res, next) => {
  var params = req.body;
  if(!valid.checkParams({"username" : String,
                         "password" : String}, params)){
    res.status(300).json({
      msn : "Valores no Encontrados"
    });
    return;
  }
  var docs = await USER.find({username : params.username, password : params.password});
  if(docs.length == 0){
    res.status(300).json({
      msn : "Usuario o contrasena incorrectos"
    });
    return;
  }
  if(docs.length == 1){
    jwt.sign({username : params.username, password : params.password}, "password", (err,token)=>{
      if(err){
        res.status(300).json({
          msn : "Erro de JWT"
        });
        return;
      }
      res.status(200).json({
        "token" : token
      });
    });
    return;
  }
});


/*router.get('/user', (req, res)=>{
  var params=req.query;
  var limit=30;
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
  USER.find({}).limit(limit).sort({_id : order}).skip(skip).exec((err, docs) => {
    res.status(200).json(docs);
  });
  //COMPRADOR.find({}, (err, docs) => {
    //res.status(200).json(docs);
  //});
});*/

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
