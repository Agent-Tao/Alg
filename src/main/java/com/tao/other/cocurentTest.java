package com.tao.test;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;


public class cocurentTest {

	public static void main(String[] args) {
		ConcurrentLinkedDeque<String> d = new ConcurrentLinkedDeque<String>();
		ConcurrentLinkedQueue<String> q = new ConcurrentLinkedQueue<String>();	
		
		d.add("123");
		d.add("234");
		d.add("345");
		d.add("678");
		
		for(String str:d) {
			System.out.println(str);
		}
		
		d.remove("345");
		
		for(String str:d) {
			System.out.println(str);
		}
		
		task task1 = new task();
		task1.setTaskID(1223l);
		task1.setName("ryr");
		task1.setOther("et");
		
		task task2 = new task();
		task2.setTaskID(123l);
		task2.setName("hoho");
		task2.setOther("hoho");
		
		System.out.println(task1.equals(task2));
		
	}

}
