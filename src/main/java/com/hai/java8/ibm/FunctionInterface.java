package com.hai.java8.ibm;

import com.hai.java8.ibm.model.OrderItem;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

/**
 * 函数接口有 3 条重要法则：
 * 1.一个函数接口只有一个抽象方法。
 * 2.* 在 Object 类中属于公共方法的抽象方法不会被视为单一抽象方法。
 * 3.函数接口可以有默认方法和静态方法。
 * 任何满足单一抽象方法法则的接口，都会被自动视为函数接口。这包括 Runnable 和 Callable 等传统接口，以及您自己构建的自定义接口。
 * <p>
 * 自定义函数接口
 * 要创建自己的函数接口，需要做两件事：
 * 1.使用 @FunctionalInterface 注释该接口，这是 Java 8 对自定义函数接口的约定。
 * 2.确保该接口只有一个抽象方法。
 * <p>
 * <p>
 * 将 lambda 表达式设置为函数接口类型的设计决策，有助于在 Java 8 与早期 Java 版本之间实现向后兼容性。
 * 可以将 lambda 表达式传递给任何通常接收单一抽象方法接口的旧函数。要接收 lambda 表达式，方法的参数类型应为函数接口。
 * Created by Administrator on 2017/11/19.
 */
public class FunctionInterface {

    @Test
    public void test() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                System.out.println("In another thread");
            }
        });
        thread.start();

        Thread thread2 = new Thread(() -> System.out.println("In another thread with functional interface"));
        thread2.start();

        System.out.println("In main");
    }

    //自定义函数式接口
    @FunctionalInterface
    public interface Transformer<T> {
        T transform(T input);
    }


    public class Order {
        List<OrderItem> items;

        public Order(List<OrderItem> orderItems) {
            items = orderItems;
        }

        public void transformAndPrint(Transformer<Stream<OrderItem>> transformOrderItems) {
            transformOrderItems
                    .transform(items.stream())
                    .forEach(System.out::println);
        }

        //用 JDK 的内置函数接口 Function<T, R> 可以取代 Transformer
        public void transformAndPrint2(Function<Stream<OrderItem>, Stream<OrderItem>> transformOrderItems) {
            transformOrderItems
                    .apply(items.stream())
                    .forEach(System.out::println);
        }
    }

    @Test
    public void test2() {
        Order order = new Order(Arrays.asList(
                new OrderItem(1, 1225),
                new OrderItem(2, 983),
                new OrderItem(3, 1554)
        ));

        order.transformAndPrint(new Transformer<Stream<OrderItem>>() {
            public Stream<OrderItem> transform(Stream<OrderItem> orderItems) {
                return orderItems.sorted(comparing(OrderItem::getPrice));
            }
        });
    }

    @Test
    public void test3() {
        Order order = new Order(Arrays.asList(
                new OrderItem(1, 1225),
                new OrderItem(2, 983),
                new OrderItem(3, 1554)
        ));

        //对 transformAndPrint 的调用使用了一个匿名内部类，改为 lambda 表达式
        order.transformAndPrint2(orderItems -> orderItems.sorted(comparing(OrderItem::getPrice)));
    }

    @Test
    public void test4() {
        test2();
        System.out.println("=================");
        test3();
    }
}
