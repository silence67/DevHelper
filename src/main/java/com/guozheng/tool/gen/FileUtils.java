package com.guozheng.tool.gen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {


    public static Path createFile(String filePath, String javaFileName) {
        Path path = Paths.get(filePath);
        if(!Files.exists(path)){
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        String mapperFilePath = filePath + "/" + javaFileName;
        Path javaPath = Paths.get(mapperFilePath);
        try {
            Files.deleteIfExists(javaPath);
            Files.createFile(javaPath);
            return  javaPath;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Files.write(javaPath, mapperTemplate.getBytes("utf-8"));
    }
}
