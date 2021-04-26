package com.hai.javase.concurrent.complete;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * description
 *
 * @author hai
 * @date 2021/3/26 16:42
 */
public class CompletableFutureTest {

    @Test
    public void test1() throws ExecutionException, InterruptedException {
        CompletableFuture completableFuture1 = CompletableFuture.supplyAsync(() -> "hello");
        CompletableFuture completableFuture2 = CompletableFuture.supplyAsync(() -> 100);
        CompletableFuture completableFuture3 = CompletableFuture.supplyAsync(() -> true);

        // CompletableFuture.allOf(completableFuture1, completableFuture2, completableFuture3)
        //         .thenAccept((Object value) -> {
        //             System.out.println("value: " + value);
        //         })
        //         .thenApply(value -> "then-apply: " + value)
        //         .get();

        completableFuture1
                // .thenCompose(s -> {
                //     s = s + "then-compose";
                //     System.out.println(s);
                //     return s;
                // })
                .thenApply(s -> {
                    s = s + ":then-apply";
                    System.out.println(s);
                    return s;
                })
                .thenAccept((Object value) -> {
                    System.out.println(value + ":then-accept");
                })
                .thenCombine(completableFuture2, (v1, v2) -> {
                    v2 = "completableFuture1 thenCombine completableFuture2. value: " + v2;
                    System.out.println("completableFuture1 thenCombine completableFuture2.v1:" + v1);
                    System.out.println(v2);
                    return v2;
                })
                .thenCombine(completableFuture3, (v1, v2) -> {
                    v2 = "completableFuture1 thenCombine completableFuture3. value: " + v2;
                    System.out.println("completableFuture1 thenCombine completableFuture3.v1:" + v1);
                    System.out.println(v2);
                    return v2;
                })
                .whenComplete((Object obj1, Object obj2) -> {
                    System.out.println("completableFuture1-completableFuture3 complete. obj1: " + obj1 + ", obj2: " + obj2);
                })
                .join();

        completableFuture3
                .whenComplete((Object obj1, Object obj2) -> {
                    System.out.println("completableFuture3 complete. obj1: " + obj1 + ", obj2: " + obj2);
                })
                .get();
    }

    @Test
    public void testThenApply() throws ExecutionException, InterruptedException {
        // map
        // thenApply(Function<? super T,? extends U> fn)
        // 接受一个Function<? super T,? extends U>参数用来转换CompletableFuture

        // thenApplyAsync(Function<? super T,? extends U> fn)
        // 接受一个Function<? super T,? extends U>参数用来转换CompletableFuture，使用ForkJoinPool

        // thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)
        // 接受一个Function<? super T,? extends U>参数用来转换CompletableFuture，使用指定的线程池

        // thenApply的功能相当于将CompletableFuture<T>转换成CompletableFuture<U>。
        // 链接：https://www.jianshu.com/p/d81cf0beb858
        CompletableFuture<String> future = CompletableFuture
                .supplyAsync(() -> "testThenApply")
                // future
                // .thenAcceptAsync((String value) -> System.out.println("args:" + value))
                .thenApply(value -> value + "-apply")
                .thenApply(String::toUpperCase)
                .whenComplete((s, e) -> System.out.println("whenComplete->value:" + s + ", exception:" + e));
        System.out.println("result1:" + future.get());

        CompletableFuture<Double> future2 = CompletableFuture
                .supplyAsync(() -> "10")
                .thenApply(Integer::parseInt)
                .thenApply(i -> i * 10.0);
        System.out.println("result2:" + future2.get());
    }


