package com.itran.fgoc.common.security.config.service;


import com.itran.fgoc.common.security.config.param.LoginParam;
import com.itran.fgoc.common.security.config.vo.LoginVO;

/**
 * @author chun
 * @date 2020/9/4
 * @description
 */
public interface LoginService {

    /**
     * 登录
     *
     * @param param
     * @return
     */
    LoginVO login(LoginParam param);
}
