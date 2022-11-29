package com.guozheng.tool.jdbc;

public class DBTableInfoFactory {

    public static IDBTableInfo createDBTableInfo(String type){
        if("mysql".equals(type)) {
            return new MysqlDBTableInfo();
        }
        return null;
    }

}
