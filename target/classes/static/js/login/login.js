window.onload = function () {

}
//登录
function login() {
    let account = $("#account").val();
    let password = $("#password").val();
    let flag = validateParam(account,password);
    if(flag){
        $.ajax({
            type: "POST",
            url: "/login/login/"+account,
            data: {"password":password},
            dataType:"json",
            success: function (data) {
                //登陆成功 保token信息跳转至首页
                if(data.status === 400){
                    toastr.info(data.msg);
                }else{
                    toastr.info("登录成功!");
                    toIndex(data);
                }
            },
            error: function () {
                toastr.info("系统异常!");
            }
        });

    }
}

//注册
function register() {
    window.location = "/route/toRegist"
}

//校验参数
function validateParam(account,password) {
    if(account == null || account == ""){
        toastr.error("账号不得为空!");
        return false;
    }
    if(password == null || password == ""){
        toastr.error("密码不得为空!");
        return false;
    }
    return true;
}

//记录用户信息同时跳转到首页
function toIndex(data) {
    sessionStorage.setItem("account",data.data.account);
    sessionStorage.setItem("token",data.data.token);
    window.location = data.data.route;
}