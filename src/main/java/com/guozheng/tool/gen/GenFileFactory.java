package com.guozheng.tool.gen;

public class GenFileFactory {

    public static IGenBeanFile createGenBeanFile(String type) {
        if("my".equals(type)) {
            return  new MyGenBeanFile();
        } else {
            return null;
        }
    }

    public static IGenMapperFile createGenMapperFile(String type) {
        if("my".equals(type)) {
            return  new MyGenMapperFile();
        } else {
            return null;
        }
    }

    public static IGenServiceFile createGenServiceFile(String type) {
        if("my".equals(type)) {
            return  new MyGenServiceFile();
        } else {
            return null;
        }
    }

    public static IGenServiceImplFile createGenServiceImplFile(String type) {
        if("my".equals(type)) {
            return  new MyGenServiceImplFile();
        } else {
            return null;
        }
    }


    public static IGenControllerFile createGenControllerFile(String type) {
        if("my".equals(type)) {
            return  new MyGenControllerFile();
        } else {
            return null;
        }
    }

}
