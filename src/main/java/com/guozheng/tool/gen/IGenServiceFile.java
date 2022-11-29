package com.guozheng.tool.gen;

import java.nio.file.Path;

public interface IGenServiceFile {

    Path generateServiceFile(String beanName, String packageName, String filePath);

}
