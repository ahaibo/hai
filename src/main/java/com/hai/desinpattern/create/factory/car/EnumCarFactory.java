package com.hai.desinpattern.create.factory.car;

/**
 * Created by Administrator on 2017/9/30.
 */
public enum EnumCarFactory {
    FORD, BUICK;

    public Car create() {
        Car car = null;
        switch (this) {
            case FORD:
                car = new FordCar();
                break;
            case BUICK:
                car = new BuickCar();
                break;
        }
        return car;
    }
}
