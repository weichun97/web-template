
<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->

<!-- code_chunk_output -->

- [代码生成器说明文档](#代码生成器说明文档)
    - [使用](#使用)
    - [部署方法](#部署方法)
        - [引入 pom 文件](#引入-pom-文件)
        - [编写模板文件](#编写模板文件)
        - [编写 yml 文件](#编写-yml-文件)

<!-- /code_chunk_output -->

# 代码生成器使用文档
## 使用
访问地址：http://localhost:8999/generate.html
![](http://img.chunge666.top/20220519172320.png)

生成代码
![](http://img.chunge666.top/20220519172859.png)

查看生成结果
![](http://img.chunge666.top/20220519173141.png)

## 部署方法
### 引入 pom 文件
```
<dependency>
    <groupId>com.itran.fgoc</groupId>
    <artifactId>common-generate</artifactId>
</dependency>
```

### 编写模板文件
用于生成自定义的文件，放置与 `resource/template` 目录下即可读取到。模板文件是以 `freemarker` 格式编写, 示例文件位于 `doc/代码生成器示例文件` 目录下，可以直接复制到项目的 `resource` 目录下使用.

模板文件中可使用的变量如下

|  字段 |  说明 |
|---|---|
| packageName  |  当前项目的包名 |
| module       |  模块名，用户通过 web 页面手动录入 |
| dir          |  配置文件录入,位于 chun.generate.template-map.*.dir |
| tableInfo    |  模块名，用户通过 web 页面手动录入 |
| ----tableNameCamelCase  |  数据库表名小驼峰模式 |
| ----tableComment  |  数据库表注释 |
| ----columnDtos  |  字段信息，数组 |
| --------columnName  |  数据库字段名转成小驼峰格式 |
| --------javaType  |  数据库字段的java类型 |
| --------columnComment  |  数据库字段备注 |
| --------oldColumnName  |  数据库字段名数据库格式 |

### 编写 yml 文件

```yaml
# 固定前缀
chun:
  generate:
    template-map:
      # 生成模板类型，此处可自定义，用于在 web 页面显示不同的生成方式的单选框
      all:
        # 模板文件名
        template-file: poAll.ftl
        # 文件夹名
        dir: entity/po
        # 生成的文件后缀
        suffix: .java
        # 生成类型，此处只能选择两种，class 或者 resource, 选择 class 的话会生成在 java 包的根目录里面, 选择 resource 则会生成在 resource 资源文件夹
        type: class
        # 子类型，递归生成文件，此处有BUG,只会识别到第一层的子元素，后续修复
        child:
          mapper:
            template-file: mapperAll.ftl
            dir: mapper
            suffix: Mapper.java
            type: class
          mapper-xml:
            template-file: mapperXmlAll.ftl
            dir: mapper
            suffix: Mapper.xml
            type: resource
```


