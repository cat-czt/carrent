
window.onload = function () {
    //渲染数据
    new Vue({
        el: "#wrapper",
        data: {
            userName:sessionStorage.getItem("account"),
            matchList: [],
            logged: false,
            notLogged: true,
            teamList:[],
            newsList:[],
            playerList:[]
        },
        methods: {
            toIndex: function () {
                window.location = "/route/toIndex";
            },
            toNewsIndexList: function () {
                window.location = "/route/toNewsIndexList";
            },
            toTeamIndexList: function () {
                window.location = "/route/toTeamIndexList";
            },
            toMatchList: function () {
                window.location = "/route/toMatchList";
            },
            toPlayerIndexList: function () {
                window.location = "/route/toPlayerIndexList";
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
            },
            init: function () {
                let data = request("GET", null, "/main/matchs/list");
                this.matchList = data;
                this.teamList = request("GET", null, "/main/team/list");
                this.newsList = request("GET", null, "/main/news/newsList");
                this.playerList = request("GET", null, "/main/player/list");
            },
            follow:function (item) {
                // let data = request("GET",null,"/main/")
            }
        },
        mounted: function () {
            this.init();
            console.log("data" + this.matchList);
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