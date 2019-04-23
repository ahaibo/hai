package com.hai.javase.collection.list;

import com.alibaba.fastjson.JSONObject;

import java.util.*;
import java.util.concurrent.*;

public class ConcurrentModificationExceptionTest {

    public static void main(String[] args) {
//        new ConcurrentModificationExceptionOnSingleThread(new ArrayList(), true).action();
//        new ConcurrentModificationExceptionOnSingleThread(new Vector(), false).action();
//        new ConcurrentModificationExceptionOnMultiThread(new ArrayList(), true).action();
        new ConcurrentModificationExceptionOnMultiThreadByConcurrentCollection(new CopyOnWriteArrayList(), true).execute();
    }

    /**
     * ConcurrentModificationException 测试基类
     */
    static abstract class ConcurrentModificationExceptionOnBaseClass {

        private boolean toSafeOperation;
        private Collection collection;

        public ConcurrentModificationExceptionOnBaseClass(Collection collection) {
            this(collection, false);
        }

        public ConcurrentModificationExceptionOnBaseClass(Collection collection, boolean toSafeOperation) {
            this.collection = collection;
            this.toSafeOperation = toSafeOperation;
            if (check(false)) {
                generateItems();
            }
            this.collectionToString("before");
        }

        abstract public void execute();

        protected boolean check() {
            return check(true);
        }

        protected boolean check(boolean checkEmpty) {
            if (null == this.collection || (checkEmpty && this.collection.isEmpty())) {
                return false;
            }
            return true;
        }

        protected void generateItems() {
            Random random = new Random();
            int range = random.nextInt(10) + 10;
            int removeIndex = random.nextInt(range);
            int itemRange = 1000;
            for (int i = 0; i < range; i++) {
                if (i == removeIndex) {
                    this.collection.add("remove");
                } else {
                    this.collection.add(random.nextInt(itemRange));
                }
            }
            System.out.println("toSafeOperation:" + toSafeOperation + ";\trange:" + range + ";\tremoveIndex:" + removeIndex + ";\titemRange:" + itemRange);
        }

        protected void collectionToString(String prefix) {
            System.out.println(prefix + " action collection:" + Arrays.toString(this.collection.toArray()));
        }

        protected void futuresGet(List<Future> futures) {
            for (Future future : futures) {
                try {
                    Object value = future.get();
                    System.out.println("future value:" + value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 单线程 ConcurrentModificationException 解决方案：iterator.remove();
     */
    static class ConcurrentModificationExceptionOnSingleThread extends ConcurrentModificationExceptionOnBaseClass {
        public ConcurrentModificationExceptionOnSingleThread(Collection collection) {
            this(collection, false);
        }

        public ConcurrentModificationExceptionOnSingleThread(Collection collection, boolean toSafeOperation) {
            super(collection, toSafeOperation);
        }

        @Override
        public void execute() {
            if (!this.check()) {
                return;
            }
            Iterator iterator = super.collection.iterator();
            while (iterator.hasNext()) {
                Object object = iterator.next();
                if ("remove".equals(object)) {
                    if (super.toSafeOperation) {
                        iterator.remove();
                    } else {
                        super.collection.remove(object);
                    }
                }
            }
            this.collectionToString("after");
        }
    }

    /**
     * ConcurrentModificationException 多线程解决方案一：在 Iterator 迭代时加同步 synchronized 或 lock 操作
     */
    static class ConcurrentModificationExceptionOnMultiThread extends ConcurrentModificationExceptionOnBaseClass {
        public ConcurrentModificationExceptionOnMultiThread(Collection collection, boolean toSafeOperation) {
            super(collection, toSafeOperation);
        }

        @Override
        public void execute() {
            int threadPoolSize = 5;
            int threadSize = threadPoolSize * 2;
            ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);
            List<Future> futures = new ArrayList<>(threadSize);
            for (int i = 0; i < threadSize; i++) {
                int finalI = i;
                futures.add(executorService.submit(() -> {
                    synchronized (super.collection) {
                        Object result = null;
                        Iterator iterator = super.collection.iterator();
                        while (iterator.hasNext()) {
                            Object object = iterator.next();
                            if (finalI % 2 == 0) {
                                if (super.toSafeOperation) {
                                    iterator.remove();
                                } else {
                                    super.collection.remove(object);
                                }
                                result = object;
                            } else {
                                System.out.println(this.getClass().getName() + ".action: i=" + finalI + ";object=" + object);
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        return result;
                    }
                }));
            }
            this.futuresGet(futures);
            executorService.shutdown();
        }
    }

    /**
     * ConcurrentModificationException 多线程解决方案二：使用同步集合如 CopyOnWriteArrayList
     */
    static class ConcurrentModificationExceptionOnMultiThreadByConcurrentCollection extends ConcurrentModificationExceptionOnBaseClass {
        public ConcurrentModificationExceptionOnMultiThreadByConcurrentCollection(Collection collection, boolean toSafeOperation) {
            super(collection, toSafeOperation);
        }

        @Override
        public void execute() {
            int threadPoolSize = 5;
            int threadSize = threadPoolSize * 1;
            ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);
            List<Future> futures = new ArrayList<>(threadSize);
            for (int i = 0; i < threadSize; i++) {
                int finalI = i;
                futures.add(executorService.submit(() -> {
                    Object result = null;
                    Iterator iterator = super.collection.iterator();
                    while (iterator.hasNext()) {
                        Object object = iterator.next();
                        if (finalI % 2 == 0) {
//                            CopyOnWriteArrayList copyOnWriteArrayList= (CopyOnWriteArrayList) super.collection;
//                            copyOnWriteArrayList.remove(object);
                            if (super.toSafeOperation) {
                                //TODO 安全集合场景下的remove操作需调用集合的remove方法，Iterator的remove方法因各线程实例不同而不同，故不安全
                                super.collection.remove(object);
                            } else {
                                iterator.remove();
                            }
                            result = object;
                        } else {
                            System.out.println(this.getClass().getName() + ".action: i=" + finalI + ";object=" + object);
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    return result;
                }));
            }
            this.futuresGet(futures);
            executorService.shutdown();
        }
    }
}
