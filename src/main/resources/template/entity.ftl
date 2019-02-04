<#if sign == "">
package ${basePackage}.pojo.dataobject;
 <#else>
package ${basePackage}.pojo.dataobject.${sign};
</#if>
import lombok.Getter;
import lombok.Setter;
<#assign dateimportFlag = false>
<#assign bigDecimalimportFlag = false>
<#list columnList as x>
    <#if x.dataType == "timestamp" || x.dataType == "datetime" || x.dataType == "date">
        <#assign dateimportFlag = true>
    </#if>
    <#if x.dataType == "decimal">
        <#assign bigDecimalimportFlag = true>
    </#if>
</#list>
<#if dateimportFlag == true>
import java.util.Date;
</#if>
<#if bigDecimalimportFlag == true>
import java.math.BigDecimal;
</#if>

/**
 * @author ${author}
 * @date  ${date}
*/
@Getter
@Setter
public class ${entityName} {

<#list columnList as x>
    <#-- 判断字段类型，不同类型设置不同的java 类型 -->
        /**
        * ${x.columnComment}
        */
    <#if x.dataType == "bigint" || x.dataType == "int" >
        private Long ${x.columnCamelName};
    <#elseif x.dataType == "char" || x.dataType == "varchar" || x.dataType == "text" || x.dataType == "tinytext" || x.dataType == "mediumtext" || x.dataType == "longtext" || x.dataType == "enum" >
        private String ${x.columnCamelName};
    <#elseif  x.dataType == "integer" || x.dataType == "smallint" || x.dataType == "mediumint" || x.dataType == "tinyint">
        private Integer ${x.columnCamelName};
    <#elseif x.dataType == "blob" || x.dataType == "tinyblob" || x.dataType == "mediumblob" || x.dataType == "longblob" >
        private byte[] ${x.columnCamelName};
    <#elseif x.dataType == "timestamp" || x.dataType == "datetime" || x.dataType == "date" >
        private Date ${x.columnCamelName};
    <#elseif x.dataType == "double" >
        private Double ${x.columnCamelName};
    <#elseif x.dataType == "float" >
        private Float ${x.columnCamelName};
    <#elseif x.dataType == "decimal" >
        private BigDecimal ${x.columnCamelName};
    <#elseif x.dataType == "bit" >
        private Boolean ${x.columnCamelName};
    </#if>
</#list>

}
