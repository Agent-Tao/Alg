package com.tao.test;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class multiWorkerTest {

	
	
	public static void main(String[] args) throws InterruptedException {
		
		ExecutorService service= Executors.newFixedThreadPool(5);
		final CountDownLatch latch = new CountDownLatch(5);	
		for(int i=0;i<5;i++) {
			final int t = i; 
			Runnable func = ()->{
				System.out.println("hoho"+t);
				latch.countDown();
			};
			service.execute(func);
		}
		
		latch.await();
		System.out.println("done");
	}

}
