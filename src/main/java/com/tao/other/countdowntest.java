package com.tao.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class countdowntest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService service= Executors.newFixedThreadPool(5);
		final CountDownLatch latch = new CountDownLatch(3);
		
		service.execute(()->{
			System.out.println("haha");
			latch.countDown();
		});
		
		service.execute(()->{
			System.out.println("hoho");
			latch.countDown();
		});

		Future task = service.submit(()->{
			System.out.println("hoho");
			latch.countDown();
			return "hohoret";
		});
		
		//Thread.sleep(1000);
		latch.await();
		System.out.println("done");
		
		if(task.isDone()) {
			System.out.println(task.get().toString());
		}
	}

}
