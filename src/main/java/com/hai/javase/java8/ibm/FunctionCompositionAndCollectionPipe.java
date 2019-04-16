package com.hai.javase.java8.ibm;

import com.hai.common.util.RandomUtil;
import com.hai.javase.java8.ibm.model.Car;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toCollection;


/**
 * Java 8 习惯用语，第 2 部分
 * 函数组合与集合管道模式
 * Created by Administrator on 2017/11/19.
 */
public class FunctionCompositionAndCollectionPipe {

    public static final int MIN_YEAR = 1000;
    public static final int MAX_YEAR = 5000;
    public static final int FILTER_YEAR = 2000;
    public static final int LOOP_COUNT = 10000 * 100;

    public static List<Car> cars = new ArrayList<>();

    static {
        long start = System.nanoTime();
        int years = 0;
        for (int i = 0; i < LOOP_COUNT; i++) {
            int year = RandomUtil.random(MIN_YEAR, MAX_YEAR);
            if (year > FILTER_YEAR) {
                years += 1;
            }
            cars.add(new Car("make" + year, "model" + year, year));
        }
        long end = System.nanoTime();
        long time = end - start;
        System.out.println("init cars used times: " + time);
        System.out.println("filter years: " + years + "\n");
    }

    public static List<String> getModelsAfter2000UsingFor(List<Car> cars) {
        List<Car> carsSortedByYear = new ArrayList<>();

        for (Car car : cars) {
            if (car.getYear() > FILTER_YEAR) {
                carsSortedByYear.add(car);
            }
        }

        Collections.sort(carsSortedByYear, new Comparator<Car>() {
            public int compare(Car car1, Car car2) {
                return new Integer(car1.getYear()).compareTo(car2.getYear());
            }
        });

        List<String> models = new ArrayList<>();
        for (Car car : carsSortedByYear) {
            models.add(car.getModel());
        }

        return models;
    }

    /**
     * 使用集合管道进行迭代和排序
     * 在函数编程中，通常会通过一系列更小的模块化函数或运算来对复杂运算进行排序。该系列被称为函数组合（composition of functions, or a function composition）。
     * 当一个数据集合流经一个函数组合时，它就变成一个集合管道。函数组合和集合管道是函数式编程中常用的两种设计模式。
     *
     * @param cars
     * @return
     */
    public static List<String> getModelsAfter2000UsingPipeline(List<Car> cars) {
        return cars.stream()
                .filter(car -> car.getYear() > FILTER_YEAR)
                .sorted(Comparator.comparing(Car::getYear))
                .map(Car::getModel)
                .collect(toCollection(ArrayList::new));//将构造函数调用替换为方法引用 -  lambda 表达式替换为对 new 的方法引用
//                .collect(toList());
    }

    @Test
    public void test1() {
        long start = System.nanoTime();
        getModelsAfter2000UsingFor(cars);
        long end = System.nanoTime();
        long time = end - start;
        System.out.println("getModelsAfter2000UsingFor used time:\t\t" + time);
    }

    @Test
    public void test2() {
        long start = System.nanoTime();
        getModelsAfter2000UsingPipeline(cars);
        long end = System.nanoTime();
        long time = end - start;
        System.out.println("getModelsAfter2000UsingPipeline used time:\t" + time);
    }

    @Test
    public void test() {
        test1();
        test2();
    }
}
