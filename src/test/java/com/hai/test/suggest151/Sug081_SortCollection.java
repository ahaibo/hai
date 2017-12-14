package com.hai.test.suggest151;

import org.junit.Test;

import java.util.*;

/**
 * Created by Administrator on 2017/9/30.
 */
public class Sug081_SortCollection {
    public static void main(String[] args) {
        SortedSet<Person> sortedSet = new TreeSet<>();
        sortedSet.add(new Person(1, 180));
        sortedSet.add(new Person(2, 175));

        sortedSet.first().setHeight(185);
        print(sortedSet);

        sortedSet = new TreeSet<>(new ArrayList<>(sortedSet));
        System.out.println("after sorted...");
        print(sortedSet);
    }

    @Test
    public void test1() {
        List<Person> list = new ArrayList<>();
        list.add(new Person(1, 180));
        list.add(new Person(2, 175));

        System.out.println("before modify...");
        print(list);

        Collections.sort(list);

        System.out.println("after modify...");
        print(list);

    }

    public static <T> void print(Collection<T> collections) {
        for (T t : collections) {
            System.out.println(t);
        }
    }
}

class Person implements Comparable<Person> {
    private int id;
    private int height;

    public Person(int id, int height) {
        this.id = id;
        this.height = height;
    }

    @Override
    public int compareTo(Person o) {
        return height - o.height;
    }

    @Override
    public String toString() {
        return "Person:id=" + this.id + ";height=" + this.height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
