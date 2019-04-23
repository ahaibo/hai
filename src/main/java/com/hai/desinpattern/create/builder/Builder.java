package com.hai.desinpattern.create.builder;

/**
 * 抽象建造者
 * Created by Administrator on 2018/2/2.
 */
public abstract class Builder {
    Product product = new Product();

    protected abstract void buildId();

    protected abstract void buildAge();

    protected abstract void buildName();

    protected abstract void buildFunction();

    public Product getProduct() {
        return product;
    }
}
