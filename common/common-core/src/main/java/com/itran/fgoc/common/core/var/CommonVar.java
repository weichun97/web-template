package com.itran.fgoc.common.core.var;

import cn.hutool.core.map.MapUtil;

import java.util.Map;

/**
 * 公用常量
 */
public interface CommonVar {

    String SYSTEM = "系统";

    Map<Object, Object> MSG = MapUtil.builder()
            .put(SYSTEM, "系统")
            .build()
            ;
}
