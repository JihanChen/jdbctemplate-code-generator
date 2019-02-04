<#if sign == "">
package ${basePackage}.service;
import ${basePackage}.pojo.dataobject.${entityName};
<#else>
package ${basePackage}.service.${sign};
import ${basePackage}.pojo.dataobject.${sign}.${entityName};
</#if>

/**
 * @author ${author}
 * @date  ${date}
*/
public interface ${tableCamelName}Service {

}
