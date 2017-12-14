/**
 *
 */
package com.hai.test;

/**
 * @author as
 */
public class TestInit {
    private int a = 1;
    private int b;
    private static String NAME = "hai";

    {
        b = 2;
        System.out.println("init blocks..");
    }

    static {
        NAME = "handsome";
        System.out.println("static init blocks..");
    }

    public TestInit() {
        System.out.println("default constructor...");
    }

    public void init() {
        System.out.println("init...");
    }

    public void cinit() {
        System.out.println("cinit...");
    }

    public static void main(String[] args) {
        // System.out.println(TestInit.NAME);
        TestInit instance = new TestInit();
        System.out.println(instance);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + a;
        result = prime * result + b;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TestInit other = (TestInit) obj;
        if (a != other.a)
            return false;
        if (b != other.b)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TestInit [a=" + a + ", b=" + b + "]";
    }
}
