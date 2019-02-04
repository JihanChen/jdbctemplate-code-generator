package com.jihan.service;
/**
 * 主要逻辑接口
 */
public interface CodeGenerator {
	
	/**
	 * 代码生成主要逻辑
	 * @param tableName 表名
	 * @param customEntityName 自定义实体类名, 为null则默认将表名下划线转成大驼峰形式
、	 */
	void genCode(String tableName, String customEntityName);
}
