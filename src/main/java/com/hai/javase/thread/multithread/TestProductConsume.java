/**
 * 
 */
package com.hai.javase.thread.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Product {
	int num;

	public synchronized void add() {
		if (num >= 100) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			num++;
			notifyAll();// 唤醒消费者
			System.out.println(Thread.currentThread().getName() + " add 1 product.");
		}
	}

	public synchronized void consume() {
		if (num <= 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		{
			num--;
			notifyAll();// 唤醒生产者
			System.out.println(Thread.currentThread().getName() + " consume 1 product.");
		}
	}
}

class Producer implements Runnable {
	Product product;
	long sleep;

	public Producer(Product product, long sleep) {
		this.product = product;
		this.sleep = sleep;
	}

	@Override
	public void run() {
		if (null != product) {
			System.out.println("producer main now");
			while (true) {
				try {
					Thread.sleep(this.sleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				product.add();
			}
		}
	}

}

class Consume implements Runnable {
	Product product;
	long sleep;

	public Consume(Product product, long sleep) {
		this.product = product;
		this.sleep = sleep;
	}

	@Override
	public void run() {
		if (null != product) {
			System.out.println("consume main now");
			while (true) {
				try {
					Thread.sleep(this.sleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				product.consume();
			}
		}
	}

}

/**
 * @author as
 *
 */
public class TestProductConsume {

	public static void main(String[] args) {
		long sleep = 500;
		Product product = new Product();

		Producer producer1 = new Producer(product, sleep);
		Producer producer2 = new Producer(product, sleep);
		Producer producer3 = new Producer(product, sleep);

		Consume consume1 = new Consume(product, sleep);
		Consume consume2 = new Consume(product, sleep);
		Consume consume3 = new Consume(product, sleep);

		ExecutorService executorService = Executors.newCachedThreadPool();

		executorService.execute(producer1);
		executorService.execute(producer2);
		executorService.execute(producer3);

		executorService.execute(consume1);
		executorService.execute(consume2);
		executorService.execute(consume3);

		executorService.shutdown();

	}

}
