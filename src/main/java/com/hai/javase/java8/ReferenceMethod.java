package com.hai.javase.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * 方法引用
 *
 * @author as
 */
public class ReferenceMethod {

    public static void main(String[] args) {
        // 第一种方法引用的类型是构造器引用，语法是Class::new，或者更一般的形式：Class<T>::new。注意：这个构造器没有参数。
        Car car = Car.create(Car::new);
        List<Car> cars = Arrays.asList(car);

        // 第二种方法引用的类型是静态方法引用，语法是Class::static_method。注意：这个方法接受一个Car类型的参数。
        cars.forEach(Car::collide);

        // 第三种方法引用的类型是某个类的成员方法的引用，语法是Class::method，注意，这个方法没有定义入参。
        cars.forEach(Car::repair);

        // 第四种方法引用的类型是某个实例对象的成员方法的引用，语法是instance::method。注意：这个方法接受一个Car类型的参数。
        Car police = Car.create(Car::new);
        cars.forEach(police::follow);
    }

    public static class Car {

        // 第一种方法引用的类型是构造器引用，语法是Class::new，或者更一般的形式：Class<T>::new。注意：这个构造器没有参数。
        public static Car create(final Supplier<Car> supplier) {
            return supplier.get();
        }

        // 第二种方法引用的类型是静态方法引用，语法是Class::static_method。注意：这个方法接受一个Car类型的参数。
        public static void collide(final Car car) {
            System.out.println("Collided " + car.toString());
//            System.out.println("Collided " + Car.hello("hai"));
        }

        public static String hello(String name) {
//            System.out.println("hello, " + name);
            return "hello, " + name;
        }

        // 第三种方法引用的类型是某个类的成员方法的引用，语法是Class::method，注意，这个方法没有定义入参。
        public void repair() {
            System.out.println("Repaired " + this.toString());
        }

        // 第四种方法引用的类型是某个实例对象的成员方法的引用，语法是instance::method。注意：这个方法接受一个Car类型的参数。
        public void follow(final Car another) {
            System.out.println("Following the " + another.toString());
        }
    }
}