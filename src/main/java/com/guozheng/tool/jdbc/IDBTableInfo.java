package com.guozheng.tool.jdbc;

import java.util.List;

public interface IDBTableInfo {

    List<NameAndType> getTableColumnInfo(String tableName, String databaseName);
}
