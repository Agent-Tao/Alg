package com.tao.test;

import java.util.HashSet;
import java.util.Set;

public class alg2 {

	public static void main(String[] args) {

		int[] A = {4,5,1,1,1,1,4,3,1};
		int r = solution(A);
		System.out.println(r);
	}
	


    public static int solution(int[] A) {	
    	
    	int len = A.length;
        int sum = 0;
        int lsum = 0;
        int rsum = 0;        
        int msum = 0; 
        
        int[] sums = new int[len];
        for (int i=0; i<len; i++) {
            sum += A[i];
            sums[i] = sum;
        }
        
        int l = 1, r = len - 2;
        while (l+1 < r) {
            lsum = sums[l-1];
            msum = sums[r-1] - sums[l];
            rsum = sums[len-1] - sums[r];
            if ((lsum == msum) && (msum == rsum)) {
                return 1;
            } else if (lsum > rsum) {
                r--;
            } else if (lsum < rsum){
                l++;
            } else {
                l++;
                r--;
            }
        }
        return 0;
     
    }
	
	

}
