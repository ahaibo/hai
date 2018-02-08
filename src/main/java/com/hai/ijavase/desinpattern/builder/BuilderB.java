package com.hai.ijavase.desinpattern.builder;

import java.util.Random;

/**
 * Created by Administrator on 2018/2/2.
 */
public class BuilderB extends Builder {
    @Override
    protected void buildId() {
        System.out.println(this.getClass().getName() + ".buildId...");
        this.product.setId(new Random().nextInt());
    }

    @Override
    protected void buildAge() {
        System.out.println(this.getClass().getName() + ".buildAge...");
        this.product.setAge(new Random().nextInt());
    }

    @Override
    protected void buildName() {
        System.out.println(this.getClass().getName() + ".buildName...");
        this.product.setName(this.getClass().getName());
    }

    @Override
    protected void buildFunction() {
        System.out.println(this.getClass().getName() + ".buildFunction...");
        this.product.setFunction("FunctionB");
    }
}
