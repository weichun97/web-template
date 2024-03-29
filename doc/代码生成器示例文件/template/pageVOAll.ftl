package ${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>${dir?replace("/", ".")}.${tableInfo.tableNameCamelCase?lower_case};

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chun
 * @date 2020/9/4
 * @description ${tableInfo.tableComment}
 */
@Data
@ApiModel(value = "${packageName}.<#if module!=null && module?length gt 0>${module?trim}.</#if>entity.vo.${tableInfo.tableNameCamelCase?lower_case}.${tableInfo.tableNameCamelCase}PageVO")
public class ${tableInfo.tableNameCamelCase}PageVO implements Serializable{

    private static final long serialVersionUID = 1L;

<#if tableInfo.columnDtos?exists && tableInfo.columnDtos?size gt 0>
    <#list tableInfo.columnDtos as columnDTO >
    <#if columnDTO.columnName != 'deleteTime' && columnDTO.columnName != 'updateTime'>
    @ApiModelProperty(value = "${columnDTO.columnComment}", example = "")
    private ${columnDTO.javaType} ${columnDTO.columnName};

    </#if>
    </#list>
</#if>
}
