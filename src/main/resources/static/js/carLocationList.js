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
            locationCar:{},
            carList: [],
            locationList: [],
            chooseCarList:[],
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
                let data = request("GET", null, "/main/car/getByLocationId/"+sessionStorage.getItem("locationId"));
                this.carList = data;
                this.locationList = request("GET", null, "/main/location/list");
                this.chooseCarList = request("GET", null, "/main/car/list");
            },
            detail:function (d) {
                this.title = "Edit Car Location Information";
                this.isAdd = false;
                this.isUpdate = true;
                let id = d.locationId;
                let data = request("GET", null, "/main/locationCar/get/"+id);
                this.locationCar = data;
            },
            updateCarLocation:function () {
                let param = this.locationCar;
                let data = request("POST", param, "/main/locationCar/edit");
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
                    this.getCarList();
                }else{
                    alert(data.msg)
                }
            },
            addCarLocation:function () {
                this.title = "Add Car Location Information";
                this.isAdd = true;
                this.isUpdate = false;
            },
            saveCarLocation:function () {
                let data = this.locationCar;
                let res = request("POST",data,"/main/locationCar/edit");
                if(res.status == 200){
                    alert("Successfully Added！");
                    this.getCarList();
                }else{
                    alert(res.msg)
                }
            }
        },
        mounted: function () {
            this.getCarList();
            this.locationName = sessionStorage.getItem("locationName");
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