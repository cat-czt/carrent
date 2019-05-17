package com.qc.information.main.service.impl;

import com.qc.information.main.entity.Orders;
import com.qc.information.main.mapper.OrdersMapper;
import com.qc.information.main.service.IOrdersService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qc.information.utils.ResultData;
import com.qc.information.utils.ResultUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {


    @Override
    @Transactional
    public ResultData edit(Orders orders, Integer id) {
        if(orders.getOrderId() != null){
            orders.setUpdateTime(new Date());
            this.updateById(orders);
        }else{
            orders.setCreateTime(new Date());
            this.insert(orders);
        }
        return ResultUtils.success();
    }
}
