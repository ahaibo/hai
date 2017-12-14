package com.hai.test.file;

class AAA implements Runnable {

    public static void main(String[] args) {
        try {
            abc();
            System.out.println(1);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(2);
        } catch (NullPointerException e) {
            System.out.println(3);
        } finally {
            System.out.println(4);
        }

    }

    /**
     *
     */
    private static void abc() {
        return;
    }

    /**
     * @param sb1
     * @param sb2
     */
    static void operate(StringBuffer sb1, StringBuffer sb2) {
        sb1.append(sb2);
        System.out.println("a " + sb1);
        sb2 = sb1;
        System.out.println("b " + sb2);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        System.out.println("ok");

    }
}