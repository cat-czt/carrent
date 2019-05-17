package com.qc.information.main.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.qc.information.config.AuthPassport;
import com.qc.information.main.entity.LocationCar;
import com.qc.information.main.service.ILocationCarService;
import com.qc.information.utils.ResultData;
import com.qc.information.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.qc.information.config.BaseController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author czt123
 * @since 2019-05-13
 */
@RestController
@RequestMapping("/main/locationCar")
public class LocationCarController extends BaseController {


    @Autowired
    private ILocationCarService locationCarService;


    @GetMapping("/list")
    @AuthPassport(false)
    @ResponseBody
    public List<LocationCar> LocationCarList(LocationCar locationCar){
        Wrapper<LocationCar> wrapper = new EntityWrapper<>();
        return locationCarService.selectList(wrapper);
    }

    @GetMapping("/latest")
    @AuthPassport(false)
    @ResponseBody
    public List<LocationCar> latest(){
        Wrapper<LocationCar> wrapper = new EntityWrapper();
        List<LocationCar> list =  locationCarService.selectList(wrapper);
        if(list != null && list.size() > 5){
            list = list.subList(0,9);
        }
        return list;
    }


    @PostMapping("/edit")
    @ResponseBody
    public ResultData edit(@RequestBody LocationCar locationCar){
        return locationCarService.edit(locationCar,this.getUserLogin().getId());
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public LocationCar get(@PathVariable("id") Integer id){
        Wrapper<LocationCar> wrapper = new EntityWrapper<>();
        wrapper.eq("location_id",id);
        return locationCarService.selectOne(wrapper);
    }

    @GetMapping("/del/{id}")
    @ResponseBody
    @Transactional
    public ResultData del(@PathVariable("id") Integer id){
        Wrapper<LocationCar> wrapper = new EntityWrapper<>();
        wrapper.eq("location_id",id);
        locationCarService.delete(wrapper);
        return ResultUtils.success();
    }
}

