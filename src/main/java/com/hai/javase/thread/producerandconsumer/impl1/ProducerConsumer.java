package com.hai.javase.thread.producerandconsumer.impl1;

/**
 * 生产者-消费者模式Demo
 * 
 * @author Administrator
 * 
 */
class ProducerConsumer
{
	public static void main(String[] args)
	{
		StackBasket s = new StackBasket();
		
		Producer producer = new Producer(s);
		Consumer consumer = new Consumer(s);
		
		Thread producerThread = new Thread(producer);
		Thread consumerThread = new Thread(consumer);
		
		producerThread.start();
		consumerThread.start();
	}
}