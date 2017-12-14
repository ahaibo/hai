package com.hai.test;


import org.junit.Test;

import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/5/2.
 */
public class TestPriority {
    public static void main(String[] args) {
        int x = 0, y = 1;
        if (++x == y-- & x++ == 1 || --y == 0)
            System.out.println("x=" + x + ",y=" + y);//x = 2,y = 0;
        else
            System.out.println("y=" + y + ",x=" + x);
    }

    @Test
    public void test() {
        List list = Collections.emptyList();//此方式返回的list不可增删操作
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(1);
        System.out.println(list);

        List list2 = Collections.emptyList();
        System.out.println(list);
        System.out.println(list2);
        list2.add(5);
        list2.add(6);
        list2.add(7);
        System.out.println(list2);
    }
}
