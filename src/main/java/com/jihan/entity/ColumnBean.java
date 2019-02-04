package com.jihan.entity;

public class ColumnBean {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 主键时为:PRI
     */
    private String columnKey;
    /**
     * 字段名
     */
    private String columnName;
    /**
     * 转驼峰后的字段名
     */
    private String columnCamelName;
    /**
     * 是否可为空
     */
    private String isNullable;
    /**
     * 数据类型
     */
    private String dataType;
    /**
     * 字符最大长度
     */
    private String characterMaximumLength;
    /**
     * 注释
     */
    private String columnComment;



    public String getTableName() {
        return tableName;
    }

    public ColumnBean setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public ColumnBean setColumnKey(String columnKey) {
        this.columnKey = columnKey;
        return this;
    }

    public String getColumnName() {
        return columnName;
    }

    public ColumnBean setColumnName(String columnName) {
        this.columnName = columnName;
        return this;
    }

    public String getColumnCamelName() {
        return columnCamelName;
    }

    public ColumnBean setColumnCamelName(String columnCamelName) {
        this.columnCamelName = columnCamelName;
        return this;
    }

    public String getIsNullable() {
        return isNullable;
    }

    public ColumnBean setIsNullable(String isNullable) {
        this.isNullable = isNullable;
        return this;
    }

    public String getDataType() {
        return dataType;
    }

    public ColumnBean setDataType(String dataType) {
        this.dataType = dataType;
        return this;
    }

    public String getCharacterMaximumLength() {
        return characterMaximumLength;
    }

    public ColumnBean setCharacterMaximumLength(String characterMaximumLength) {
        this.characterMaximumLength = characterMaximumLength;
        return this;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public ColumnBean setColumnComment(String columnComment) {
        this.columnComment = columnComment;
        return this;
    }
}
