package com.itran.fgoc.common.core.var;

import cn.hutool.core.map.MapUtil;

import java.util.Map;

/**
 * 通用正则表达式
 * @author chun
 * @date 2020/9/17 10:13
 */
public interface PatternVar {

    String PHONE = "^1\\d{10}$";
    String PASSWORD = "^(?![A-Za-z]+$)(?![A-Z\\d]+$)(?![A-Z\\W]+$)(?![a-z\\d]+$)(?![a-z\\W]+$)(?![\\d\\W]+$)\\S{8,}$";

    Map<Object, Object> MSG = MapUtil.builder()
            .put(PHONE, "手机号")
            .put(PASSWORD, "密码")
            .build()
            ;
}
