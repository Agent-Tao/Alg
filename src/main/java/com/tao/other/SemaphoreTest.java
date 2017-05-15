package com.tao.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService service= Executors.newFixedThreadPool(5);
		Semaphore semaphore = new Semaphore(0);
		AtomicInteger count = new AtomicInteger(0);
		AtomicBoolean bb = new AtomicBoolean(false);
		boolean ret  = bb.compareAndSet(false, true);
			
		System.out.println(ret);
		
		service.execute(()->{
			System.out.println("haha");
			try {
				Thread.sleep(1000);
				if(count.addAndGet(1)==3) {
					semaphore.release();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		service.execute(()->{
			System.out.println("hoho");
			try {
				Thread.sleep(2000);
				if(count.addAndGet(1)==3) {
					semaphore.release();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		service.execute(()->{
			System.out.println("gaga");
			try {
				Thread.sleep(3000);
				if(count.addAndGet(1)==3) {
					semaphore.release();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		semaphore.acquire();
		System.out.println("done");
	}

}
