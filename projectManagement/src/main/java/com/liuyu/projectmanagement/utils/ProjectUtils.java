package com.liuyu.projectmanagement.utils;

import org.springframework.util.StringUtils;

public class ProjectUtils {

    public final static String ERROR_MESSAGE_IN_SAVE = "Error occur where save data!";
    public final static String ERROR_MESSAGE_IN_UPDATE = "Error occur where update data!";
    public final static String ERROR_MESSAGE_IN_DELETE = "Error occur where remove data!";
    public final static String ERROR_MESSAGE_IN_QUERY = "Error occur where query data!";

    public static String formatKeyField(String key) {
        if (StringUtils.isEmpty(key)) return "";
        int i = key.indexOf("_");
        if (i > 0) {
            char ch= key.charAt(i + 1);
            return key.substring(0, i) + new String(String.valueOf(ch)).toUpperCase() + key.substring(i + 2);
        }
        return key;
    }
}
