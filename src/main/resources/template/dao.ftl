<#if sign == "">
package ${basePackage}.dao;
import ${basePackage}.pojo.dataobject.${entityName};
<#else>
package ${basePackage}.dao.${sign};
import ${basePackage}.pojo.dataobject.${sign}.${entityName};
</#if>

import java.util.List;

/**
 * @author ${author}
 * @date  ${date}
*/
public interface ${tableCamelName}Dao {

    /**
     * 查询所有
     * @param entity
     * @return
     */
    List<${entityName}> list(${entityName} entity);

    /**
    * 添加
    * @param entity
    * @return
    */
    ${entityName} insert(${entityName} entity);

    /**
    * 删除
    * @param entity
    * @return
    */
    int delete(${entityName} entity);

    /**
    * 修改
    * @param entity
    * @return
    */
    int update(${entityName} entity);

<#if isNeedGeneBatch>
    /**
    * 批量插入数据
    * <p>1.主键自增，将自动生成主键赋值并返回（前提：建表设置主键为AUTO_INCREMENT）<p/>
    * <p>2.主键非自增，需要外部传入主键的值，返回原参数对象<p/>
    * @param entityList
    * @return 批量保存结果集合
    */
    List<${entityName}> insertBatch(List<${entityName}> entityList);
</#if>
}
