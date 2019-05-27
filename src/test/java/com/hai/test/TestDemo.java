package com.hai.test;

import com.google.common.util.concurrent.*;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.Future;
import org.junit.Test;

import java.security.MessageDigest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/5/2.
 */
public class TestDemo {

    @Test
    public void testRuntime() {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("availableProcessors: " + availableProcessors);
    }

    @Test
    public void testString() {
//        String s1 = "ab";
//        String s2 = "a" + "b";
//        System.out.println(s1 == s2);

        StringBuilder sb = new StringBuilder(1024);
        for (int i = 0; i < 1024; i++) {
            sb.append(i);
        }
        System.out.println(sb.toString());
    }

    @Test
    public void test() {
//        try {
//            byte[] passwd = encrypte("5e52767f0606e64e63babd1e448c848f".getBytes(), "eb1739930ffe65e2e33a70f5713fdcdc");
////            System.out.println(new String(passwd).toString());
//            System.out.println(passwd);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        System.out.println(UUID.randomUUID().toString().replace("-", ""));
    }

    public static byte[] encrypte(byte[] salt, String passwrod) throws Exception {
        try {
            //声明消息摘要对象
            MessageDigest md = null;
            //创建消息摘要
            md = MessageDigest.getInstance("MD5");
            //将盐数据传入消息摘要对象
            md.update(salt);
            //将口令的数据传给消息摘要对象
            md.update(passwrod.getBytes("UTF-8"));
            //获得消息摘要的字节数组
            return md.digest();
        } catch (Exception e) {
            throw new Exception("Md5解密失败", e);
        }
    }

    @Test
    public void testListenableFuture4Netty() {
        EventExecutorGroup group = new DefaultEventExecutorGroup(1);
        System.out.println("testListenableFuture4Netty Do something ...");

        Future<Integer> future = group.submit(() -> {
            System.out.println("testListenableFuture4Netty Sleep......");
            Thread.sleep(1000);
            return 99;
        });

        future.addListener(future1 -> System.out.println("testListenableFuture4Netty result:" + future1.get()));
        System.out.println("testListenableFuture4Netty Main Done ...");
    }

    @Test
    public void testListenableFuture4Guava() {
        ListeningExecutorService executor = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        ListenableFuture<Integer> future = executor.submit(() -> {
            try {
                System.out.println("testListenableFuture4Guava 111111111 ...");
//                int i = 1 / 0;
                Thread.sleep(2000);
                System.out.println("testListenableFuture4Guava 222222222 ...");
            } catch (InterruptedException e) {
                e.printStackTrace();
                return -100;
            }
            return 100;
        });
        System.out.println("testListenableFuture4Guava Do something ...");

        Futures.addCallback(future, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                System.out.println("testListenableFuture4Guava Future return value " + result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("testListenableFuture4Guava Future occur error:" + t.getMessage());
            }
        });

        System.out.println("testListenableFuture4Guava ... Main thread Done ...");

        executor.shutdown();
    }

    public static void main(String[] args) {
        TestDemo demo = new TestDemo();
        demo.testListenableFuture4Netty();
        demo.testListenableFuture4Guava();
    }

    @Test
    public void change() {
        int n = -55, m = 132;
        //int temp = n;
        //n = m;
        //m = temp;
        System.out.println("n=" + n + ",m=" + m);

        //n = n + m;
        //m = n - m;
        //n = n - m;
        System.out.println("n=" + n + ",m=" + m);

        n = n ^ m;
        m = n ^ m;
        n = n ^ m;
        System.out.println("n=" + n + ",m=" + m);

        char c = '海';
        System.out.println(c);
    }

    /*public <E> void read(List<? super E> list) {
        for (E e : list) {

        }
    }*/
    public <E> void read(List<? extends E> list) {
        Set set = new HashSet();

        for (E e : list) {

        }
    }

    /*public void write(List<? extends Number> list) {
        list.add(123);
    }*/

    public void write(List<? super Number> list) {
        list.add(123);
    }
}
