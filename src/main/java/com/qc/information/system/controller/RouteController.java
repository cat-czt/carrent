package com.qc.information.system.controller;

import com.qc.information.config.AuthPassport;
import com.qc.information.config.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Authour czt
 * @Date 上午9:28
 **/
@Api("系统路由")
@Controller
@RequestMapping("/route")
public class RouteController extends BaseController {

    //region
    @ApiOperation(value = "登陆界面")
    @AuthPassport(false)
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }
    //endregion

    //region
    @ApiOperation(value = "首页")
    @AuthPassport(false)
    @RequestMapping("/toIndex")
    public String toIndex(){
        return "index";
    }
    //endregion

    //region
    @ApiOperation(value = "后台主页")
    @AuthPassport(false)
    @RequestMapping("/toHome")
    public String toMain(){
        return "home";
    }
    //endregion

    //region
    @ApiOperation(value = "用户列表")
    @AuthPassport(false)
    @RequestMapping("/toUserList")
    public String toUserList(){
        return "userList";
    }
    //endregion

    //region
    @ApiOperation(value = "注册")
    @AuthPassport(false)
    @RequestMapping("/toRegist")
    public String toRegist(){
        return "register";
    }
    //endregion

    //region
    @AuthPassport(false)
    @RequestMapping("/toIntroduction")
    public String toIntroduction(){
        return "introduction";
    }
    //endregion

    //region
    @AuthPassport(false)
    @RequestMapping("/toReservastion")
    public String toReservastion(){
        return "reservastion";
    }
    //endregion

    //region
    @AuthPassport(false)
    @RequestMapping("/toCarInformation")
    public String toCarInformation(){
        return "carInformation";
    }
    //endregion

    //region
    @AuthPassport(false)
    @RequestMapping("/toVehicleList")
    public String toVehicleList(){
        return "vehicleList";
    }
    //endregion

    //region
    @AuthPassport(false)
    @RequestMapping("/toLocationList")
    public String toLocationList(){
        return "locationList";
    }
    //endregion

    //region
    @AuthPassport(false)
    @RequestMapping("/toCustomerList")
    public String toCustomerList(){
        return "customerList";
    }
    //endregion

    //region
    @AuthPassport(false)
    @RequestMapping("/toCarLocationList")
    public String toCarLocationList(){
        return "carLocationList";
    }
    //endregion
}
