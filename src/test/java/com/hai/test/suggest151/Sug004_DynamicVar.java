package com.hai.test.suggest151;

import java.text.NumberFormat;

/**
 * 建议04: 避免带有变长参数的方法重载
 * Created by Administrator on 2017/9/28.
 */
public class Sug004_DynamicVar {

    public static void main(String[] args) {
        new Sug004_DynamicVar().calPrice(4990, 76);
    }

    public void calPrice(int price, int discount) {
        float knockdownPrice = price * discount / 100.0F;
        System.out.println("简单折扣计算后的价格：" + formatCurrency(knockdownPrice));
    }

    public void calPrice(int price, int... discounts) {
        float knockdownPrice = price;
        for (int discount : discounts) {
            knockdownPrice = price * discount / 100.0F;
        }
        System.out.println("复杂折扣计算后的价格：" + formatCurrency(knockdownPrice));
    }

    public String formatCurrency(float price) {
        return NumberFormat.getCurrencyInstance().format(price / 100);
    }
}
