package com.itran.fgoc.common.mybatisplus;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author cocoyang
 * @date 2020/9/4
 * @description
 */
@ApiModel(description = "com.itran.fgoc.common.mybatisplus.PageParam")
public class PageParam implements Serializable {

    private static final long serialVersionUID = 2147028314624861265L;

    @NotNull(message = "显示条数不能为空")
    @ApiModelProperty(value = "每页显示条数", example = "10", required = true)
    private long size = 10;

    @NotNull(message = "当前页不能为空")
    @ApiModelProperty(value = "当前页", example = "1", required = true)
    private long current = 1;

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }
}
