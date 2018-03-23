/**
 * 
 */
package com.hai.javase.thread.multithread.ticket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Administrator
 * 
 */
public class Ticket2 implements Runnable {
	private int ticketNum = 200;
	private int opCount = 0;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		while (ticketNum > 0) {
			// 同步代码块
			synchronized (this) {
				if (ticketNum > 0) {
					System.out.println(Thread.currentThread().getName() + "...sale: " + ticketNum--);
					opCount++;
				}
			}
		}
		System.out.println("opCount: " + opCount);

	}

	public static void main(String[] args) {

		Ticket2 ticket2 = new Ticket2();
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		executorService.submit(ticket2);
		executorService.submit(ticket2);
		executorService.submit(ticket2);
		executorService.shutdown();
	}
}
