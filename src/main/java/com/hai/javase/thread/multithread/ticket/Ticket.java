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
public class Ticket implements Runnable {
	private static int ticketNum = 200;
	private static int opCount = 0;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		while (ticketNum > 0) {
			// 同步代码块
			synchronized (Ticket.class) {
				if (ticketNum > 0) {
					System.out.println(Thread.currentThread().getName() + "...sale: " + ticketNum--);
					opCount++;
				}
			}
		}
		System.out.println("opCount: " + opCount);

	}

	public static void main(String[] args) {

		ExecutorService service = Executors.newFixedThreadPool(5);

		service.execute(new Ticket());
		service.execute(new Ticket());
		service.execute(new Ticket());
		service.execute(new Ticket());
		service.execute(new Ticket());

		System.out.println("service main...");
		service.shutdown();
//		System.out.println("service.isShutdown(): " + service.isShutdown());
//		while (!service.isTerminated()) {
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		System.out.println("service terminated: " + service.isTerminated());

	}
}
