package com.qc.information.config;

import com.alibaba.fastjson.JSONObject;
import com.qc.information.system.entity.SysUser;
import com.qc.information.system.entity.SysUserQuery;
import com.qc.information.utils.JwtUtil;
import com.qc.information.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: czt
 * @Date: 18-11-12 上午10:37
 */
public class BaseController {

    @Autowired
    private HttpServletRequest request;

    /**
     * 获取登录用户信息
     *
     * @return
     */
    public SysUserQuery getUserLogin() {
        String token = request.getHeader("Token");
        if (StringUtil.isNullOrEmpty(token)) {
            token = request.getParameter("Token");
        }
        JSONObject json = JwtUtil.getTokenContent(token);
        SysUserQuery userInfo = JSONObject.parseObject(json.toString(), SysUserQuery.class);
        return userInfo;
    }

    /**
     * 设置登陆token 有效性24H
     * @param sysUser
     * @return
     */
    public String setUserLogin(SysUserQuery sysUser) {
        sysUser.setPassword("password");
        String token = "";
        String s = sysUser.toString();
        token = JwtUtil.generateToken(JSONObject.parseObject(sysUser.toString()), 60 * 24);
        return token;
    }

    /**
     * 刷新token
     * @param expireTime
     * @return
     */
    public String refreshToken(int expireTime) {
        String token = request.getHeader("token");
        return JwtUtil.refreshToken(token, expireTime);
    }
}
