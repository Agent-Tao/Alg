package com.tao.other;

import java.util.Arrays;
import java.util.List;

public class ArraySort {

	public static void main(String[] args) {
		
		int[] a = new int[10];
		System.out.println(a.length);
		int aa[] = new int[4];
		System.out.println(aa.length);
		int[] numbers = {1, 6, 3, 1, 5,5,7,84};
		System.out.println(numbers.length);
		int[][] matrix = {
				{10, 20, 30, 40, 50},
				{11, 21, 31, 41, 51}
				};
		System.out.println(matrix.length);
		System.out.println(matrix[0].length);
		System.out.println(matrix[1].length);
		
		System.out.println(matrix[0][0]);
		System.out.println(matrix[1][4]);
		
		for(int i:numbers) {
			System.out.print(i+" ");
		}
		//testSelectSort(numbers);
		quickSort(numbers,0,numbers.length-1);
		System.out.println();
		for(int i:numbers) {
			System.out.print(i+" ");
		}
	}
	
	static void swap(int[] in,int p1,int p2) {
		int t = in[p1];
		in[p1] = in[p2];
		in[p2] = t;
	}
	
	static void testbubboSort(int[] in) {
		
		for(int i=0;i<in.length;i++) {
			for(int j=in.length-1;j>i;j--) {
				if(in[j]>in[j-1]) {
					swap(in,j,j-1);
				}
			}
		}
	}
	
	static void testSelectSort(int[] in) {
		
		for(int i=0;i<in.length;i++) {
			for(int j=i+1;j<in.length;j++) {
				if(in[i]<in[j]) {
					swap(in,i,j);
				}
			}
		}
	}
	
	
	static void quickSort(int[] in,int start,int end) {
		
		if(start>=end) {
			return;
		}
		
		int m = in[start];
		int i=start;
		int j=end;
		boolean flag=true;
		while(i!=j) {
			if(flag) {
				if(in[j]<m) {
					in[i] = in[j];
					i++;
					flag=!flag;
				} else {
					j--;
				}
			} else {
				if(in[i]>m) {
					in[j] = in[i];
					j--;
					flag=!flag;
				} else {
					i++;
				}
			}	
		}
		in[i] = m;
		quickSort(in,start,i-1);
		quickSort(in,i+1,end);
	}

}
