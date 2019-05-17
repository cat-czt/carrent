window.onload = function () {
    //渲染数据
    new Vue({
        el: "#wrapper",
        data: {
            isAdd: true,
            isUpdate: false,
            title: "",
            userName: sessionStorage.getItem("account"),
            location: {},
            locationList: [],
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
            getUserList: function () {
                let data = request("GET", null, "/main/location/list");
                this.locationList = data;
            },
            detail: function (d) {
                this.title = "Edit Location Information";
                this.isAdd = false;
                this.isUpdate = true;
                let id = d.locationId;
                let data = request("GET", null, "/main/location/get/" + id);
                this.location = data;
            },
            updateLocation: function () {
                let param = this.location;
                let data = request("POST", param, "/main/location/edit");
                if (data.status == 200) {
                    alert("Successfully Modified！");
                    window.location.reload();
                } else {
                    alert(data.msg)
                }
            },
            del: function (d) {
                let id = d.locationId;
                let data = request("GET", null, "/system/sys-user/del/" + id);
                if (data.status == 200) {
                    alert("Successfully Deleted！");
                    window.location.reload();
                } else {
                    alert(data.msg)
                }
            },
            addLocation: function () {
                this.title = "Add Location Information";
                this.isAdd = true;
                this.isUpdate = false;
            },
            saveLocation: function () {
                let data = this.location;
                let res = request("POST", data, "/main/location/edit");
                if (res.status == 200) {
                    alert("Successfully Added！");
                    window.location.reload();
                } else {
                    alert(res.msg)
                }
            },
            showCars: function (d) {
                sessionStorage.setItem("locationId", d.locationId);
                sessionStorage.setItem("locationName", d.locationName);
                window.location = "/route/toCarLocationList"
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