
<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->

<!-- code_chunk_output -->

- [数据库规范](#数据库规范)
  - [建表规范](#建表规范)
  - [查询规范](#查询规范)

<!-- /code_chunk_output -->

# 数据库规范

## 建表规范
|  字段名 | 类型 | 默认值 | 是否非空 |  说明 |
|---|---|---|---|---|
|  id |  bigint | 无 | 是 | 主键,自动递增 |
|  delete_time | int | 0 | 是 |  删除时间, 删除后记录删除的时间戳 |
|  create_time | datetime | 无 | 是 |  创建时间 |
|  update_time | datetime | 无 | 否 | 更新时间，表更新时自动更新当前时间 |

建表语句示例
```sql
CREATE TABLE `表名` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `delete_time` int(11) NOT NULL DEFAULT '0' COMMENT '删除时间，默认为0',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='注释';
```

## 查询规范
1. 查询多条数据禁止使用 `select *` 查询所有字段，查询单条数据允许使用 `getById()` 方法查询所有字段
