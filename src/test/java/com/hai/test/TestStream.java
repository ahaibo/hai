package com.hai.test;

import com.hai.model.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * description
 *
 * @author hai
 * @date 2020/12/11 11:13
 */
public class TestStream {

    private static final Logger log = LoggerFactory.getLogger(TestStream.class);

    @Test
    public void test1() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6);
        Stream.iterate(0, x -> x + 2).limit(6).forEach(System.out::println);
    }

    @Test
    public void test2() {
        Random random = new Random();
        Stream.generate(Math::random).limit(3).forEach(System.out::println);
        Stream.generate(random::nextInt).limit(3).forEach(System.out::println);
    }

    @Test
    public void test3() throws FileNotFoundException {
        String filePath = "E:\\notes\\we\\lottery\\data-fc.txt";
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        reader.lines().forEach(System.out::println);
    }

    @Test
    public void test4() {
        Pattern pattern = Pattern.compile(",");
        pattern.splitAsStream("1,2,3,4,5").forEach(System.out::println);
    }

    @Test
    public void test5() {
        Stream<Integer> stream = Stream.of(6, 4, 6, 7, 3, 9, 8, 10, 12, 14, 14);
        stream
                .distinct()
                .filter(i -> i % 2 == 0)
                .skip(2)
                .limit(2)
                .forEach(System.out::println);
    }

    @Test
    public void test6() {
        /*
        映射
        map：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
        flatMap：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
        */
        List<String> list = Arrays.asList("a,b,c", "1,2,3");
        list.stream().map(s -> s.replaceAll(",", "")).forEach(System.out::println);

        System.out.println("==================================================");
        list.stream().flatMap(s -> {
            String[] arr = s.split(",");
            return Arrays.stream(arr);
        }).forEach(System.out::println);
    }

    @Test
    public void test7() {
        /*
        排序
        sorted()：自然排序，流中元素需实现Comparable接口
        sorted(Comparator com)：定制排序，自定义Comparator排序器
        */
        List<String> list = Arrays.asList("aa", "ff", "dd");
        list.stream().sorted().forEach(System.out::println);

        System.out.println("==================================================");
        List<User> users = new ArrayList<>();
        users.add(new User(1, "aa", 20));
        users.add(new User(2, "bb", 28));
        users.add(new User(3, "aa", 18));
        users.add(new User(4, "cc", 22));
        users.add(new User(5, "dd", 23));

        users.stream().sorted((o1, o2) -> {
            if (o1.getName().equals(o2.getName())) {
                return o1.getAge() - o2.getAge();
            }
            return o1.getName().compareTo(o2.getName());
        }).forEach(System.out::println);
    }

    @Test
    public void test8() {
        /*
        排序
        peek：如同于map，能得到流中的每一个元素。但map接收的是一个Function表达式，有返回值；而peek接收的是Consumer表达式，没有返回值。
        */
        List<User> users = new ArrayList<>();
        users.add(new User(1, "aa", 20));
        users.add(new User(2, "bb", 28));
        users.add(new User(3, "aa", 18));
        users.add(new User(4, "cc", 22));
        users.add(new User(5, "dd", 23));

        users.stream().peek(o -> {
            o.setId(0);
            o.setAge(30);
        }).forEach(System.out::println);
    }

    @Test
    public void test9() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(list.stream().count());
        System.out.println(list.stream().min(Integer::compareTo).get());
        System.out.println(list.stream().max(Integer::compareTo).get());
        System.out.println(list.stream().findFirst().get());
        System.out.println(list.stream().findAny().get());
        System.out.println(list.stream().allMatch(i -> i > 3));
        System.out.println(list.stream().noneMatch(i -> i > 3));
        System.out.println(list.stream().anyMatch(i -> i > 3));
    }

    @Test
    public void test10() {
        //经过测试，当元素个数小于24时，并行时线程数等于元素个数，当大于等于24时，并行时线程数为16
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);
        System.out.println(list.stream().reduce((o1, o2) -> o1 + o2).get());
        System.out.println(list.stream().reduce(10, (o1, o2) -> o1 + o2));

        System.out.println(
                list
                        .stream()
                        .reduce(0,
                                (o1, o2) -> {
                                    System.out.printf("stream accumulator o1:%s, o2:%s\n", o1, o2);
                                    return o1 - o2;
                                },
                                (o1, o2) -> {
                                    System.out.printf("stream accumulator o1:%s, o2:%s\n", o1, o2);
                                    return o1 * o2;
                                }));

        System.out.println("==================================================");
        System.out.println(
                list
                        .parallelStream()
                        .reduce(0,
                                (o1, o2) -> {
                                    System.out.printf("stream accumulator o1:%s, o2:%s\n", o1, o2);
                                    return o1 - o2;
                                },
                                (o1, o2) -> {
                                    System.out.printf("stream accumulator o1:%s, o2:%s\n", o1, o2);
                                    return o1 * o2;
                                }));
    }

}
