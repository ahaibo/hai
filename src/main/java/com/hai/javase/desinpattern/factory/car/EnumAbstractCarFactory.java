package com.hai.javase.desinpattern.factory.car;

/**
 * Created by Administrator on 2017/9/30.
 */
public enum EnumAbstractCarFactory {
    FORD {
        @Override
        public Car create() {
            return new FordCar();
        }
    }, BUICK {
        @Override
        public Car create() {
            return new BuickCar();
        }
    };

    public abstract Car create();
}
