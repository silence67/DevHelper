package com.guozheng.tool.jdbc;

import java.sql.*;

public class MysqlJDBCUtils {
    private static String url = "jdbc:mysql://127.0.0.1:3306/kaifeng_db?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false";
    private static String user = "root";
    private static String password = "123456";
    private static String driver = "com.mysql.cj.jdbc.Driver";

    static {
        try {
            ClassLoader cl = MysqlJDBCUtils.class.getClassLoader();
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 获取连接对象
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // 关闭1
    public static void close(Statement statement, Connection connection) {
        try {
            if (statement != null)
                statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 关闭2
    public static void close(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (resultSet != null)
                resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (statement != null)
                statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


