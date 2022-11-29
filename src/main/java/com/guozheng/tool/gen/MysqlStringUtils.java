package com.guozheng.tool.gen;

import com.mysql.cj.util.StringUtils;

public class MysqlStringUtils {

    public static String toJavaName(String fieldName) {
        if(StringUtils.isNullOrEmpty(fieldName)) return null;
        String[] strings = fieldName.split("_");
        StringBuilder sb = new StringBuilder();
        if(strings.length <= 1) {
            return strings[0];
        } else {
            for(int i = 0; i < strings.length; i++) {
                if(i > 0) {
                    String str = strings[i].substring(0,1).toUpperCase() + strings[i].substring(1);
                    sb.append(str);
                } else {
                    sb.append(strings[0]);
                }
            }
        }
        return sb.toString();
    }

    public static String mysqlTypeName2javaType(String mysqlTypeName) {
        if(mysqlTypeName.contains("INT")) {
            return "Integer";
        }
        if(mysqlTypeName.contains("VARCHAR")) {
            return "String";
        }
        return "";
    }

    public static String mysqlTableName2JavaClassName(String tableName) {
        if(tableName.startsWith("t_")) {
            return tableName.substring(2, 3).toUpperCase() + tableName.substring(3);
        }
        if(tableName.startsWith("sys_")) {
            return "Sys" + tableName.substring(3, 4).toUpperCase() + tableName.substring(5);
        }
        return tableName;
    }

    public static String genFieldGetterFuncName(String mysqlFieldName){
        String str = "get";
        String javaName = toJavaName(mysqlFieldName);
        str += javaName.substring(0,1).toUpperCase() + javaName.substring(1);
        return str;
    }

    public static String genFieldSetterFuncName(String mysqlFieldName){
        String str = "set";
        String javaName = toJavaName(mysqlFieldName);
        str += javaName.substring(0,1).toUpperCase() + javaName.substring(1);
        return str;
    }

}
