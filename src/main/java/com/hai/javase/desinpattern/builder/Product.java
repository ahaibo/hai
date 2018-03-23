package com.hai.javase.desinpattern.builder;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 产品类
 * Created by Administrator on 2018/2/2.
 */
public class Product {
    private int id;
    private int age;
    private String name;
    private String function;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this);
    }
}
