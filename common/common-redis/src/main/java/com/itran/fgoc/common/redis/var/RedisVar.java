package com.itran.fgoc.common.redis.var;

import java.util.HashMap;
import java.util.Map;

/**
 * The interface Redis var.
 *
 * @description redis缓存key值
 */
public interface RedisVar {

    /**
     * 默认缓存时间 （小时）
     */
    int DEFAULT_TTL = 6;

    String TEST = "test";

    /**
     * 自定义缓存时长 Map
     * <p>
     * key: 缓存 key
     * value: 缓存时间(分钟)
     */
    Map<String, Integer> TTL_MAP = new HashMap<String, Integer>() {
        {
            put(TEST, 1);
        }
    };
}
