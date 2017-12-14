package com.hai.test.suggest151;

import java.util.Random;

/**
 * Created by Administrator on 2017/9/28.
 */
public class Sug002_Const2Var {

    public static void main(String[] args) {
        System.out.println(Const.RAND_CONST);
    }

}

interface Const {
    int RAND_CONST = new Random().nextInt();
}
