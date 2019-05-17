package com.qc.information.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.qc.information.system.entity.SysUserRole;
import com.qc.information.utils.ResultData;
//import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author czt
 * @since 2019-03-28
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    void save(SysUserRole userRole);

    void resetCredit(Integer id);
}
