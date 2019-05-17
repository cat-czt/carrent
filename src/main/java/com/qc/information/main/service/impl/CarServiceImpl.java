package com.qc.information.main.service.impl;

import com.qc.information.main.entity.Car;
import com.qc.information.main.mapper.CarMapper;
import com.qc.information.main.service.ICarService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qc.information.utils.ResultData;
import com.qc.information.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author czt123
 * @since 2019-05-13
 */
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements ICarService {

    @Autowired
    private CarMapper carMapper;

    @Override
    @Transactional
    public ResultData edit(Car car, Integer id) {
        if(car.getCid() != null){
            car.setUpdateTime(new Date());
            this.updateById(car);
        }else{
            car.setCreateTime(new Date());
            this.insert(car);
        }
        return ResultUtils.success();
    }

    @Override
    public List<Car> getByLocationId(Integer id) {
        return carMapper.getByLocationId(id);
    }
}
