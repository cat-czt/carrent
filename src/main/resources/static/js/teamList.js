window.onload = function () {
    //渲染数据
    new Vue({
        el: "#wrapper",
        data: {
            isAdd:true,
            isUpdate:false,
            title:"",
            userName: sessionStorage.getItem("account"),
            team:{},
            teamList: [],
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
            getTeamList:function () {
                let data = request("GET", null, "/main/team/list");
                this.teamList = data;
            },
            detail:function (d) {
                this.title = "编辑球队信息";
                this.isAdd = false;
                this.isUpdate = true;
                let id = d.id;
                let data = request("GET", null, "/main/team/get/"+id);
                this.team = data;
            },
            updateTeam:function () {
                let param = this.team;
                let data = request("POST", param, "/main/team/edit");
                if(data.status == 200){
                    alert("修改成功！");
                    window.location.reload();
                }else{
                    alert(data.msg)
                }
            },
            del:function (d) {
                let id = d.id;
                let data = request("GET", null, "/main/team/del/"+id);
                if(data.status == 200){
                    alert("删除成功！");
                    window.location.reload();
                }else{
                    alert(data.msg)
                }
            },
            addTeam:function () {
                this.title = "新建球队信息";
                this.isAdd = true;
                this.isUpdate = false;
            },
            saveTeam:function () {
                let data = this.team;
                let res = request("POST",data,"/main/team/edit");
                if(res.status == 200){
                    alert("添加成功！");
                    window.location.reload();
                }else{
                    alert(res.msg)
                }
            }
        },
        mounted: function () {

            this.getTeamList();

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