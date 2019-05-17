window.onload = function () {
    //渲染数据
    new Vue({
        el: "#wrapper",
        data: {
            isAdd:true,
            isUpdate:false,
            title:"",
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
                this.title = "Edit User Information";
                this.isAdd = false;
                this.isUpdate = true;
                let id = d.id;
                let data = request("GET", null, "/system/sys-user/get/"+id);
                this.user = data;
            },
            updateUser:function () {
                let param = this.user;
                let data = request("POST", param, "/system/sys-user/edit");
                if(data.status == 200){
                    alert("Successfully Modified！");
                    window.location.reload();
                }else{
                    alert(data.msg)
                }
            },
            del:function (d) {
                let id = d.id;
                let data = request("GET", null, "/system/sys-user/del/"+id);
                if(data.status == 200){
                    alert("Successfully Deleted！");
                    window.location.reload();
                }else{
                    alert(data.msg)
                }
            },
            addUser:function () {
                this.title = "Add User Information";
                this.isAdd = true;
                this.isUpdate = false;
            },
            saveUser:function () {
                let data = this.user;
                let res = request("POST",data,"/system/sys-user/edit");
                if(res.status == 200){
                    alert("Successfully Added！");
                    window.location.reload();
                }else{
                    alert(res.msg)
                }
            },
            resetCredit:function (d) {
                let data = request("GET", null, "/system/sys-user/resetCredit/"+d.id);
                if(data.status == 200){
                    alert("Successfully Reset！");
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