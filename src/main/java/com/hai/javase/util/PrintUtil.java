/**
 *
 */
package com.hai.javase.util;

import java.util.Enumeration;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * @author Administrator
 */
public class PrintUtil {
    /**
     * @param map
     */
    public static void printMap(Map<Object, Object> map) {
        Set<Entry<Object, Object>> entrySet = map.entrySet();
        for (Entry<Object, Object> entry : entrySet) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void printMap(String title, Map<Object, Object> map) {
        System.out.println(title.concat("\n"));
        printMap(map);
    }

    public static void printArray(Object[] array) {
        for (Object object : array) {
            System.out.println(object);
        }
    }

    public static void printArray(String title, Object[] array) {
        System.out.println(title.concat("\n"));
        printArray(array);
    }

    public static void printEnumeration(Enumeration<Object> enumeration) {
        while (enumeration.hasMoreElements()) {
            Object object = enumeration.nextElement();
            System.out.println(object);
        }
    }

    public static void printArray(String title, Enumeration<Object> enumeration) {
        System.out.println(title.concat("\n"));
        printEnumeration(enumeration);
    }

}
