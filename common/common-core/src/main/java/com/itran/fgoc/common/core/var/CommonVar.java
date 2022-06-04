package com.itran.fgoc.common.core.var;

import com.itran.fgoc.common.core.api.Enums;

/**
 * 公用常量
 */
public interface CommonVar {

    String SYSTEM = "系统";

    Enums MSG = Enums.build()
            .add(SYSTEM, "系统")
            ;
}
