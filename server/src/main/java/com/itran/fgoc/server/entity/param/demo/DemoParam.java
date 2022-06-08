package com.itran.fgoc.server.entity.param.demo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chun
 * @date 2020/9/4
 * @description demo
 */
@Data
@ApiModel(value = "com.itran.fgoc.server.entity.param.demo.DemoParam")
public class DemoParam implements Serializable{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键", example = "")
    private Long id;

    @ApiModelProperty(value = "描述", example = "")
    private String msg;

    @ApiModelProperty(value = "删除时间，默认为0", example = "")
    private Integer deleteTime;

    @ApiModelProperty(value = "创建时间", example = "")
    private Date createTime;

    @ApiModelProperty(value = "更新时间", example = "")
    private Date updateTime;

}