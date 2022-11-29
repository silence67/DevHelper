package com.guozheng.tool.gen;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class MyGenMapperFile implements  IGenMapperFile{
    @Override
    public Path generateFile(String packageName, String beanName, String filePath) {
        try {
            String mapperTemplate = Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource("com/guozheng/tool/template/MapperTemplate").toURI())).stream().collect(Collectors.joining("\r\n"));
            mapperTemplate = String.format(mapperTemplate, packageName, packageName+".bean." + beanName, beanName, beanName);

            Path path = Paths.get(filePath);
            if(!Files.exists(path)){
                Files.createDirectory(path);
            }
            String mapperFilePath = filePath + "/" + beanName + "Mapper.java";
            Path javaPath = Paths.get(mapperFilePath);
            Files.deleteIfExists(javaPath);
            Path beanFilePath  = Files.createFile(javaPath);
            return  Files.write(beanFilePath, mapperTemplate.getBytes("utf-8"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
