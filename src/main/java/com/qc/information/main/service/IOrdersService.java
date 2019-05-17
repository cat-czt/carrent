package com.qc.information.main.service;

import com.qc.information.main.entity.Orders;
import com.baomidou.mybatisplus.service.IService;
import com.qc.information.utils.ResultData;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author czt123
 * @since 2019-05-13
 */
public interface IOrdersService extends IService<Orders> {

    ResultData edit(Orders orders, Integer id);
}
