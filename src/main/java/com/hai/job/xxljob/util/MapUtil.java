package com.hai.job.xxljob.util;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

public class MapUtil {
    public MapUtil() {
    }

    public static MultiValueMap<String, String> obj2Map(Object obj) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap();
        Field[] fields = obj.getClass().getDeclaredFields();
        int i = 0;

        for (int len = fields.length; i < len; ++i) {
            String varName = fields[i].getName();

            try {
                boolean accessFlag = fields[i].isAccessible();
                fields[i].setAccessible(true);
                Object o = fields[i].get(obj);
                if (o != null) {
                    map.put(varName, Collections.singletonList(o.toString()));
                }

                fields[i].setAccessible(accessFlag);
            } catch (IllegalArgumentException var8) {
                var8.printStackTrace();
            } catch (IllegalAccessException var9) {
                var9.printStackTrace();
            }
        }

        return map;
    }

    public static MultiValueMap<String, String> obj2MapWithNull(Object obj) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap();
        Field[] fields = obj.getClass().getDeclaredFields();
        int i = 0;

        for (int len = fields.length; i < len; ++i) {
            String varName = fields[i].getName();

            try {
                boolean accessFlag = fields[i].isAccessible();
                fields[i].setAccessible(true);
                Object o = fields[i].get(obj);
                if (o != null) {
                    map.put(varName, Collections.singletonList(o.toString()));
                } else {
                    map.put(varName, null);
                }

                fields[i].setAccessible(accessFlag);
            } catch (IllegalArgumentException var8) {
                var8.printStackTrace();
            } catch (IllegalAccessException var9) {
                var9.printStackTrace();
            }
        }

        return map;
    }

    public static MultiValueMap<String, String> obj2MapWithString(Object obj) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap();
        Field[] fields = obj.getClass().getDeclaredFields();
        int i = 0;

        for (int len = fields.length; i < len; ++i) {
            String varName = fields[i].getName();

            try {
                boolean accessFlag = fields[i].isAccessible();
                fields[i].setAccessible(true);
                Object o = fields[i].get(obj);
                if (o != null) {
                    map.put(varName, Collections.singletonList(o.toString()));
                } else {
                    map.put(varName, Collections.singletonList(""));
                }

                fields[i].setAccessible(accessFlag);
            } catch (IllegalArgumentException var8) {
                var8.printStackTrace();
            } catch (IllegalAccessException var9) {
                var9.printStackTrace();
            }
        }

        return map;
    }
}