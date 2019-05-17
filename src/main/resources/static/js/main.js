
window.onload = function () {
    //渲染数据
    new Vue({
        el: "#wrapper",
        data: {
            userName: sessionStorage.getItem("account"),
            logged: false,
            notLogged: true
        },
        methods: {
            toIndex: function () {
                window.location = "/route/toMain";
            },
            logout: function () {
                Ewin.confirm({message: "确认要退出嘛？"}).on(function (e) {
                    if (e) {
                        let data = request("GET", null, "/login/logout");
                        if (data.status === 200) {
                            //清空登录信息
                            sessionStorage.clear();
                            window.location = "/route/toIndex";
                        }
                    }
                });
            }
        },
        mounted: function () {

            if (sessionStorage.getItem("token") != "" && sessionStorage.getItem("token") != null) {
                this.logged = true;
                this.notLogged = false;
            } else {
                this.logged = false;
                this.notLogged = true;
            }
        }
    });
}