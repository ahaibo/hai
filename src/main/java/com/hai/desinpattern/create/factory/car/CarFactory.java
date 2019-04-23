package com.hai.desinpattern.create.factory.car;

/**
 * Created by Administrator on 2017/9/30.
 */
public class CarFactory {
    private CarFactory() {
    }

    public static Car create(Class<? extends Car> clazz) {
        try {
            clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}

class FordCar implements Car {
}

class BuickCar implements Car {
}
