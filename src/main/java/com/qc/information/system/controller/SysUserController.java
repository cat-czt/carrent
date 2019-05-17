package com.qc.information.system.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.qc.information.config.AuthPassport;
import com.qc.information.system.entity.SysUser;
import com.qc.information.system.service.ISysUserService;
import com.qc.information.utils.ResultData;
import com.qc.information.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.qc.information.config.BaseController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author czt
 * @since 2019-03-28
 */
@RestController
@RequestMapping("/system/sys-user")
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserService userService;

    @PostMapping("/register")
    @AuthPassport(false)
    @ResponseBody
    public ResultData register(@RequestBody SysUser sysUser){
        return userService.register(sysUser);
    }

    @PostMapping("/edit")
    @AuthPassport(false)
    @ResponseBody
    public ResultData edit(@RequestBody SysUser sysUser){
        return userService.edit(sysUser);
    }


    @GetMapping("/userList")
    @ResponseBody
    public List<SysUser> userList(){
        Wrapper<SysUser> wrapper = new EntityWrapper<>();
        return userService.selectList(wrapper);
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public SysUser get(@PathVariable("id") Integer id){
        Wrapper<SysUser> wrapper = new EntityWrapper<>();
        wrapper.eq("id",id);
        return userService.selectOne(wrapper);
    }

    @GetMapping("/resetCredit/{id}")
    @ResponseBody
    public ResultData resetCredit(@PathVariable("id") Integer id){
        return userService.resetCredit(id);
    }

    @GetMapping("/del/{id}")
    @ResponseBody
    @Transactional
    public ResultData del(@PathVariable("id") Integer id){
        Wrapper<SysUser> wrapper = new EntityWrapper<>();
        wrapper.eq("id",id);
        userService.delete(wrapper);
        return ResultUtils.success();
    }

}
