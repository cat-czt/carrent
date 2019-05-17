package com.qc.information.main.service;

import com.qc.information.main.entity.Car;
import com.baomidou.mybatisplus.service.IService;
import com.qc.information.utils.ResultData;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author czt123
 * @since 2019-05-13
 */
public interface ICarService extends IService<Car> {

    ResultData edit(Car car, Integer id);

    List<Car> getByLocationId(Integer locationId);
}
