package com.qc.information.main.service;

import com.qc.information.main.entity.Location;
import com.baomidou.mybatisplus.service.IService;
import com.qc.information.utils.ResultData;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author czt123
 * @since 2019-05-13
 */
public interface ILocationService extends IService<Location> {

    ResultData edit(Location location, Integer id);
}
