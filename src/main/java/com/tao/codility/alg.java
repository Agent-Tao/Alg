package com.tao.test;

import java.util.HashSet;
import java.util.Set;

public class alg {

	public static void main(String[] args) {

		//int[] A = {60,80,40};
		//int[] B = {2,3,5};
		int[] A = {40,40,100,80,30};
		int[] B = {3,3,2,2,3};
		
		int r = solution(A,B,3,5,200);
		System.out.println(r);
	}
	


    public static int solution(int[] A, int[] B, int M, int X, int Y) {
    	
    	int len = A.length;
    	
    	Set<Integer> mask= new HashSet<Integer>();
    	
    	int stops=0;
    	int i = 0;
    	while(i<len) {
 	    	stops++;   		
    		mask.clear();
    		int weight=0;
	    	int j=0;
	    	while(j<X&&i<len) {
	    		weight+=A[i];
	    		if(weight>Y) {
	    			break;
	    		}
	    		if(!mask.contains(B[i])) {
	    			stops++;
	    		}	    		
	    		
	    		mask.add(B[i]);
	    		i++;
	    		j++;
	    	}

    	}  	
		return stops;
    }
	
	

}
