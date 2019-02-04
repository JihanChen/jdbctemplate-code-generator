<#if sign == "">
package ${basePackage}.web.controller;
import ${basePackage}.pojo.dataobject.${entityName};
import ${basePackage}.service.${tableCamelName}Service;
<#else>
package ${basePackage}.web.controller.${sign};
import ${basePackage}.pojo.dataobject.${sign}.${entityName};
import ${basePackage}.service.${sign}.${tableCamelName}Service;
</#if>
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author ${author}
 * @date  ${date}
*/
@Controller
@RequestMapping("/${baseRequestMapping}")
public class ${tableCamelName}Controller {

    @Autowired
    private ${tableCamelName}Service ${entityNameFirstLowerCamel}Service;

}
