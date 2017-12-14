/**
 *
 */
package com.hai.ijavase.thread.sync.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Administrator
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, new MainTask());

        executorService.execute(new SubTask("任务A", cyclicBarrier));
        executorService.execute(new SubTask("任务B", cyclicBarrier));
        executorService.execute(new SubTask("任务C", cyclicBarrier));
        executorService.execute(new SubTask("任务D", cyclicBarrier));
        executorService.execute(new SubTask("任务E", cyclicBarrier));
        executorService.execute(new SubTask("任务F", cyclicBarrier));
        executorService.execute(new SubTask("任务G", cyclicBarrier));
        executorService.execute(new SubTask("任务H", cyclicBarrier));
        executorService.execute(new SubTask("任务I", cyclicBarrier));
        executorService.execute(new SubTask("任务J", cyclicBarrier));

        executorService.shutdown();
    }
}

class MainTask implements Runnable {

    @Override
    public void run() {
        System.out.println("主任务完成！");
    }

}

class SubTask implements Runnable {

    private CyclicBarrier cyclicBarrier;
    private String taskName;

    public SubTask() {
    }

    /**
     * @param cyclicBarrier
     * @param taskName
     */
    public SubTask(String taskName, CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println("[子任务" + taskName + "]开始执行了！");

        int result = 1;
        int length = random(1000, 5000);
        // 模拟耗时的任务
        for (int i = 0; i < length; i++) {
            result++;
        }

        System.out.println("[子任务" + taskName + "]开始执行完成了，并通知障碍器已经完成！Current Result: " + result + "[by length: "
                + length + "]");

        try {
            // 通知障碍器已经完成
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param min
     * @param max
     * @return
     */
    private int random(int min, int max) {
        int result;
        Random random = new Random();
        do {
            result = random.nextInt(max);
        }
        while (result < min);

        return result;
    }

    /**
     * @return the cyclicBarrier
     */
    public CyclicBarrier getCyclicBarrier() {
        return cyclicBarrier;
    }

    /**
     * @param cyclicBarrier the cyclicBarrier to set
     */
    public void setCyclicBarrier(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    /**
     * @return the taskName
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * @param taskName the taskName to set
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

}
