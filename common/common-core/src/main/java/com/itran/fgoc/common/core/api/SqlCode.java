package com.itran.fgoc.common.core.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wxp
 * @date 2020/9/3 10:21
 * @description SplCode
 */
@AllArgsConstructor
@Getter
public enum SqlCode implements IErrorCode{
    /**
     * 插入数据失败
     */
    INSERT_ERROR(5000,"插入数据失败！"),
    /**
     * 修改数据失败
     */
    UPDATE_ERROR(5001,"修改数据失败！"),
    /**
     * 删除数据失败
     */
    DELETE_ERROR(5002,"删除数据失败！");

    private long code;
    private String message;
}
