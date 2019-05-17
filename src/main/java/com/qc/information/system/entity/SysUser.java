package com.qc.information.system.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author czt
 * @since 2019-03-28
 */
@Getter
@Setter
@TableName("sys_user")
public class SysUser{
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField("user_name")
    private String userName;

    private String account;

    private String password;

    @TableField("id_card")
    private String idCard;

    private String mobile;

    private String address;

    private Integer credit;

    @TableField("is_blacklist")
    private Integer isBlacklist;

    @TableField("last_login_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date LastLoginTime;

    @TableField("create_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;
    @TableField("create_user")
    private Integer createUser;
    @TableField("update_user")
    private Integer updateUser;
    @TableField("update_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;

    public SysUser() {
    }

    @Override
    public String toString() {
        return "{'id':" + id +
                ",'userName':'" + userName +
                "','account':'" + account +
                "','password':'" + password +
                "','idCard':'" + idCard +
                "','mobile':'" + mobile +
                "','createDate':'" + createDate +
                "','createUser':" + createUser +
                ",'updateUser':" + updateUser +
                ",'updateDate':'" + updateDate +
                "'}";
    }
}
