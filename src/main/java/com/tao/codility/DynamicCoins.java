package com.tao.test;

public class DynamicCoins {

    public static int solution(int[] A,int sum,int mask) {
    	
    	if(mask==0) {
    		return sum/A[mask];
    	}
    	
    	if(sum<0) {
    		return 0;
    	}
    	
		int max=A[mask];
		if(max>sum) {
			int r = solution(A,sum,--mask);
			return r;
		} else {		
			int r1 = 1+solution(A,sum-max,mask);
			int r2 = solution(A,sum,--mask);
			return Math.min(r1, r2);
		}

    }
	
	
	public static void main(String[] args) {
		
		int[] A = {1,3,4};
		
		int n = solution(A,10,A.length-1);
		
		System.out.println(n);
	}

}
