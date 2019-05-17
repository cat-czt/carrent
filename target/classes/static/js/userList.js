window.onload = function () {
    //渲染数据
    new Vue({
        el: "#wrapper",
        data: {
            userName: sessionStorage.getItem("account"),
            user:{},
            userList: [],
            logged: false,
            notLogged: true
        },
        methods: {
            toIndex: function () {
                window.location = "/route/toMain";
            },
            logout: function () {
                let data = request("GET", null, "/login/logout");
                if (data.status === 200) {
                    //清空登录信息
                    sessionStorage.clear();
                    window.location = "/route/toIndex";
                }
            },
            getUserList:function () {
                let data = request("GET", null, "/system/sys-user/userList");
                this.userList = data;
            },
            detail:function (d) {
                let id = d.id;
                let data = request("GET", null, "/system/sys-user/get/"+id);
                this.user = data;
            },
            updateUser:function () {
                let param = this.user;
                let data = request("POST", param, "/system/sys-user/edit");
                if(data.status == 200){
                    alert("修改成功！");
                    window.location.reload();
                }else{
                    alert(data.msg)
                }
            },
            del:function (d) {
                let id = d.id;
                let data = request("GET", null, "/system/sys-user/del/"+id);
                if(data.status == 200){
                    alert("删除成功！");
                    window.location.reload();
                }else{
                    alert(data.msg)
                }
            }
        },
        mounted: function () {

            this.getUserList();

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