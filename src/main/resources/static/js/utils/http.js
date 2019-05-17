//ajax 请求
function request(type,data,url) {
    let res = {};
    $.ajax({
        type: type,
        url: url,
        dataType:'json',
        contentType: "application/json", //必须有
        data: data == null ? null : JSON.stringify(data),
        async:false,
        beforeSend: function(request) {
            let token = sessionStorage.getItem("token") == null ? null : sessionStorage.getItem("token");
            // if(token != null && token != ""){
            request.setRequestHeader("token", token);
            // }else {
            //     alert("您已退出登陆，请重新登陆！");
            //     window.location = "/route/toLogin"
            // }
        },
        dataType:"json",
        success: function (data) {
            res = data;
        },
        error: function () {
            res =  "error";
        }
    });
    return res;
}
