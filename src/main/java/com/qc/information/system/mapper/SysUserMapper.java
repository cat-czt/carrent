package com.qc.information.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.qc.information.system.entity.SysUser;
//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qc.information.system.entity.SysUserQuery;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author czt
 * @since 2019-03-28
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    SysUserQuery login(SysUser sysUser);

    void save(SysUser sysUser);
}
