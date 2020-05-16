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
		

		
	}

}
