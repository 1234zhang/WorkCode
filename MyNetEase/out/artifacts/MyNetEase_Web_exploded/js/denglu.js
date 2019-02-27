$(".singIn").click(function(){
    $("#signIn").css("display","inline")
})
$(".guan").click(function(){
    $("#signIn").css("display","none")
})
//登录主界面


$(".psignIn_btn").click(function(){
    
    $("#signIn_phone").css("display","inline")
})
$(".guan_phone").click(function(){
    $("#signIn_phone").css("display","none")
})







// 手机号登录


$(".signIn_btn").click(function(){
    
    $("#signIn_normal").css("display","inline")
})
$(".guan_normal").click(function(){
    $("#signIn_normal").css("display","none")
})
$(".signIn_btn_normal").click(function(){    
    if ($(".normalNumber").val() == "" && $(".normalPassword").val() == ""){
        $(".normalNumber_tips").css("color","red")
        $(".normalPassword_tips").css("color","red")
        return false
    }
    else if($(".normalNumber").val() == ""  ){
        $(".normalNumber_tips").css("color","red")
        return false
    }
    else if ($(".normalPassword").val() == "" ){
        $(".normalPassword_tips").css("color","red")
        return false
    }
    
    else {
    return true}
})
//账号密码登录
var reg = /^[0-9a-zA-Z]+$/


$(".signUp_btn").click(function(){
    
    $("#signUp_normal").css("display","inline")
})
$(".guan_normal").click(function(){
    $("#signUp_normal").css("display","none")
})



$(".signUpNumber").focus(function(){
    $(".signUpNumber").val("")
    $(".signUpNumber_tip").remove()
    $(".signUpNumber").removeClass("addtest110")
}).blur(function(){
    if(reg.test($(".signUpNumber").val())){
    }
    else {
        $(".signUpNumber").after("<span class='signUpNumber_tip'>您输入的账号有误</span>").addClass("addtest110")
    }
})

$(".signUpId").focus(function(){
    $(".signUpId").val("")
    $(".signUpNumber_tip").remove()
    $(".signUpId").removeClass("addtest110")
}).blur(function(){
    if(reg.test($(".signUpId").val())){
    }
    else {
        $(".signUpId").after("<span class='signUpNumber_tip'>您输入的昵称有误</span>").addClass("addtest110")
    }
})



$(".signUpPassword_1").focus(function test(){
    $(".signUpPassword_1").val("")
    $(".signUpPassword_1_tip").remove()
    $(".signUpPassword_1").removeClass("addtest108")
}).blur(function(){
    if(($(".signUpPassword_1").val().length > 9 || $(".signUpPassword_1").val().length <6) && $(".signUpPassword_1_tip").val()==undefined ){
        $(".signUpPassword_1").after("<span class='signUpPassword_1_tip'>您输入的密码有误</span>").addClass("addtest108")
    }
})


$(".signUpPassword_2").focus(function(){
    $(".signUpPassword_2").val("")
    $(".signUpPassword_2_tip").remove()
    $(".signUpPassword_2").removeClass("addtest98")
}).blur(function(){
    if ($(".signUpPassword_2").val() == $(".signUpPassword_1").val() ){
    }
    else {
        $(".signUpPassword_2").after("<span class='signUpPassword_2_tip'>两次输入密码不同</span>").addClass("addtest98")
    }
})




$(".signUp_btn_normal").click(function(){
  if($(".signUpPassword_1_tip").val()!=undefined ||$(".signUpPassword_2_tip").val()!=undefined ||$(".signUpNumber_tip").val()!=undefined){
      alert("no")
      return false
  }
  else if($(".signUpNumber").val()=="账号仅由字母数字组成"||$(".signUpPassword_2").val() =="密码应为6-9位"||$(".signUpPassword_1").val()=="请重复密码"){
      alert("dasdad")
      return false
  }
  else {
    var params = {"username":$(".signUpId").val(), "password":$(".signUpPassword_2").val(), "id" :$(".signUpNumber").val()}; 
    $.ajax({
        url : "http://brandonxcc.top:8080/MyNetEase/login/Register",
        data : params,
        type : "post",
        dateType:"jsonp",
        jsonp:"callback",
        success : function(data) {
            
            alert("成功")
        },
        error : function() {
            alert("注册失败");
        }
    });

  }

})


// 登录部分
$(".signIn_btn_normal").click(function(){
    if($(".normalNumber").val() == "" ||$(".normalPassword").val() =="" ){
        alert("no")
        return false
    }

    
    else {
      var params = {"id":$(".normalNumber").val(), "password":$(".normalPassword").val()}; 
      $.ajax({
          url : "http://brandonxcc.top:8080/MyNetEase/login/Login",
          data : params,
          type : "post",
          dateType:"jsonp",
          jsonp:"callback",
          success : function(data) {
              
              alert(data)
          },
          error : function() {
              alert("登录失败");
          }
      });
  
    }
  
  })
