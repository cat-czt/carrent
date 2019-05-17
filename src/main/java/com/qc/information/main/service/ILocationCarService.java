package com.qc.information.main.service;

import com.qc.information.main.entity.LocationCar;
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
public interface ILocationCarService extends IService<LocationCar> {

    ResultData edit(LocationCar locationCar, Integer id);
}
