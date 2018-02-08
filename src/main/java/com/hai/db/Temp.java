/**
 *
 */
package com.hai.db;

/**
 * @author Administrator
 */
public class Temp {
    static int count;

    public static void main(String[] args) {
        int num = 0;
        for (int i = 0; i < len(); i++) {
            num++;
        }
        System.out.println(num);
        System.out.println(count);
    }

    static int len() {
        count++;
        return 100;
    }

}
