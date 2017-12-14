package com.hai.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/11/20.
 */
//使用自定义序列化
//@JsonSerialize(using = CustomSerializer.class)
//@JsonDeserialize(using = CustomDeserializer.class)
public class Person implements Serializable {
    private int id;
    private String name;
    private int age;
    private String hobby;
    private Address address;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
