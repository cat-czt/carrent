package com.qc.information.main.service.impl;

import com.qc.information.main.entity.LocationCar;
import com.qc.information.main.mapper.LocationCarMapper;
import com.qc.information.main.service.ILocationCarService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qc.information.utils.ResultData;
import com.qc.information.utils.ResultUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author czt123
 * @since 2019-05-13
 */
@Service
public class LocationCarServiceImpl extends ServiceImpl<LocationCarMapper, LocationCar> implements ILocationCarService {


    @Override
    public ResultData edit(LocationCar locationCar, Integer id) {
        if(locationCar.getLocationId() != null){
            this.updateById(locationCar);
        }else{
            this.insert(locationCar);
        }
        return ResultUtils.success();
    }
}
