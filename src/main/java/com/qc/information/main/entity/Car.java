package com.qc.information.main.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
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
public class Car extends Model<Car> {

    private static final long serialVersionUID = 1L;

    /**
     * 车辆编号
     */
    @TableId(value = "cid", type = IdType.AUTO)
    private Integer cid;
    /**
     * 车辆制造商
     */
    private String maker;
    /**
     * 型号
     */
    private String model;
    /**
     * 车型。小型、中型、大型、豪华型
     */
    private String type;
    /**
     * 生产时间
     */
    @TableField("product_time")
    private Date productTime;
    /**
     * 租金/天
     */
    private BigDecimal rent;
    /**
     * 汽车图片
     */
    private String cicon;
    /**
     * 信息插入时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 信息修改时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 是否禁用。1：禁用；0：可用
     */
    private Integer disable;
    /**
     * 车辆状态。0：未加入网点；1：已加入网点；2：被使用中（已加入网点）；
     */
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.cid;
    }

}
