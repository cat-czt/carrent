let sysUser = {
    'id':null,
    'userName':'',
    'account':'',
    'email':'',
    'mobile':'',
    'password':'',
    'createDate':null,
    'createUser':null,
    'updateDate':null,
    'updateUser':null
};
//注册
function register() {
    let flag = validateParam();
    if(flag){
        $.ajax({
            type: "POST",
            url: "/system/sys-user/register",
            data: JSON.stringify(sysUser),
            dataType:"json",
            contentType: "application/json", //必须有
            success: function (data) {
                //注册成功
                if(data.status === 400){
                    toastr.info(data.msg);
                }else{
                    toastr.info("注册成功!");
                    toLogin();
                }
            },
            error: function () {
                toastr.info("系统异常!");
            }
        });

    }
}

//校验参数
function validateParam() {
    let userName = $("#userName").val();
    let account = $("#account").val();
    let email = $("#email").val();
    let mobile = $("#mobile").val();
    let password = $("#password").val();
    let repeatPassword = $("#repeatPassword").val();
    if(userName == null || userName == ""){
        toastr.error("用户名不得为空!");
        return false;
    }
    if(account == null || account == ""){
        toastr.error("账号不得为空!");
        return false;
    }
    if(email == null || email == ""){
        toastr.error("邮箱不得为空!");
        return false;
    }
    if(mobile == null || mobile == ""){
        toastr.error("联系电话不得为空!");
        return false;
    }
    if(password == null || password == ""){
        toastr.error("密码不得为空!");
        return false;
    }
    if(repeatPassword == null || repeatPassword == ""){
        toastr.error("请再次确认密码!");
        return false;
    }
    if(password != repeatPassword){
        toastr.error("请确保与第一次输入密码一致!");
        return false;
    }
    sysUser.userName = userName;
    sysUser.account = account;
    sysUser.email = email;
    sysUser.mobile = mobile;
    sysUser.password = password;
    return true;
}

//记录用户信息同时跳转到登录界面
function toLogin() {
    window.location = "/route/toLogin";
}