package com.qc.information.main.web;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.qc.information.config.AuthPassport;
import com.qc.information.main.entity.Car;
import com.qc.information.main.service.ICarService;
import com.qc.information.utils.ResultData;
import com.qc.information.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import com.qc.information.config.BaseController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
@RequestMapping("/main/car")
public class CarController extends BaseController {

    @Autowired
    private ICarService carService;


    @GetMapping("/list")
    @AuthPassport(false)
    @ResponseBody
    public List<Car> OrdersList(Car car){
        Wrapper<Car> wrapper = new EntityWrapper<>();
        return carService.selectList(wrapper);
    }

    @GetMapping("/getByLocationId/{locationId}")
    @AuthPassport(false)
    @ResponseBody
    public List<Car> getByLocationId(@PathVariable("locationId") Integer locationId){
        return carService.getByLocationId(locationId);
    }

    @GetMapping("/latest")
    @AuthPassport(false)
    @ResponseBody
    public List<Car> latest(){
        Wrapper<Car> wrapper = new EntityWrapper();
        wrapper.orderBy("create_time",false);
        List<Car> list =  carService.selectList(wrapper);
        if(list != null && list.size() > 5){
            list = list.subList(0,5);
        }
        return list;
    }


    @PostMapping("/edit")
    @ResponseBody
    public ResultData edit(@RequestBody Car car,@RequestParam(value = "carImg") MultipartFile carImg){
        return carService.edit(car,this.getUserLogin().getId());
    }

    @PostMapping("/saveImg")
    @ResponseBody
    public ResultData saveImg(@RequestParam(value = "carImg") MultipartFile file){
        if (file.isEmpty()) {
            return null;
        }
        String fileName = file.getOriginalFilename();  // 文件名
//        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = getUrl() + File.separator + "img";
//        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = "../static/img/" + fileName;
        JSONObject d = new JSONObject();
        d.put("fileName",filename);
        ResultData res = new ResultData();
        res.setStatus(200);
        res.setData(d);
        return res;
    }

    public static  String  getUrl() {

        String path = null;
        try {
            String serverpath= ResourceUtils.getURL("classpath:static").getPath().replace("%20"," ").replace('/', '\\');
            path=serverpath.substring(1);//从路径字符串中取出工程路径
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return path;
    }


    @GetMapping("/get/{id}")
    @ResponseBody
    public Car get(@PathVariable("id") Integer id){
        Wrapper<Car> wrapper = new EntityWrapper<>();
        wrapper.eq("cid",id);
        return carService.selectOne(wrapper);
    }

    @GetMapping("/del/{id}")
    @ResponseBody
    @Transactional
    public ResultData del(@PathVariable("id") Integer id){
        Wrapper<Car> wrapper = new EntityWrapper<>();
        wrapper.eq("cid",id);
        carService.delete(wrapper);
        return ResultUtils.success();
    }
}

