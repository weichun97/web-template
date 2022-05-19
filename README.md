# web-template
## 项目介绍

个人自用的后台项目模板

## 项目文档

- 代码生成器使用文档：[./doc/document/generate.md](doc/document/generate.md)
- redis 使用文档：[./doc/document/redis.md](doc/document/redis.md)

## 组织结构

``` 
web-template
└──common -- 通用代码
    ├── common-core -- 工具类及通用代码
    ├── common-generate -- 代码生成器
    ├── common-log -- 日志相关
    ├── common-mybatisplus -- mybatisplus相关
    └── common-swagger -- swagger文档
└── server -- 项目代码
```

### 技术选型

#### 后端技术

| 技术                 | 说明                | 官网                                           |
| -------------------- | ------------------- | ---------------------------------------------- |
| SpringBoot           | 容器+MVC框架        | https://spring.io/projects/spring-boot         |
| MyBatisPlus          | 数据库连接工具             | https://baomidou.com/ |
| Redis                | 分布式缓存          | https://redis.io/                              |
| Lombok               | 简化对象封装工具    | https://github.com/rzwitserloot/lombok         |
| Hutool               | Java工具类库        | https://github.com/looly/hutool                |
| knife4j              | 接口文档    | https://doc.xiaominfo.com/      |
| Mapsturct            | JavaBean转换工具    | https://mapstruct.org/      |
| Fastjson             | json转换工具    | https://github.com/alibaba/fastjson/wiki      |

## 环境搭建
### 开发环境

| 工具          | 版本号 | 下载                                                         |
| ------------- | ------ | ------------------------------------------------------------ |
| JDK           | 1.8    | https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html |
| Mysql         | 5.7    | https://www.mysql.com/                                       |
| Redis         | 3.2.1  | https://redis.io/download                    
