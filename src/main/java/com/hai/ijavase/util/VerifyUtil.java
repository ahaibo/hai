package com.hai.ijavase.util;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyUtil {

    public static boolean isEmpty(String str) {
        return null == str || str.isEmpty();
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isEmpty(Object obj) {
        return null == obj;
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return null == map || map.isEmpty();
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static boolean isEmpty(Collection<?> collection) {
        return null == collection || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static boolean isEmpty(Object[] arr) {
        return null == arr || arr.length == 0;
    }

    public static boolean isNotEmpty(Object[] arr) {
        return !isEmpty(arr);
    }

    /**
     * 判断一个字符是否是数值
     *
     * @param str
     * @return
     */
    public static boolean isNumerialNumber(String str) {
        Pattern pattern = Pattern.compile("[0-9]+");

        Matcher matcher = pattern.matcher(str);

        // 简单方式：str.matches("[0-9]+");

        return matcher.matches();
    }

    public static boolean isNotNumerialNumber(String str) {
        return !isNumerialNumber(str);
    }

}
