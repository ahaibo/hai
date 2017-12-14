package com.hai.test.suggest151;

/**
 * Created by Administrator on 2017/10/1.
 */
public class Sug118_DeprecateOverrideStart {

    public static void main(String[] args) {
        MyThread thread = new MyThread();
//        thread.main();
//        thread.run();
        thread.start(true);
    }

    static class MyThread extends Thread {

        public synchronized void start(boolean isExtends) {
            if (isExtends) {
                super.start();
            }
            System.out.println("override main in thread...");
            run();
        }

        @Override
        public synchronized void start() {
//            super.main();
            System.out.println("override main in thread...");
            run();
        }

        @Override
        public void run() {
            System.out.println(getClass().getName() + ": " + this.getName() + " do something...");
        }
    }
}
