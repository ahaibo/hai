/**
 *
 */
package com.hai.javase.servlet.iusermanager.entity;

import java.io.Serializable;

/**
 * @author Administrator
 */
public class User implements Serializable {

    private static final long serialVersionUID = -4877228422088739950L;
    private int id;
    private String name;
    private String pass;
    private String sex;
    private int age;
    private String email;
    private String hobby;

    public User() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param name
     * @param sex
     * @param age
     */
    public User(String name, String pass, String sex, int age) {
        this.name = name;
        this.pass = pass;
        this.sex = sex;
        this.age = age;
    }

    /**
     * @param id
     * @param name
     * @param sex
     * @param age
     * @param email
     * @param hobby
     */
    public User(String name, String pass, String sex, int age, String email, String hobby) {
        this(name, pass, sex, age);
        this.email = email;
        this.hobby = hobby;
    }

    public User(int id, String name, String pass, String sex, int age, String email, String hobby) {
        this(name, pass, sex, age, email, hobby);
        this.id = id;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the hobby
     */
    public String getHobby() {
        return hobby;
    }

    /**
     * @param hobby the hobby to set
     */
    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

}
