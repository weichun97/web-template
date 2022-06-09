package com.itran.fgoc.server.sys.service;

import com.itran.fgoc.server.sys.entity.param.login.LoginParam;
import com.itran.fgoc.server.sys.entity.vo.login.LoginVO;

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
