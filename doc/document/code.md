
<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->

<!-- code_chunk_output -->

- [代码规范](#代码规范)
    - [文件命名规范及存放位置](#文件命名规范及存放位置)
    - [接口请求类型规范](#接口请求类型规范)
    - [接口名定义规范](#接口名定义规范)

<!-- /code_chunk_output -->


# 代码规范

### 文件命名规范及存放位置
|  文件类型 | 说明 |   文件命名 | 存放位置 | 示例 |
|---|---|---|---|---|
| controller  | 控制器 |  {tableName}Controller | {module}/controller/ | com/itran/fgoc/server/sys/controller/UserController.java
| service     | 服务  | {tableName}Service | {module}/serivce/ | com/itran/fgoc/server/sys/service/UserService.java
| serviceImpl | 服务实现类     | {tableName}ServiceImpl | {module}/serivce/impl/ | com/itran/fgoc/server/sys/service/impl/UserServiceImpl.java
| po          | 数据库映射实体类 | {tableName} | {module}/entity/po/ | com/itran/fgoc/server/sys/entity/po/User.java
| param       | 请求参数实体类 | {tableName}Param | {module}/entity/param/{tableName}/ | com/itran/fgoc/server/sys/entity/param/UserParam.java
| vo       | 响应数据实体类 | {tableName}VO | {module}/entity/vo/{tableName}/ | com/itran/fgoc/server/sys/entity/vo/UserVO.java
| dto       | 中间转换层数据实体类 | {tableName}DTO | {module}/entity/dto/{tableName}/ | com/itran/fgoc/server/sys/entity/dto/UserDTO.java
| mapper       | mybatis的mapper文件 | {tableName}Mapper | {module}/entity/mapper/ | com/itran/fgoc/server/sys/entity/mapper/UserMapper.java
| maps       | mapstructs  的mapper文件 | {tableName}Maps | {module}/entity/maps/ | com/itran/fgoc/server/sys/entity/maps/UserMaps.java
| mapperxml       | mybatis的xml文件  的mapper文件 | {tableName}Mapper | {module}/mapper/ | resources/com/itran/fgoc/server/sys/mapper/UserMapper.xml
| var       | 常量定义的文件 | {tableName}Var | {module}/var/ | com/itran/fgoc/server/sys/var/UserVar.java


参数说明

-  tableName: 数据库表名
-  module: 模块名

### 接口请求类型规范

| 类型  |  请求类型 |
|---|---|
| 新增  |  Post |
| 删除  |  Delete |
| 修改  |  Put |
| 查询  |  Get |

### 接口名定义规范

| 类型  | 方法名  |
|---|---|
|  分页 | {tableName}Page  |
|  下拉列表 | {tableName}Select  |
|  树形结构 | {tableName}Tree  |



