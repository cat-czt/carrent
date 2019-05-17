window.onload = function () {
    //渲染数据
    new Vue({
        el: "#wrapper",
        data: {
            isAdd:true,
            isUpdate:false,
            title:"",
            userName: sessionStorage.getItem("account"),
            race:{},
            raceList: [],
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
            getRaceList:function () {
                let data = request("GET", null, "/main/matchs/list");
                this.raceList = data;
            },
            detail:function (d) {
                this.title = "编辑赛事信息";
                this.isAdd = false;
                this.isUpdate = true;
                let id = d.id;
                let data = request("GET", null, "/main/matchs/get/"+id);
                this.race = data;
            },
            updateMatchs:function () {
                let param = this.race;
                let data = request("POST", param, "/main/matchs/edit");
                if(data.status == 200){
                    alert("修改成功！");
                    window.location.reload();
                }else{
                    alert(data.msg)
                }
            },
            del:function (d) {
                let id = d.id;
                let data = request("GET", null, "/main/matchs/del/"+id);
                if(data.status == 200){
                    alert("删除成功！");
                    window.location.reload();
                }else{
                    alert(data.msg)
                }
            },
            addMatches:function () {
                this.title = "新建赛事信息";
                this.isAdd = true;
                this.isUpdate = false;
            },
            saveMatchs:function () {
                let data = this.race;
                let res = request("POST",data,"/main/matchs/edit");
                if(res.status == 200){
                    alert("添加成功！");
                    window.location.reload();
                }else{
                    alert(res.msg)
                }
            }
        },
        mounted: function () {

            this.getRaceList();

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