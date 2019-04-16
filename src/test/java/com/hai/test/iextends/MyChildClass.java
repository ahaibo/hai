package com.hai.test.iextends;

public class MyChildClass extends MySuperClass {
    private int id;
    private String name;
    private static int COUNT = 11;
    public static int NUM = 22;

    {
        id = 0;
        System.out.println(this.getClass().getName() + " non static block init...");
    }

    static {
        System.out.println(MyChildClass.class.getName() + " static block init...");
    }

    public void show() {
        System.out.println("child.id:" + this.id + ";child.name:" + this.name);
        System.out.println("child.count:" + COUNT + ";child.num:" + NUM);
        System.out.println("\n");
    }

    public void show2() {
        System.out.println("child.show2.id:" + this.id + ";child.show2.name:" + this.name);
        System.out.println("child.show2.count:" + COUNT + ";child.show2.num:" + NUM);
        System.out.println("\n");
    }

    public void show3() {
        System.out.println(this.getClass().getName() + ".show3...");
        super.show();
    }

    public MyChildClass line() {
        System.out.println("\n");
        return this;
    }

    public static void incrementCount2() {
        System.out.println(MyChildClass.class.getName() + ".incrementCount2...");
        MySuperClass.incrementCount();
    }

    public static void incrementCount() {
        System.out.println(MyChildClass.class.getName() + ".incrementCount...");
        System.out.println("child.count:" + COUNT + ";child.num:" + NUM);
        System.out.println("\n");
    }

    public static void main(String[] args) {
        incrementCount();
        incrementCount2();
        MyChildClass childClass = new MyChildClass();
//        childClass.show();
//        childClass.show2();
//        childClass.show3();

        MyChildClass childClass2 = new MyChildClass();
        System.out.println(childClass.equals(childClass2));
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println(this.getClass().getName() + ".equals...");
        return super.equals(obj);
    }

    public boolean equals(Object obj, Object obj2) {
        System.out.println(this.getClass().getName() + ".equals2...");
        return obj.equals(obj);
    }
}
