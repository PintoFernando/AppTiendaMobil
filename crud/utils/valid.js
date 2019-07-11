var valid = {
  checkParams : function(refobj, evalueobj){
    if(Object.keys(refobj).sort().toString() == Object.keys(evalueobj).sort().toString())return true;
    return false;
  },
  checkEmail : function(email){
    var exp = /^\w{1,}@\w{1,}[.]\w{2,3}$/g;
    if(email.match(exp) == null)return false;
    return true;
  },
  checkPassword : function(password){
    var chk = /^(?=\w*\d)(?=\w*[A-Z])(?=\w*[a-z])\S{8,16}$/g;
    if(password.match(chk) == null) return false;
    return true;
  }
};

module.exports = valid;
