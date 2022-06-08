package com.itran.fgoc.server.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chun
 * @date 2020/8/12 15:51
 */
@Data
@TableName("demo")
public class Demo implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")
    private Long id;

    /**
     * 描述
     */
    @TableField(value = "msg")
    private String msg;

    /**
     * 删除时间，默认为0
     */
    @TableField(value = "delete_time")
    @TableLogic(value = "0", delval = "UNIX_TIMESTAMP()")
    private Long deleteTime;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;


}
