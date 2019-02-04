package com.jihan.service.impl;

import com.jihan.service.CodeGenerator;
import com.jihan.service.CodeGeneratorManager;
import com.jihan.util.StringUtils;
import com.jihan.util.ThreadLocalUtil;
import freemarker.template.Configuration;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * DAO 代码生成器
 */
public class DaoGenerator extends CodeGeneratorManager implements CodeGenerator {

	@Override
	public void genCode(String tableName, String customEntityName) {

		Configuration cfg = getFreemarkerConfiguration();
		String tableCamelName = StringUtils.isNullOrEmpty(customEntityName) ? tableNameConvertUpperCamel(tableName) : customEntityName;
		// 设置文件名称
		String daoFileName;
		String daoImplFileName;
		if (StringUtils.isNullOrEmpty(SIGN_PACKAGE_NAME)){
			daoFileName = PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_DAO + tableCamelName + "Dao.java";
			daoImplFileName = PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_DAO_IMPL + tableCamelName + "DaoImpl.java";
		}else {
			daoFileName = PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_DAO +"/" + SIGN_PACKAGE_NAME + "/"+ tableCamelName + "Dao.java";
			daoImplFileName = PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_DAO_IMPL +"/" + SIGN_PACKAGE_NAME + "/"+ tableCamelName + "DaoImpl.java";
		}
		Map<String, Object> data = getDataMapInit(tableName,tableCamelName);
		try {
			// 创建 Service 接口
			File serviceFile = new File(daoFileName);
			// 查看父级目录是否存在, 不存在则创建
			if (!serviceFile.getParentFile().exists()) {
				serviceFile.getParentFile().mkdirs();
			}
			cfg.getTemplate("dao.ftl").process(data, new FileWriter(serviceFile));
			logger.info(tableCamelName + "Dao.java 生成成功!");

			// 创建 Service 接口的实现类
			File serviceImplFile = new File(daoImplFileName);
			// 查看父级目录是否存在, 不存在则创建
			if (!serviceImplFile.getParentFile().exists()) {
				serviceImplFile.getParentFile().mkdirs();
			}
			cfg.getTemplate("dao-impl.ftl").process(data, new FileWriter(serviceImplFile));
			logger.info(tableCamelName + "DaoImpl.java 生成成功!");

		} catch (Exception e) {
			throw new RuntimeException("Dao 生成失败!", e);
		}finally {
			ThreadLocalUtil.removeColumn();
		}
	}

	private Map<String, Object> getDataMapInit(String tableName, String tableCamelName) {
		Map<String, Object> data = new HashMap<>();
		data.put("date", DATE);
		data.put("author", AUTHOR);
		data.put("sign", SIGN_PACKAGE_NAME);
		data.put("tableName", tableName);
		data.put("entityName", tableCamelName+"DO");
		data.put("tableCamelName", tableCamelName);
		data.put("columnList", ThreadLocalUtil.getColumnBeanList());
		data.put("entityNameFirstLowerCamel", StringUtils.toLowerCaseFirstOne(tableCamelName));
		data.put("basePackage", BASE_PACKAGE);
		data.put("isNeedGeneBatch", !NOT_GEN_BATCH);
		return data;
	}

}
