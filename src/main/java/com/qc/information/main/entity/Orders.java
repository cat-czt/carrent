package com.qc.information.main.entity;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author czt123
 * @since 2019-05-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Orders extends Model<Orders> {

    private static final long serialVersionUID = 1L;

    /**
     * 订单编号
     */
    @TableId("order_id")
    private String orderId;
    /**
     * 订单金额
     */
    @TableField("order_amount")
    private BigDecimal orderAmount;
    /**
     * 客户编号
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 客户名称
     */
    @TableField("user_name")
    private String userName;
    /**
     * 客户电话
     */
    @TableField("user_phone")
    private String userPhone;
    /**
     * 客户地址
     */
    @TableField("user_address")
    private String userAddress;
    /**
     * 车辆编号
     */
    @TableField("car_id")
    private Integer carId;
    /**
     * 车辆制造商
     */
    @TableField("car_maker")
    private String carMaker;
    /**
     * 车辆类型
     */
    @TableField("car_type")
    private String carType;
    /**
     * 取车地址
     */
    @TableField("pickup_location")
    private String pickupLocation;
    /**
     * 取车时间
     */
    @TableField("pickup_time")
    private Date pickupTime;
    /**
     * 还车地址
     */
    @TableField("dropoff_location")
    private String dropoffLocation;
    /**
     * 预定还车时间
     */
    @TableField("dropoff_time")
    private Date dropoffTime;
    /**
     * 订单状态。0：取车中；1：使用中；2：已还车,订单完结；3:取消订单。
     */
    @TableField("order_status")
    private Integer orderStatus;
    /**
     * 订单创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 订单修改时间
     */
    @TableField("update_time")
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.orderId;
    }

}
