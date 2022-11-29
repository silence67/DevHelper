package com.guozheng.tool.gen;

import com.mysql.cj.util.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MyGenControllerFile implements IGenControllerFile{
    @Override
    public Path generateFile(String packageName, String beanName, String filePath) {
        try {
            String controllerStr = Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource("com/guozheng/tool/template/RestControllerTemplate").toURI())).stream().collect(Collectors.joining("\r\n"));
            Map<String, String> map = new HashMap<>();
            map.put("{#pakageName}", packageName);
            map.put("{#beanName}", beanName);
            map.put("{#lBeanName}", beanName.substring(0,1).toLowerCase() + beanName.substring(1));
            controllerStr = MyStringUtils.format(controllerStr, map);
            Path path = FileUtils.createFile(filePath, beanName + "Controller.java");
            return  Files.write(path, controllerStr.getBytes("utf-8"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
