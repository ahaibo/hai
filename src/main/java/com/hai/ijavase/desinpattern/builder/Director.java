package com.hai.ijavase.desinpattern.builder;

/**
 * 指挥(导演)类，负责根据指定的建造者进行建造、创建
 * Created by Administrator on 2018/2/2.
 */
public class Director {
    private Builder builder;

    public void setBuilder(Builder builder) {
        this.builder = builder;
    }

    //构建
    public Product construct() {
        //构建产品
        this.builder.buildId();
        this.builder.buildAge();
        this.builder.buildName();
        this.builder.buildFunction();
        //返回构建好的产品
        return this.builder.getProduct();
    }
}
