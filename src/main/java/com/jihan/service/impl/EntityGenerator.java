package com.jihan.service.impl;

import com.jihan.dao.CodeGenDao;
import com.jihan.entity.ColumnBean;
import com.jihan.service.CodeGenerator;
import com.jihan.service.CodeGeneratorManager;
import com.jihan.util.StringUtils;
import com.jihan.util.ThreadLocalUtil;
import freemarker.template.Configuration;

import java.io.File;
import java.io.FileWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Model 代码生成器
 */
public class EntityGenerator extends CodeGeneratorManager implements CodeGenerator {

	@Override
	public void genCode(String tableName, String customEntityName) {

		// 获取数据库连接,查询所有的字段
		List<ColumnBean> columnList = null;
		try {
			columnList = CodeGenDao.getInstance().selectColumns(ThreadLocalUtil.getSchema(), tableName);
		} catch (SQLException e) {
			System.out.printf("处理异常*_*，原因:%s\n", e.getMessage());
			logger.info("处理异常*_*，原因:%s\n", e.getMessage());
		} catch (Exception e){
			logger.info("处理异常*_*，原因:%s\n", e.getMessage());
		} finally {
			ThreadLocalUtil.removeSchema();
		}
		if (columnList == null || columnList.size() == 0){
			throw new RuntimeException("entity实体 生成失败!,tableName 表不存在或者生成失败！！");
		}
		ThreadLocalUtil.setColumnBean(columnList);
		Configuration cfg = getFreemarkerConfiguration();

		// 设置文件名称
		String tableCamelName = StringUtils.isNullOrEmpty(customEntityName) ? tableNameConvertUpperCamel(tableName) : customEntityName;
		String fileName;
		if (StringUtils.isNullOrEmpty(SIGN_PACKAGE_NAME)){
			fileName = PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_ENTITY + tableCamelName + "DO.java";
		}else {
			fileName = PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_ENTITY +"/" + SIGN_PACKAGE_NAME + "/"+ tableCamelName + "DO.java";
		}
		Map<String, Object> data = getDynamicDataMapInit( tableCamelName,columnList);
		try {
			File controllerFile = new File(fileName);
			if (!controllerFile.getParentFile().exists()) {
				controllerFile.getParentFile().mkdirs();
			}
			cfg.getTemplate("entity.ftl").process(data, new FileWriter(controllerFile));
			logger.info(tableCamelName + "DO.java 生成成功!");
		} catch (Exception e) {
			throw new RuntimeException("entity实体 生成失败!", e);
		}
		
		if (StringUtils.isNullOrEmpty(customEntityName)) {
			customEntityName = tableNameConvertUpperCamel(tableName);
		}
		
		logger.info(customEntityName, "{}.java 生成成功!");
	}

	private Map<String, Object> getDynamicDataMapInit( String tableCamelName,List<ColumnBean> columnList) {
		Map<String, Object> data = new HashMap<>();
		data.put("date", DATE);
		data.put("author", AUTHOR);
		data.put("sign", SIGN_PACKAGE_NAME);
		data.put("entityName", tableCamelName+"DO");
		data.put("columnList", columnList);
		data.put("entityNameFirstLowerCamel", StringUtils.toLowerCaseFirstOne(tableCamelName));
		data.put("basePackage", BASE_PACKAGE);
		return data;
	}

}
