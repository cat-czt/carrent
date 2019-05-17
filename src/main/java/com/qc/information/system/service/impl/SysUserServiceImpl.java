package com.qc.information.system.service.impl;

//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qc.information.system.entity.SysUser;
import com.qc.information.system.entity.SysUserQuery;
import com.qc.information.system.entity.SysUserRole;
import com.qc.information.system.mapper.SysUserMapper;
import com.qc.information.system.mapper.SysUserRoleMapper;
import com.qc.information.system.service.ISysUserService;
import com.qc.information.utils.Md5Encrypt;
import com.qc.information.utils.ResultData;
import com.qc.information.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author czt
 * @since 2019-03-28
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public SysUserQuery login(SysUser sysUser) {
        return sysUserMapper.login(sysUser);
    }

    @Override
    @Transactional
    public ResultData register(SysUser sysUser) {
        //添加用户
        sysUser.setCreateUser(1);
        sysUser.setPassword(Md5Encrypt.encrypt(sysUser.getPassword()));
        sysUserMapper.save(sysUser);
        //添加用户角色  默认为普通用户
        SysUserRole userRole = new SysUserRole();
        userRole.setRoleId(2);
        userRole.setUserId(sysUser.getId());
        sysUserRoleMapper.save(userRole);
        return ResultUtils.success();
    }

    @Override
    @Transactional
    public ResultData edit(SysUser sysUser) {
        sysUser.setUpdateDate(new Date());
        sysUser.setUpdateUser(1);
        this.updateById(sysUser);
        return ResultUtils.success();
    }

    @Override
    public ResultData resetCredit(Integer id) {
        sysUserRoleMapper.resetCredit(id);
        return ResultUtils.success();
    }
}
