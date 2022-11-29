package com.guozheng.tool.gen;

import com.guozheng.tool.jdbc.NameAndType;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class MyGenBeanFile implements  IGenBeanFile{

    @Override
    public Path generateFile(List<NameAndType> fields, String packageName, String tableName, String filePath) {
        try {
            if(fields == null || fields.size() < 1) return null;
            String strId = Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource("com/guozheng/tool/template/TableIdTemplate").toURI())).stream().collect(Collectors.joining("\r\n\t"));
            strId = String.format(strId, fields.get(0).getName(), MysqlStringUtils.mysqlTypeName2javaType(fields.get(0).getType()), MysqlStringUtils.toJavaName(fields.get(0).getName()));
            StringBuilder strFields = new StringBuilder();
            String fieldTemplate = Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource("com/guozheng/tool/template/TableFieldTemplate").toURI())).stream().collect(Collectors.joining("\r\n\t"));

            for(int i=1; i< fields.size(); i++) {
                String field = String.format(fieldTemplate, fields.get(i).getName(), MysqlStringUtils.mysqlTypeName2javaType(fields.get(i).getType()), MysqlStringUtils.toJavaName(fields.get(i).getName()));
                strFields.append(field);
                strFields.append("\r\n\r\n\t");
            }
            String beanStr = Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource("com/guozheng/tool/template/BeanTemplate").toURI())).stream().collect(Collectors.joining("\r\n"));


            String getterTemplate = Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource("com/guozheng/tool/template/GetterTemplate").toURI())).stream().collect(Collectors.joining("\r\n\t"));
            String setterTemplate = Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource("com/guozheng/tool/template/SetterTemplate").toURI())).stream().collect(Collectors.joining("\r\n\t"));
            StringBuilder getterSetterStr = new StringBuilder();
            for(int i=0; i< fields.size(); i++) {
                NameAndType nameAndType = fields.get(i);
                String javaName = MysqlStringUtils.toJavaName(nameAndType.getName());
                String getter = String.format(getterTemplate, MysqlStringUtils.mysqlTypeName2javaType(nameAndType.getType()), MysqlStringUtils.genFieldGetterFuncName(nameAndType.getName()), MysqlStringUtils.toJavaName(nameAndType.getName()));
                String setter = String.format(setterTemplate, MysqlStringUtils.genFieldSetterFuncName(nameAndType.getName()), MysqlStringUtils.mysqlTypeName2javaType(nameAndType.getType()), javaName, javaName, javaName);
                getterSetterStr.append(getter + "\r\n\r\n\t");
                getterSetterStr.append(setter + "\r\n\r\n\t");
            }
            beanStr = String.format(beanStr, packageName, tableName, MysqlStringUtils.mysqlTableName2JavaClassName(tableName),strId, strFields.toString(),getterSetterStr.toString());

            Path path = Paths.get(filePath);
            if(!Files.exists(path)){
                Files.createDirectory(path);
            }
            Files.deleteIfExists(Paths.get(filePath + "/" +  MysqlStringUtils.mysqlTableName2JavaClassName(tableName) + ".java"));
            Path beanFilePath  = Files.createFile(Paths.get(filePath + "/" +  MysqlStringUtils.mysqlTableName2JavaClassName(tableName) + ".java"));
            Path thePath = Files.write(beanFilePath, beanStr.getBytes("utf-8"));
            return thePath;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }
}
