package com.guozheng.tool.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlDBTableInfo implements IDBTableInfo{

    @Override
    public List<NameAndType> getTableColumnInfo(String tableName, String databaseName) {
        List<NameAndType> list = new ArrayList<>();
        try {
            Connection connection = MysqlJDBCUtils.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from " + tableName);
            ResultSetMetaData rsmd = rs.getMetaData();
            int count = rsmd.getColumnCount();
            for(int i = 1; i <= count; i++) {
                NameAndType nameAndType = new NameAndType();
                String name = rsmd.getColumnName(i);
                String type = rsmd.getColumnTypeName(i);
                nameAndType.setName(name);
                nameAndType.setType(type);
                list.add(nameAndType);
            }
            rsmd = null;

            MysqlJDBCUtils.close(rs,st,connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }




}
