package com.hai.javase.java8.ibm;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

/**
 * Java 8 习惯用语，第 4 部分
 * 提倡使用有帮助的编码
 * Java 8 约定令人惊喜的好处:垂直对其
 * 略
 * <p>
 * Java 8 习惯用语，第 5 部分
 * 传递表达式（pass-through lambdas）的替代方案
 * Created by Administrator on 2017/11/19.
 */
public class PassThroughLambdas {

    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    public int increment(int number) {
        return number + 1;
    }

    public static int staticIncrement(int number) {
        return number + 1;
    }

    @Test
    public void test1() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        numbers.stream()
                .filter(e -> e % 2 == 0)
                .forEach(e -> System.out.println(e));
//        在此代码中，我们还将一个 lambda 表达式传递给了 forEach 方法。尽管这两个 lambda 表达式明显具有不同作用，但它们之间还有另一个重要的细微区别：
//        第一个 lambda 表达式实际执行一些工作，而第二个没有。
//        传递给 forEach 方法的 lambda 表达式就是我们所称的传递 lambda 表达式。表达式 e -> System.out.println(e)将它的形参作为实参传递给 PrintStream 类的 println 方法，该方法是 System.out 实例。
    }

    @Test
    public void test2() {
        numbers.stream()
                .filter(e -> e % 2 == 0)
                .forEach(System.out::println);//将传递 lambda 表达式改为方法引用
    }

    @Test
    public void test3() {
        System.out.println(numbers.stream()
                .map(e -> increment(e))
                .count());

        numbers.stream()
                .map(e -> this.increment(e));

        //方法引用解决冗余问题
        numbers.stream()
                .map(this::increment)
                .collect(Collectors.toCollection(ArrayList::new));//构造方法引用

        //ClassName::staticMethodName
        long result = numbers.stream()
                .map(PassThroughLambdas::staticIncrement)
                .count();
        System.out.println(result);
    }

    public void test4() {
        List<String> names = Arrays.asList("Dory", "Gill", "Bruce", "Nemo", "Darla", "Marlin", "Jacques");
        List<String> nonNullNamesInUpperCase = names
                .stream()
                .filter(name -> Objects.nonNull(name))
                .map(name -> name.toUpperCase())
                .collect(collectingAndThen(toList(), list -> Collections.unmodifiableList(list)));

        //使用方法引用
        List<String> nonNullNamesInUpperCase2 = names
                .stream()
                .filter(Objects::nonNull)
                .map(String::toUpperCase)
                .collect(collectingAndThen(toList(), Collections::unmodifiableList));

//        只要看到一个 lambda 表达式的唯一目的是将形参传递给一个或多个其他函数，就需要考虑将该 lambda 表达式替换为方法引用是否更好。
//        决定因素在于，lambda 表达式内没有完成任何实际工作。在这种情况下，lambda 表达式就是一个传递表达式，而且它的语法对于当前这个任务而言可能过于复杂了。
    }

    /**
     * 充满危险的长 lambda 表达式
     * 尽管此代码是用函数式风格编写的，但它丢失了函数式编程的优点。让我们考虑一下原因何在。
     * 1.难以读懂
     * 2.用途不明
     * 3.代码质量差
     * 4.难以测试
     * 5.代码覆盖范围小
     */
    public void testDangerousLambda() {
        System.out.println(
                numbers.stream()
                        .mapToInt(e -> {
                            int sum = 0;
                            for (int i = 1; i <= e; i++) {
                                if (e % i == 0) {
                                    sum += i;
                                }
                            }

                            return sum;
                        })
                        .sum());
    }

    //    完美的 lambda 表达式只有一行
    //    使代码更容易阅读、测试和重用的单行 lambda 表达式
    public void testDangerousLambda2() {
        System.out.println(
                numbers.stream()
                        .mapToInt(e -> sumOfFactors(e))
                        .sum());
    }

    public int sumOfFactors(int number) {
        return IntStream.rangeClosed(1, number)
                .filter(i -> number % i == 0)
                .sum();
    }

//    简短的 lambda 表达式能提高代码可读性，这是函数式编程的重要好处之一。包含多行的 lambda 表达式具有相反的效果，会让代码变得杂乱且难以阅读。
//    多行 lambda 表达式还难以测试和重用，这可能导致重复工作和代码质量差。幸运的是，通过将多行 lambda 表达式的主体转移到一个命名函数中，
//    然后从 lambda 表达式内调用该函数，这样很容易避免这些问题。我也推荐尽可能将 lambda 表达式替换为方法引用。
//    简言之，我推荐避免多行 lambda 表达式，除非是为了演示它们为什么不好。

}
