package com.qc.information.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.qc.information.system.entity.SysUser;
import com.qc.information.system.entity.SysUserQuery;
import com.qc.information.utils.ResultData;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author czt
 * @since 2019-03-28
 */
public interface ISysUserService extends IService<SysUser> {
    SysUserQuery login(SysUser sysUser);

    ResultData register(SysUser sysUser);

    ResultData edit(SysUser sysUser);

    ResultData resetCredit(Integer id);
}
