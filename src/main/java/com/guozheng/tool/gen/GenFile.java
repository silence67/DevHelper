package com.guozheng.tool.gen;

import com.guozheng.tool.jdbc.DBTableInfoFactory;
import com.guozheng.tool.jdbc.IDBTableInfo;
import com.guozheng.tool.jdbc.NameAndType;

import java.nio.file.Path;
import java.util.List;

public class GenFile {

    private String tableName;

    private String packageName;

    private String filePath;

    private IDBTableInfo idbTableInfo;

    private IGenBeanFile genBeanFile;

    private IGenMapperFile genMapperFile;

    private IGenServiceFile genServiceFile;

    private IGenServiceImplFile genServiceImplFile;

    private IGenControllerFile genControllerFile;

    public GenFile(String tableName, String packageName, String filePath) {
        this.tableName = tableName;
        this.packageName = packageName;
        this.filePath = filePath;
        Long start = System.currentTimeMillis();
        idbTableInfo = DBTableInfoFactory.createDBTableInfo("mysql");
        genBeanFile = GenFileFactory.createGenBeanFile("my");
        genMapperFile = GenFileFactory.createGenMapperFile("my");
        genServiceFile = GenFileFactory.createGenServiceFile("my");
        genServiceImplFile = GenFileFactory.createGenServiceImplFile("my");
        genControllerFile = GenFileFactory.createGenControllerFile("my");
        Long end = System.currentTimeMillis();
        System.out.println("------ 用时:" + (end - start) + "ms ------");
    }

    public void generateFiles() {
        Path path = generateBeanFile();
        String beanName = path.toFile().getName().substring(0, path.toFile().getName().indexOf("."));
        generateMapperFile(beanName);
        genServiceFile.generateServiceFile(beanName, packageName, filePath);
        genServiceImplFile.generateServiceImplFile(beanName, packageName, filePath);
        genControllerFile.generateFile(packageName, beanName, filePath);
    }

    public Path generateBeanFile() {
        List<NameAndType> list = idbTableInfo.getTableColumnInfo(tableName, "");
        return genBeanFile.generateFile(list, packageName, tableName, filePath);
    }

    public Path generateMapperFile(String beanName) {
        Path path = genMapperFile.generateFile(packageName, beanName, filePath);
        return path;
    }

}
