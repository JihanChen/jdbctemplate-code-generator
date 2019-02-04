package com.jihan;

import com.jihan.service.CodeGeneratorManager;

public class GeneratorApplication {
    // 数据库
    private static final String SCHEMA = "test";

    private static final String TABLE = "aa_test";

    private static final String CUSTOM_NAME = "ITest";

    private static final String[] TABLES = {
            "aa_test1", "aa_test2"
    };

    public static void main(String[] args) {
        String customizedPath = "log4j.properties";
        System.setProperty("log4j.configuration", customizedPath);
        CodeGeneratorManager cgm = new CodeGeneratorManager();
		//cgm.genCodeWithCustomName(SCHEMA,TABLE,CUSTOM_NAME);
        cgm.genCodeByTableName(SCHEMA,TABLES);

    }
}
