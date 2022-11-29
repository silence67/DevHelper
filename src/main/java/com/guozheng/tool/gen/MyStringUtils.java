package com.guozheng.tool.gen;

import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class MyStringUtils {

    public static String format(String src, Map<String, String> params) {
        Set<String> set = params.keySet();

        for(String key : set) {
            src = src.replaceAll(Pattern.quote(key), params.get(key));
        }
        return src;
    }
}
