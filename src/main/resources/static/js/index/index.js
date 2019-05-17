window.onload = function () {
    //渲染数据
    new Vue({
        el: "#wrapper",
        data: {
            orderId:'',
            name:'',
            userPhone:'',
            createTime:'',
            userName: sessionStorage.getItem("account"),
            logged: false,
            notLogged: true,
            raceList: [],
            playerList: [],
            newsList: [],
            shootList: [],
            assistList: [],
            orderList:[],
            carList:[],
            recommendCar:[],
            commentList:[]
        },
        methods: {
            toIndex: function () {
                window.location = "/route/toIndex";
            },
            toIntroduction: function () {
                window.location = "/route/toIntroduction";
            },
            toReservastion: function () {
                window.location = "/route/toReservastion";
            },
            toCarInformation: function () {
                window.location = "/route/toCarInformation";
            },
            toMatchList: function () {
                window.location = "/route/toMatchList";
            },
            logout: function () {
                let data = request("GET", null, "/login/logout");
                if (data.status === 200) {
                    //清空登录信息
                    sessionStorage.clear();
                    window.location = "/route/toIndex";
                }
            },
            initData: function () {
                let data = request("GET", null, "/main/matchs/latest");
                this.raceList = data;
                this.playerList = request("GET", null, "/main/player/latest");
                this.newsList = request("GET", null, "/main/news/latest");
                this.shootList = request("GET", null, "/main/player/shootList");
                this.assistList = request("GET", null, "/main/player/assistList");
                this.orderList = request("GET", null, "/main/orders/list");
                this.carList = request("GET", null, "/main/car/list");
                this.recommendCar = request("GET", null, "/main/car/latest");
                this.commentList = request("GET", null, "/main/comment/latest");
            },
            searchByOrderId:function () {
                this.orderList = request("GET", null, "/main/orders/list?orderId="+this.orderId);
            },
            searchByUserName:function () {
                this.orderList = request("GET", null, "/main/orders/list?userName="+this.name);
            },
            searchByUserPhone:function () {
                this.orderList = request("GET", null, "/main/orders/list?userPhone="+this.userPhone);
            },
            searchByCreateTime:function () {
                this.orderList = request("GET", null, "/main/orders/list?userName="+this.createTime);
            }
        },
        mounted: function () {
            this.initData();
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
