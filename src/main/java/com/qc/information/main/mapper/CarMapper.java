package com.qc.information.main.mapper;

import com.qc.information.main.entity.Car;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author czt123
 * @since 2019-05-13
 */
public interface CarMapper extends BaseMapper<Car> {

    List<Car> getByLocationId(Integer id);
}
