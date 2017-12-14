package com.hai.ijavase.desinpattern.decorator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

/**
 * 装饰模式
 * Created by Administrator on 2017/9/30.
 */
public class DecoratorPattern {
}

class DecoratorAnimal implements Animal {
    private Animal animal;
    private Class<? extends Feature> feature;

    public DecoratorAnimal() {
    }

    public DecoratorAnimal(Animal animal, Class<? extends Feature> feature) {
        this.animal = animal;
        this.feature = feature;
    }

    @Override
    public void doStuff() {
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object result = null;
                if (Modifier.isPublic(method.getModifiers())) {
                    result = method.invoke(feature.newInstance(), args);
                }
                animal.doStuff();
                return result;
            }
        };

        Feature proxy = (Feature) Proxy.newProxyInstance(getClass().getClassLoader(), feature.getInterfaces(), handler);

        proxy.load();
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Class<? extends Feature> getFeature() {
        return feature;
    }

    public void setFeature(Class<? extends Feature> feature) {
        this.feature = feature;
    }


}

interface Animal {
    void doStuff();
}

class Rat implements Animal {
    @Override
    public void doStuff() {
        System.out.println("Jerry will play with Tom.");
    }
}

interface Feature {
    void load();
}

class FlyFeature implements Feature {
    @Override
    public void load() {
        System.out.println("fly feature...");
    }
}

class DigFeature implements Feature {
    @Override
    public void load() {
        System.out.println("dig feature...");
    }
}