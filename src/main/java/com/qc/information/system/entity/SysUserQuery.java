package com.qc.information.system.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Authour czt
 * @Date 2019-03-28 下午4:36
 **/
@Getter
@Setter
public class SysUserQuery{
    private Integer id;
    private String userName;
    private String account;
    private String password;
    private String email;
    private String mobile;
    @ApiModelProperty("角色名称")
    private String roleName;

    @Override
    public String toString() {
        return "{'id':" + id +
                ",'userName':'" + userName +
                "','account':'" + account +
                "','password':'" + password +
                "','email':'" + email +
                "','mobile':'" + mobile +
                "','roleName':'" + roleName+
                "'}";
    }

}
