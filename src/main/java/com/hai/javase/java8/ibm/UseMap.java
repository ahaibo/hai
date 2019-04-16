package com.hai.javase.java8.ibm;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Java 8 习惯用语，第 1 部分
 * Java 中的一种更轻松的函数式编程途径
 * 以声明方式思考如何在 Java 程序中采用函数方法
 */
public class UseMap {
    static Map<String, Integer> pageVisits = new HashMap<>();
    static String page = "https://agiledeveloper.com";

    public static void main(String[] args) {
        incrementPageVisit(pageVisits, page);
        incrementPageVisit(pageVisits, page);
        System.out.println(pageVisits.get(page));
    }

    public static void incrementPageVisit(Map<String, Integer> pageVisits, String page) {
        if (!pageVisits.containsKey(page)) {
            pageVisits.put(page, 0);
        }
        pageVisits.put(page, pageVisits.get(page) + 1);
    }

    /**
     * 函数式格式
     * 尽管函数式格式的编程始终是声明式的，但简单地使用声明式编程并不等于函数式编程。这是因为函数式编程合并了声明式方法与高阶函数。
     * <p>
     * Java 中的高阶函数
     * 在 Java 中，要将对象传递给方法，在方法内创建对象，并从方法中返回对象。可以对函数执行同样的操作。也就是说，可以将函数传递给方法，在方法内创建函数，并从方法返回函数。
     * <p>
     * 在本例中，并不仅仅通过选择更智能的方法采用更加声明式的格式编程；因为 merge() 是一个高阶函数，所以新代码实际上是一个不错的函数式编程示例：
     *
     * @param pageVisits
     * @param page
     */
    public static void incrementPageVisit2(Map<String, Integer> pageVisits, String page) {
        pageVisits.merge(page, 1, (oldValue, value) -> oldValue + value);
    }


    @Test
    public void test1() {
        incrementPageVisit(pageVisits, page);
        incrementPageVisit(pageVisits, page);
        System.out.println(pageVisits.get(page));
    }

    @Test
    public void test2() {
        incrementPageVisit2(pageVisits, page);
        incrementPageVisit2(pageVisits, page);
        System.out.println(pageVisits.get(page));
    }

    static class FindNemo {
        public static void main(String[] args) {
            List<String> names = Arrays.asList("Dory", "Gill", "Bruce", "Nemo", "Darla", "Marlin", "Jacques");

            findNemo(names);
        }

        /**
         * 命令格式
         */
        public static void findNemo(List<String> names) {
            boolean found = false;
            for (String name : names) {
                if (name.equals("Nemo")) {
                    found = true;
                    break;
                }
            }

            if (found)
                System.out.println("Found Nemo");
            else
                System.out.println("Sorry, Nemo not found");
        }

        /**
         * 声明式格式
         * 声明式编程意味着，您仍会告诉程序要做什么，但将实现细节留给底层的函数库。
         *
         * @param names
         */
        public static void findNemo2(List<String> names) {
            if (names.contains("Nemo"))
                System.out.println("Found Nemo");
            else
                System.out.println("Sorry, Nemo not found");
        }
    }
}