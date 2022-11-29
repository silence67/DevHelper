package com.guozheng.tool.gen;

import java.nio.file.Path;

public interface IGenControllerFile {

    Path generateFile(String packageName, String beanName, String filePath);

}
