package com.qc.information.main.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
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
public class Location extends Model<Location> {

    private static final long serialVersionUID = 1L;

    /**
     * 网点编号
     */
    @TableId(value = "location_id", type = IdType.AUTO)
    private Integer locationId;
    /**
     * 网点经纬度
     */
    private String latlng;
    /**
     * 网点名称
     */
    @TableField("location_name")
    private String locationName;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.locationId;
    }

}
