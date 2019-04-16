package com.hai.test.iextends;

public class MySuperClass {

    private int id;
    private String name;
    private static int COUNT = 1;
    public static int NUM = 2;

    {
        id = 0;
        System.out.println(this.getClass().getName() + " non static block init...");
    }

    static {
        System.out.println(MySuperClass.class.getName() + " static block init...");
    }

    public MySuperClass() {
        System.out.println(this.getClass().getName() + ".constructor1...");
        System.out.println("\n");
    }

    public MySuperClass(int id, String name) {
        this.id = id;
        this.name = name;
        System.out.println(this.getClass().getName() + ".constructor2...");
        System.out.println("\n");
    }

    public void show() {
        System.out.println("super.id:" + this.id + ";super.name:" + this.name);
        System.out.println("super.count:" + COUNT + ";super.num:" + NUM);
        System.out.println("\n");
    }

    public static void incrementCount() {
        System.out.println(MySuperClass.class.getName() + ".incrementCount...");
        System.out.println("super.count:" + COUNT + ";super.num:" + NUM);
        System.out.println("\n");
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

}
