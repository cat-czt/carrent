package com.qc.information.main.service.impl;

import com.qc.information.main.entity.Location;
import com.qc.information.main.mapper.LocationMapper;
import com.qc.information.main.service.ILocationService;
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
public class LocationServiceImpl extends ServiceImpl<LocationMapper, Location> implements ILocationService {


    @Override
    public ResultData edit(Location location, Integer id) {
        if(location.getLocationId() != null){
            location.setUpdateTime(new Date());
            this.updateById(location);
        }else{
            location.setCreateTime(new Date());
            this.insert(location);
        }
        return ResultUtils.success();
    }
}
