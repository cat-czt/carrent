package com.qc.information.main.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.qc.information.config.AuthPassport;
import com.qc.information.main.entity.Location;
import com.qc.information.main.entity.Location;
import com.qc.information.main.service.ILocationService;
import com.qc.information.main.service.ILocationService;
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
@RequestMapping("/main/location")
public class LocationController extends BaseController {

    @Autowired
    private ILocationService locationService;


    @GetMapping("/list")
    @AuthPassport(false)
    @ResponseBody
    public List<Location> LocationList(Location location){
        Wrapper<Location> wrapper = new EntityWrapper<>();
        return locationService.selectList(wrapper);
    }

    @GetMapping("/latest")
    @AuthPassport(false)
    @ResponseBody
    public List<Location> latest(){
        Wrapper<Location> wrapper = new EntityWrapper();
        wrapper.orderBy("create_time",false);
        List<Location> list =  locationService.selectList(wrapper);
        if(list != null && list.size() > 5){
            list = list.subList(0,9);
        }
        return list;
    }


    @PostMapping("/edit")
    @ResponseBody
    public ResultData edit(@RequestBody Location Location){
        return locationService.edit(Location,this.getUserLogin().getId());
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public Location get(@PathVariable("id") Integer id){
        Wrapper<Location> wrapper = new EntityWrapper<>();
        wrapper.eq("location_id",id);
        return locationService.selectOne(wrapper);
    }

    @GetMapping("/del/{id}")
    @ResponseBody
    @Transactional
    public ResultData del(@PathVariable("id") Integer id){
        Wrapper<Location> wrapper = new EntityWrapper<>();
        wrapper.eq("location_id",id);
        locationService.delete(wrapper);
        return ResultUtils.success();
    }
}

