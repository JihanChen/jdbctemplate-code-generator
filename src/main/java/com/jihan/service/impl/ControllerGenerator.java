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
 * Controller层 代码生成器
 */
public class ControllerGenerator extends CodeGeneratorManager implements CodeGenerator {

	@Override
	public void genCode(String tableName, String customEntityName) {
		if (NOT_GEN_CONTROLLER){
			return;
		}
		Configuration cfg = getFreemarkerConfiguration();
		// 设置文件名称
		String tableCamelName = StringUtils.isNullOrEmpty(customEntityName) ? tableNameConvertUpperCamel(tableName) : customEntityName;
		String fileName;
		if (StringUtils.isNullOrEmpty(SIGN_PACKAGE_NAME)){
			fileName = PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_CONTROLLER + tableCamelName + "Controller.java";
		}else {
			fileName = PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_CONTROLLER +"/" + SIGN_PACKAGE_NAME + "/"+ tableCamelName + "Controller.java";
		}

		Map<String, Object> data = getDataMapInit(tableCamelName);
		try {
			File controllerFile = new File(fileName);
	        if (!controllerFile.getParentFile().exists()) {
	        	controllerFile.getParentFile().mkdirs();
	        }
	        cfg.getTemplate("controller.ftl").process(data, new FileWriter(controllerFile));
			logger.info(tableCamelName + "Controller.java 生成成功!");
		} catch (Exception e) {
			throw new RuntimeException("Controller 生成失败!", e);
		}
	}
	
	/**
	 * 预置页面所需数据
	 * @param tableCamelName 表名驼峰, 将表名下划线转成大驼峰形式
	 * @return
	 */
	private Map<String, Object> getDataMapInit(String tableCamelName) {
		Map<String, Object> data = new HashMap<>();
		data.put("date", DATE);
        data.put("author", AUTHOR);
        data.put("sign", SIGN_PACKAGE_NAME);
        data.put("baseRequestMapping", StringUtils.toLowerCaseFirstOne(tableCamelName));
        data.put("entityName", tableCamelName+"DO");
		data.put("tableCamelName", tableCamelName);
		data.put("entityNameFirstLowerCamel", StringUtils.toLowerCaseFirstOne(tableCamelName));
        data.put("basePackage", BASE_PACKAGE);
		
		return data;
	}
}
