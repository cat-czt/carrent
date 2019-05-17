package com.qc.information.main.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import com.baomidou.mybatisplus.enums.IdType;
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
@TableName("location_car")
public class LocationCar extends Model<LocationCar> {

    private static final long serialVersionUID = 1L;

    /**
     * 网点编号
     */
    @TableId(value = "location_id", type = IdType.AUTO)
    private Integer locationId;
    /**
     * 车辆编号
     */
    private Integer cid;


    @Override
    protected Serializable pkVal() {
        return this.locationId;
    }

}
