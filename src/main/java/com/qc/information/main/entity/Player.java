package com.qc.information.main.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
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
 * @since 2019-05-09
 */
@Data
public class Player extends Model<Player> {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
     * 所属球队
     */
    @TableField("team_id")
    private Integer teamId;
    @TableField("player_name")
    private String playerName;
    @TableField("nick_name")
    private String nickName;
    private Integer age;
    /**
     * 1男 2女
     */
    private Integer sex;
    private String description;
    /**
     * 个人积分
     */
    private String score;
    /**
     * 个人进球数
     */
    @TableField("shoot_record")
    private String shootRecord;
    /**
     * 助攻数
     */
    private String assists;
    @TableField("create_date")
    private Date createDate;
    @TableField("create_user")
    private Integer createUser;
    @TableField("update_date")
    private Date updateDate;
    @TableField("update_user")
    private Integer updateUser;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
