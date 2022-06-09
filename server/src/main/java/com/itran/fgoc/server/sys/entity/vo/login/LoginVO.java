package com.itran.fgoc.server.sys.entity.vo.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "com.itran.fgoc.server.sys.entity.vo.login.LoginVO")
public class LoginVO {

    @ApiModelProperty(value = "token", required = true)
    private String token;

    @ApiModelProperty(value = "过期时间，单位:秒", required = true)
    private Integer expireTime;
}
