package com.qc.information.index.controller;

import com.qc.information.config.AuthPassport;
import com.qc.information.config.BaseController;
import com.qc.information.system.entity.SysUser;
import com.qc.information.system.entity.SysUserQuery;
import com.qc.information.system.entity.UserLogin;
import com.qc.information.system.service.ISysUserService;
import com.qc.information.utils.Md5Encrypt;
import com.qc.information.utils.ResultData;
import com.qc.information.utils.ResultUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;


/**
 * @Authour czt
 * @Date 9:49
 **/
@Controller
@RequestMapping(value = "/login")
public class LoginController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;

    //region  登陆
    @ApiOperation("登陆接口")
    @PostMapping("/login/{account}")
    @AuthPassport(false)
    @ResponseBody
    public ResultData<UserLogin> login(@PathVariable("account") String account, String password) {
        UserLogin userLogin = new UserLogin();
        SysUser user = new SysUser();
        user.setAccount(account);
        user.setPassword(Md5Encrypt.encrypt(password));
        SysUserQuery userQuery = sysUserService.login(user);
        if (!ObjectUtils.isEmpty(userQuery)) {
            //设置登陆token
            String token = this.setUserLogin(userQuery);
            BeanUtils.copyProperties(userQuery, userLogin);
            if(userQuery.getRoleName().equals("系统管理员")){
                userLogin.setRoute("/route/toHome");
            }else{
                userLogin.setRoute("/route/toIndex");
            }
            userLogin.setToken(token);
        } else {
            return ResultUtils.error("用户名密码错误！");
        }
        ResultData<UserLogin> res = new ResultData<>();
        res.setData(userLogin);
        return res;
    }
    //endregion

    //region  logOut
    @ApiOperation("logOut登录，清空token")
    @GetMapping("/logout")
    @ResponseBody
    public ResultData loginOut() {
        this.refreshToken(-1);
        return ResultUtils.success();
    }
    //endregion

}
