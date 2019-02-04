package com.jihan.service.impl;


import com.jihan.service.CodeGenerator;
import com.jihan.service.CodeGeneratorManager;
import com.jihan.util.StringUtils;
import freemarker.template.Configuration;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Service层 代码生成器
 */
public class ServiceGenerator extends CodeGeneratorManager implements CodeGenerator {

	@Override
	public void genCode(String tableName, String customEntityName) {
		if (NOT_GEN_SERVICE){
			return;
		}
		Configuration cfg = getFreemarkerConfiguration();
		// 设置文件名称
		String tableCamelName = StringUtils.isNullOrEmpty(customEntityName) ? tableNameConvertUpperCamel(tableName) : customEntityName;
		String serviceFileName;
		String serviceImplFileName;
		if (StringUtils.isNullOrEmpty(SIGN_PACKAGE_NAME)){
			serviceFileName = PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_SERVICE + tableCamelName + "Service.java";
			serviceImplFileName = PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_SERVICE_IMPL + tableCamelName + "ServiceImpl.java";
		}else {
			serviceFileName = PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_SERVICE +"/" + SIGN_PACKAGE_NAME + "/"+ tableCamelName + "Service.java";
			serviceImplFileName = PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_SERVICE_IMPL +"/" + SIGN_PACKAGE_NAME + "/"+ tableCamelName + "ServiceImpl.java";

		}

		Map<String, Object> data = getDataMapInit(tableCamelName);
		try {
			// 创建 Service 接口
			File serviceFile = new File(serviceFileName);
			// 查看父级目录是否存在, 不存在则创建
			if (!serviceFile.getParentFile().exists()) {
				serviceFile.getParentFile().mkdirs();
			}
			cfg.getTemplate("service.ftl").process(data, new FileWriter(serviceFile));
			logger.info(tableCamelName + "Service.java 生成成功!");
			
			// 创建 Service 接口的实现类
			File serviceImplFile = new File(serviceImplFileName);
			// 查看父级目录是否存在, 不存在则创建
			if (!serviceImplFile.getParentFile().exists()) {
				serviceImplFile.getParentFile().mkdirs();
			}
			cfg.getTemplate("service-impl.ftl").process(data, new FileWriter(serviceImplFile));
			logger.info(tableCamelName + "ServiceImpl.java 生成成功!");
		} catch (Exception e) {
			throw new RuntimeException("Service 生成失败!", e);
		}
	}
	
	/**
	 * 预置页面所需数据
	 * @param tableCamelName
	 * @return
	 */
	private Map<String, Object> getDataMapInit(String tableCamelName) {
		Map<String, Object> data = new HashMap<>();
		data.put("date", DATE);
		data.put("author", AUTHOR);
		data.put("sign", SIGN_PACKAGE_NAME);
		data.put("entityName", tableCamelName+"DO");
		data.put("tableCamelName", tableCamelName);
		data.put("entityNameFirstLowerCamel", StringUtils.toLowerCaseFirstOne(tableCamelName));
		data.put("basePackage", BASE_PACKAGE);
		
		return data;
	}
}