    @Test
    public void testThenCompose() throws ExecutionException, InterruptedException {
        // flatMap
        // 方法名	描述
        // thenCompose(Function<? super T, ? extends CompletionStage<U>> fn)
        // 在异步操作完成的时候对异步操作的结果进行一些操作，并且仍然返回CompletableFuture类型。

        // thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn)
        // 在异步操作完成的时候对异步操作的结果进行一些操作，并且仍然返回CompletableFuture类型。使用ForkJoinPool。

        // thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn,Executor executor)
        // 在异步操作完成的时候对异步操作的结果进行一些操作，并且仍然返回CompletableFuture类型。使用指定的线程池。

        // thenCompose可以用于组合多个CompletableFuture，将前一个结果作为下一个计算的参数，它们之间存在着先后顺序。
        // 链接：https://www.jianshu.com/p/d81cf0beb858
        CompletableFuture<Double> future = CompletableFuture
                .supplyAsync(() -> "100")
                .thenCompose(value -> CompletableFuture.supplyAsync(() -> value + "200"))
                .thenCompose(value -> CompletableFuture.supplyAsync(() -> Double.parseDouble(value)))
                .whenComplete((s, e) -> System.out.println("whenComplete->value:" + s + ", exception:" + e));
        System.out.println("result1:" + future.get());
    }

