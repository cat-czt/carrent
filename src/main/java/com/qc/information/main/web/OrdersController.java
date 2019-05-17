package com.qc.information.main.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.qc.information.config.AuthPassport;
import com.qc.information.main.entity.Orders;
import com.qc.information.main.entity.Player;
import com.qc.information.main.service.IOrdersService;
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
@RequestMapping("/main/orders")
public class OrdersController extends BaseController {

    @Autowired
    private IOrdersService ordersService;


    @GetMapping("/list")
    @AuthPassport(false)
    @ResponseBody
    public List<Orders> OrdersList(Orders orders){
        Wrapper<Orders> wrapper = new EntityWrapper<>();
        if(orders.getOrderId() != null){
            wrapper.eq("order_id",orders.getOrderId());
        }
        if(orders.getUserName() != null){
            wrapper.eq("user_name",orders.getUserName());
        }
        if(orders.getUserPhone() != null){
            wrapper.eq("user_phone",orders.getUserPhone());
        }
        if(orders.getCreateTime() != null){
            wrapper.eq("create_time",orders.getCreateTime());
        }
        return ordersService.selectList(wrapper);
    }

    @GetMapping("/latest")
    @AuthPassport(false)
    @ResponseBody
    public List<Orders> latest(){
        Wrapper<Orders> wrapper = new EntityWrapper();
        wrapper.orderBy("create_time",false);
        List<Orders> list =  ordersService.selectList(wrapper);
        if(list != null && list.size() > 5){
            list = list.subList(0,9);
        }
        return list;
    }


    @PostMapping("/edit")
    @ResponseBody
    public ResultData edit(@RequestBody Orders orders){
        return ordersService.edit(orders,this.getUserLogin().getId());
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public Orders get(@PathVariable("id") Integer id){
        Wrapper<Orders> wrapper = new EntityWrapper<>();
        wrapper.eq("id",id);
        return ordersService.selectOne(wrapper);
    }

    @GetMapping("/del/{id}")
    @ResponseBody
    @Transactional
    public ResultData del(@PathVariable("id") Integer id){
        Wrapper<Orders> wrapper = new EntityWrapper<>();
        wrapper.eq("id",id);
        ordersService.delete(wrapper);
        return ResultUtils.success();
    }
}

