package com.itran.fgoc.common.security.config.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.jwt.JWTUtil;
import com.itran.fgoc.common.security.config.JwtTokenFilter;
import com.itran.fgoc.common.security.config.param.LoginParam;
import com.itran.fgoc.common.security.config.service.LoginService;
import com.itran.fgoc.common.security.config.vo.LoginVO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author chun
 * @date 2020/9/4
 * @description
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public LoginVO login(LoginParam param) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(param.getUsername(), param.getPassword()));
        String token = JWTUtil.createToken(MapUtil.builder(new HashMap<String, Object>())
                .put("username", "admin")
                .build(), JwtTokenFilter.JWT_KEY.getBytes());
        return new LoginVO(token, 3600);
    }
}
