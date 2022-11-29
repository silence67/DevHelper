package com.guozheng.tool.gen;

import com.guozheng.tool.jdbc.NameAndType;

import java.nio.file.Path;
import java.util.List;

public interface IGenBeanFile {

    Path generateFile(List<NameAndType> fields, String packageName, String tableName, String filePath);

}
