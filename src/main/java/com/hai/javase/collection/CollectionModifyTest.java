package com.hai.javase.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Administrator on 2017/12/6.
 */
public class CollectionModifyTest {
    Collection collection = null;

    public void init() {
        collection.add("zhanggsan");
        collection.add("lisi");
        collection.add("wangwu");
    }

    public void iterate(String val) {
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            Object name = iterator.next();
            if (val.equals(name)) {
                collection.remove(name);
            } else {
                System.out.println(name);
            }
        }
    }

    @Test
    public void test1() {
        collection = new ArrayList();//常规集合不能修改数据，操作的是原数组
        init();
        iterate("wangwu");
    }

    @Test
    public void test2() {
        collection = new CopyOnWriteArrayList();//CopyOnWriteArrayList，没错新建数组
        init();
        iterate("wangwu");
    }
}
