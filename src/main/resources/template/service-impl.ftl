<#if sign == "">
package ${basePackage}.service.impl;
import ${basePackage}.pojo.dataobject.${entityName};
import ${basePackage}.dao.${tableCamelName}Dao;
import ${basePackage}.service.${tableCamelName}Service;
<#else>
package ${basePackage}.service.impl.${sign};
import ${basePackage}.pojo.dataobject.${sign}.${entityName};
import ${basePackage}.dao.${sign}.${tableCamelName}Dao;
import ${basePackage}.service.${sign}.${tableCamelName}Service;
</#if>


import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ${author}
 * @date  ${date}
*/
@Service
public class ${tableCamelName}ServiceImpl implements ${tableCamelName}Service {

    @Autowired
    private ${tableCamelName}Dao ${entityNameFirstLowerCamel}Dao;

}
