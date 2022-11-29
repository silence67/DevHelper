package com.guozheng.tool.gen;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class MyGenServiceFile implements IGenServiceFile {

    @Override
    public Path generateServiceFile(String beanName, String packageName, String filePath) {
        try {
            String strService = Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource("com/guozheng/tool/template/IServiceTemplate").toURI())).stream().collect(Collectors.joining("\r\n"));
            strService = String.format(strService, packageName, beanName, beanName, beanName);
            Path path = FileUtils.createFile(filePath, "I" + beanName + "Service.java");
            return Files.write(path, strService.getBytes("utf-8"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
