package com.guozheng.tool.gen;

import java.nio.file.Path;

public interface IGenServiceImplFile {

    Path generateServiceImplFile(String beanName, String packageName, String filePath);

}
