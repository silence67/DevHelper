package com.guozheng.tool.gen;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class MyGenServiceImplFile implements IGenServiceImplFile{

    @Override
    public Path generateServiceImplFile(String beanName, String packageName, String filePath) {
        try {
            String strServiceImpl = Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource("com/guozheng/tool/template/ServiceImplTemplate").toURI())).stream().collect(Collectors.joining("\r\n"));
            strServiceImpl = String.format(strServiceImpl, packageName, beanName, beanName, beanName, beanName, beanName, beanName, beanName);
            Path path = FileUtils.createFile(filePath, beanName + "ServiceImpl.java");
            return Files.write(path, strServiceImpl.getBytes("utf-8"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
