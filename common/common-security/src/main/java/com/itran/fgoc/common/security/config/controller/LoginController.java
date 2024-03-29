package com.itran.fgoc.common.security.config.controller;

import com.itran.fgoc.common.core.api.Response;
import com.itran.fgoc.common.security.config.param.LoginParam;
import com.itran.fgoc.common.security.config.service.LoginService;
import com.itran.fgoc.common.security.config.vo.LoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;


/**
 * @author chun
 * @date 2020/9/4
 * @description demo
 */
@RequestMapping("login")
@RestController
@Api(value = "LoginController", tags = "登录")
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping
    @ApiOperation(value = "登录")
    public Response<LoginVO> login(@Valid @RequestBody LoginParam param) {
        return Response.success(loginService.login(param));
    }
}
