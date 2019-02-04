package com.jihan.service;

/**
 * 配置信息变量
 */
public class CodeGeneratorConfig {
	// 项目在硬盘上的基础路径
	protected static final String PROJECT_PATH = System.getProperty("user.dir");
	// java文件路径
	protected static String JAVA_PATH;
	// 资源文件路径
	protected static String RESOURCES_PATH;
	// 模板存放位置
	protected static String TEMPLATE_FILE_PATH;
	
	// 项目基础包
	protected static String BASE_PACKAGE;
	// 项目 ENTITY 所在包
	protected static String ENTITY_PACKAGE;
	// 项目 dao 所在包
	protected static String DAO_PACKAGE;
	// 项目 dao impl 所在包
	protected static String DAO_IMPL_PACKAGE;

	// 项目 Service 所在包
	protected static String SERVICE_PACKAGE;
	// 项目 Service 实现类所在包
	protected static String SERVICE_IMPL_PACKAGE;
	// 项目 Controller 所在包
	protected static String CONTROLLER_PACKAGE;

	// 子包签名包名
	protected static String SIGN_PACKAGE_NAME;


	// 生成的 entity实体存放路径
	protected static String PACKAGE_PATH_ENTITY;
	// 生成的dao存放路径
	protected static String PACKAGE_PATH_DAO;
	// 生成的 dao impl实现存放路径
	protected static String PACKAGE_PATH_DAO_IMPL;
	// 生成的 Service 存放路径
	protected static String PACKAGE_PATH_SERVICE;
	// 生成的 Service 实现存放路径
	protected static String PACKAGE_PATH_SERVICE_IMPL;
	// 生成的 Controller 存放路径
	protected static String PACKAGE_PATH_CONTROLLER;

	// 不生成controller
	protected static Boolean NOT_GEN_CONTROLLER;
	// 不生成service
	protected static Boolean NOT_GEN_SERVICE;
	// 不生成批量方法，批量插入
	protected static Boolean NOT_GEN_BATCH;


	// 模板注释中 @author
	protected static String AUTHOR;
	// 模板注释中 @date
	protected static String DATE;
	
}
