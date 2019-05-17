window.onload = function () {
    //渲染数据
    new Vue({
        el: "#wrapper",
        data: {
            isAdd:true,
            isUpdate:false,
            title:"",
            userName: sessionStorage.getItem("account"),
            news:{},
            newList: [],
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
            getNewsList:function () {
                let data = request("GET", null, "/main/news/newsList");
                this.newList = data;
            },
            detail:function (d) {
                this.title = "编辑新闻";
                this.isAdd = false;
                this.isUpdate = true;
                let id = d.id;
                let data = request("GET", null, "/main/news/get/"+id);
                this.news = data;
            },
            updateNews:function () {
                let param = this.news;
                let data = request("POST", param, "/main/news/edit");
                if(data.status == 200){
                    alert("修改成功！");
                    window.location.reload();
                }else{
                    alert(data.msg)
                }
            },
            del:function (d) {
                let id = d.id;
                let data = request("GET", null, "/main/news/del/"+id);
                if(data.status == 200){
                    alert("删除成功！");
                    window.location.reload();
                }else{
                    alert(data.msg)
                }
            },
            addNews:function () {
                this.title = "新建新闻";
                this.isAdd = true;
                this.isUpdate = false;
            },
            saveNews:function () {
                let data = this.news;
                let res = request("POST",data,"/main/news/edit");
                if(res.status == 200){
                    alert("添加成功！");
                    window.location.reload();
                }else{
                    alert(res.msg)
                }
            }
        },
        mounted: function () {

            this.getNewsList();

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