package com.qc.information.system.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Authour czt
 * @Date 2019-03-28 下午4:18
 **/
@Data
public class UserLogin extends SysUser {
    @ApiModelProperty("登录token")
    private String token;
    @ApiModelProperty("route")
    private String route;
}
