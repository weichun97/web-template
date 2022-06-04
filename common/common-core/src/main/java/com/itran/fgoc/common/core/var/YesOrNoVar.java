package com.itran.fgoc.common.core.var;

import cn.hutool.core.map.MapUtil;

import java.util.Map;

/**
 * @author cocoyang
 * @date 2020/9/21
 * @description
 */
public interface YesOrNoVar {

    /**
     * 是否
     */
    int NO = 0;
    int YES = 1;

    Map<Object, Object> MSG = MapUtil.builder()
            .put(YES, "是")
            .put(NO, "否")
            .build()
            ;
}
