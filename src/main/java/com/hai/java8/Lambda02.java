/**
 *
 */
package com.hai.java8;

import org.junit.Test;

/**
 * lambda带参语法
 *
 * @author as
 */
public class Lambda02 {

    public static void main(String[] args) {

        new IAction() {

            @Override
            public void execute(String content) {
                System.out.println(content);
            }
        }.execute("jdk1.8之前用匿名内部类实现，执行登陆操作");

        IAction login = (String content) -> {
            System.out.println(content);
        };

        login.execute("jdk1.8的lambda语法实现登陆操作");
    }

    static interface IAction {

        default void hello() {
            System.out.println("default method hello in interface in jdk8...");
        }

        default void hello2() {
            System.out.println("default method hello2 in interface in jdk8...");
        }

        static void say() {
            System.out.println("static method say in interface...");
        }

        static void say2() {
            System.out.println("static method say2 in interface...");
        }

        void execute(String content);

//        void execute(int count, String content);
    }

    static class DrawAction implements IAction {
//        @Override
//        public void hello2() {
//            System.out.println(this.getClass().getName() + ".hello2...");
//        }

        @Override
        public void execute(String content) {
            System.out.println(this.getClass().getName() + ".execute...content is: " + content);
        }
    }

    @Test
    public void test() {
        DrawAction drawAction = new DrawAction();
        drawAction.hello();
        drawAction.hello2();
        drawAction.execute("hello!");
    }
}
