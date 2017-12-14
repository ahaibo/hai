package com.hai.test;

import org.junit.Test;

import java.util.*;

/**
 * Created by Administrator on 2017/9/30.
 */
public class TestCollection {

    //建议72: 生成子列表后不要再操作原列表
    @Test
    public void test1() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        List<String> subList = list.subList(1, 2);

        list.add("e");

        System.out.println("list.size: " + list.size());
        System.out.println("subList.size: " + subList.size());
    }

    @Test
    public void test2() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        List<String> subList = list.subList(1, 2);

        list = Collections.unmodifiableList(list);

        System.out.println("list items:");
        for (String str : list) {
            System.out.println(str);
        }

        System.out.println("subList items:");

        for (String str : subList) {
            System.out.println(str);
        }

        subList.add("e");
        System.out.println("subList.size: " + subList.size());
    }

    @Test
    public void test3() {
        List<String> list = new ArrayList<>();
        list.add("上海");
        list.add("广州");
        list.add("广州");
        list.add("北京");
        list.add("天津");

        int i1 = list.indexOf("广州");
        int i2 = Collections.binarySearch(list, "广州");
        System.out.println("list.indexOf: " + i1);
        System.out.println("Collections.binarySearch: " + i2);
    }

    @Test
    public void test4() {
        int size = 20000;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add("value" + i);
        }

        long start = System.nanoTime();
        list.contains("value" + (size - 1));
        long end = System.nanoTime();
        long time = end - start;
        System.out.println("list used time: " + time);

        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            map.put("key" + i, "value" + i);
        }
        long start2 = System.nanoTime();
        map.containsValue("value" + (size - 1));
        long end2 = System.nanoTime();
        long time2 = end2 - start2;
        System.out.println("map used time: " + time2);
    }
}
