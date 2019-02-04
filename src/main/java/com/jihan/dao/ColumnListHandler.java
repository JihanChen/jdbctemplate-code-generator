package com.jihan.dao;

import com.jihan.entity.ColumnBean;
import com.jihan.util.StringUtils;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * schema查询结果处理
 */
public class ColumnListHandler implements ResultSetHandler<List<ColumnBean>> {

    @Override
    public List<ColumnBean> handle(ResultSet rs) throws SQLException {
        List<ColumnBean> list = new ArrayList<>();
        ColumnBean bean = null;
        while(rs.next()){
            bean = new ColumnBean();
            bean.setTableName(StringUtils.underScoreCase2CamelCase(rs.getString("table_name")));
            bean.setColumnKey(rs.getString("column_key"));
            bean.setColumnName(rs.getString("column_name"));
            bean.setIsNullable(rs.getString("is_nullable"));
            bean.setDataType(rs.getString("data_type"));
            bean.setCharacterMaximumLength(rs.getString("character_maximum_length"));
            bean.setColumnComment(rs.getString("column_comment"));
            bean.setColumnCamelName(StringUtils.underScoreCase2CamelCase(bean.getColumnName()));
            list.add(bean);
        }
        return list;
    }

}
