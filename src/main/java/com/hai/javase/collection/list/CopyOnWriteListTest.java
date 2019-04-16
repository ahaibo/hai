package com.hai.javase.collection.list;

import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteListTest {
    public static void main(String[] args) {
        List<String> myList = new CopyOnWriteArrayList();
        myList.add("1");
        myList.add("2");
        myList.add("3");
        myList.add("4");
        myList.add("5");

        new Thread(new Runnable() {

            @Override
            public void run() {
                for (String string : myList) {
                    System.out.println("遍历集合 value = " + string);

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {

                for (int i = 0; i < myList.size(); i++) {
                    String value = myList.get(i);

                    System.out.println("删除元素 value = " + value);

                    if (value.equals("3")) {
                        myList.remove(value);
                        i--; // 注意
                    }

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Test
    public void test() {
        List<String> myList = new CopyOnWriteArrayList();
        myList.add("1");
        myList.add("2");
        myList.add("3");
        myList.add("4");
        myList.add("5");

        for (String val : myList) {
            if ("3".equals(val)) {
                myList.remove(val);
            }
            System.out.println("list: " + val);
        }

        Iterator<String> iterator = myList.iterator();
        while (iterator.hasNext()) {
            String val = iterator.next();
            if ("3".equals(val)) {
                iterator.remove();
            }
            System.out.println("iterator: " + val);
        }
    }
}