    @Test
    public void testThenCombine() throws ExecutionException, InterruptedException {
        // 组合
        // 方法名	描述
        // thenCombine(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn)
        // 当两个CompletableFuture都正常完成后，执行提供的fn，用它来组合另外一个CompletableFuture的结果。

        // thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn)
        // 当两个CompletableFuture都正常完成后，执行提供的fn，用它来组合另外一个CompletableFuture的结果。使用ForkJoinPool。

        // thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn, Executor executor)
        // 当两个CompletableFuture都正常完成后，执行提供的fn，用它来组合另外一个CompletableFuture的结果。使用指定的线程池。

        // 现在有CompletableFuture<T>、CompletableFuture<U>和一个函数(T,U)->V，
        // thenCompose就是将CompletableFuture<T>和CompletableFuture<U>变为CompletableFuture<V>。

        // 链接：https://www.jianshu.com/p/d81cf0beb858

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "100");
        CompletableFuture<Double> future2 = CompletableFuture.supplyAsync(() -> 300D);
        CompletableFuture<Double> future3 = future1.thenCombine(future2, (s, d) -> s + d)
                .thenApply(Double::parseDouble)
                .whenComplete((s, e) -> System.out.println("whenComplete->value:" + s + ", exception:" + e));
        System.out.println("result1:" + future3.get());
    }

    @Test
    public void testBoth() throws ExecutionException, InterruptedException {
        // thenAcceptBoth跟thenCombine类似，但是返回CompletableFuture<Void>类型。

        // 方法名	描述
        // thenAcceptBoth(CompletionStage<? extends U> other, BiConsumer<? super T,? super U> action)
        // 当两个CompletableFuture都正常完成后，执行提供的action，用它来组合另外一个CompletableFuture的结果。

        // thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T,? super U> action)
        // 当两个CompletableFuture都正常完成后，执行提供的action，用它来组合另外一个CompletableFuture的结果。使用ForkJoinPool。

        // thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T,? super U> action, Executor executor)
        // 当两个CompletableFuture都正常完成后，执行提供的action，用它来组合另外一个CompletableFuture的结果。使用指定的线程池。

        // 链接：https://www.jianshu.com/p/d81cf0beb858
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "100");
        CompletableFuture<Double> future2 = CompletableFuture.supplyAsync(() -> 300D);
        CompletableFuture<Double> future3 = future1.thenCombine(future2, (s, d) -> s + d)
                .thenApply(Double::parseDouble)
                .whenComplete((s, e) -> System.out.println("whenComplete->value:" + s + ", exception:" + e));

        future3.thenAcceptBothAsync(future1, (d, s) -> System.out.println("d:" + d + ",s:" + s));
        System.out.println("result1:" + future3.get());
    }

    @Test
    public void testEither() throws ExecutionException, InterruptedException {
        // 两个任务只要有一个完成 我们就执行任务三
        CompletableFuture<Object> future01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务一启动线程：" + Thread.currentThread().getId());
            int i = 10 / 4;
            System.out.println("任务一结束：" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return i;
        });

        CompletableFuture<Object> future02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务二启动线程：" + Thread.currentThread().getId());
            System.out.println("任务二结束：");
            return "hello";
        });

        //runAfterEitherAsync :不感知结果 自己也无返回值
        future01.runAfterEitherAsync(future02, () -> System.out.println("runAfterEitherAsync..."));

        //acceptEitherAsync :感知结果 无返回值
        future01.acceptEitherAsync(future02, (s) -> System.out.println("acceptEitherAsync. result:" + s));

        //applyToEitherAsync :感知结果 有返回值
        CompletableFuture<String> future03 = future01.applyToEitherAsync(future02, (res) -> {
            System.out.println("applyToEitherAsync. res:" + res);
            return res.toString();
        });

        System.out.println("result:" + future03.get());
        // 原文链接：https://blog.csdn.net/weixin_43691942/article/details/113500091
    }

    @Test
    public void testHandle() throws ExecutionException, InterruptedException {
        // 执行完Action可以做转换
        // 方法名	描述
        // handle(BiFunction<? super T, Throwable, ? extends U> fn)	 当CompletableFuture完成计算结果或者抛出异常的时候，执行提供的fn

        // handleAsync(BiFunction<? super T, Throwable, ? extends U> fn)
        // 当CompletableFuture完成计算结果或者抛出异常的时候，执行提供的fn，使用ForkJoinPool。

        // handleAsync(BiFunction<? super T, Throwable, ? extends U> fn, Executor executor)
        // 当CompletableFuture完成计算结果或者抛出异常的时候，执行提供的fn，使用指定的线程池。
        //
        // 链接：https://www.jianshu.com/p/d81cf0beb858

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "100a");
        CompletableFuture<Double> future2 = CompletableFuture.supplyAsync(() -> 300D);
        CompletableFuture<Double> future3 = future1.thenCombine(future2, (s, d) -> s + d)
                .thenApply(Double::parseDouble)
                .whenComplete((s, e) -> System.out.println("whenComplete->value:" + s + ", exception:" + e))
                // .exceptionally(e->System.out.println(e.getMessage()))
                .handle((d, t) -> {
                    System.out.println("handle[result:" + d + ", exception:" + t + "].");
                    if (null != t) {
                        t.printStackTrace();
                        return 0D;
                    }
                    return d * 2;
                });
        System.out.println("result1:" + future3.get());
    }

    @Test
    public void testThenAccept() throws ExecutionException, InterruptedException {
        // 纯消费(执行Action)
        // 方法名	描述
        // thenAccept(Consumer<? super T> action)	当CompletableFuture完成计算结果，只对结果执行Action，而不返回新的计算值
        // thenAcceptAsync(Consumer<? super T> action)	当CompletableFuture完成计算结果，只对结果执行Action，而不返回新的计算值，使用ForkJoinPool。
        // thenAcceptAsync(Consumer<? super T> action, Executor executor)	当CompletableFuture完成计算结果，只对结果执行Action，而不返回新的计算值
        // thenAccept()是只会对计算结果进行消费而不会返回任何结果的方法。

        // 链接：https://www.jianshu.com/p/d81cf0beb858
        CompletableFuture.supplyAsync(() -> "hello")
                .thenApply(s -> s + " world!")
                .thenApply(String::toUpperCase)
                .whenComplete((s, e) -> System.out.println("whenComplete->value:" + s + ", exception:" + e))
                .thenAccept(s -> System.out.println("accept:" + s));
    }

    //https://blog.csdn.net/weixin_34226706/article/details/91463103
    public static <T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> list) {
        CompletableFuture<Void> future = CompletableFuture.allOf(list.toArray(new CompletableFuture[list.size()]));
        return future.thenApply(v -> list.stream().map(CompletableFuture::join).collect(Collectors.<T>toList()));
    }

    public static <T> CompletableFuture<List<T>> sequence(Stream<CompletableFuture<T>> futures) {
        List<CompletableFuture<T>> futureList = futures.filter(f -> f != null).collect(Collectors.toList());
        return sequence(futureList);
    }

    //Java Future转CompletableFuture
    public static <T> CompletableFuture<T> toCompletable(Future<T> future, Executor executor) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return future.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }, executor);
    }

}
