window.onload = function () {
    //渲染数据
    new Vue({
        el: "#wrapper",
        data: {
            isAdd:true,
            isUpdate:false,
            title:"",
            userName: sessionStorage.getItem("account"),
            car:{},
            carList: [],
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
            getCarList:function () {
                let data = request("GET", null, "/main/car/list");
                this.carList = data;
            },
            detail:function (d) {
                this.title = "Edit Car Information";
                this.isAdd = false;
                this.isUpdate = true;
                let id = d.cid;
                let data = request("GET", null, "/main/car/get/"+id);
                this.car = data;
            },
            updateCar:function () {
                let param = this.car;
                let data = request("POST", param, "/main/car/edit");
                if(data.status == 200){
                    alert("Successfully Modified！");
                    window.location.reload();
                }else{
                    alert(data.msg)
                }
            },
            del:function (d) {
                let id = d.cid;
                let data = request("GET", null, "/main/car/del/"+id);
                if(data.status == 200){
                    alert("Successfully Deleted！");
                    window.location.reload();
                }else{
                    alert(data.msg)
                }
            },
            addCar:function () {
                this.title = "Add Car Information";
                this.isAdd = true;
                this.isUpdate = false;
            },
            saveCar:function () {
                let data = this.car;
                let file = $("#carImg").val();
                let res = request("POST",data,"/main/car/edit");
                if(res.status == 200){
                    alert("Successfully Added！");
                    window.location.reload();
                }else{
                    alert(res.msg)
                }
            }
        },
        mounted: function () {
            this.getCarList();
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