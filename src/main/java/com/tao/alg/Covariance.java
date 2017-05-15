package com.tao.alg;

public class Covariance {

	public static double mean(double[] data) {
		double sum=0;
		for(double d:data) {
			sum += d;
		}	
		return sum/data.length;	
	}
	
	public static double var(double[] data) {
		double m=mean(data);
		double sum=0;
		for(double d:data) {
			sum += (d-m)*(d-m);
		}
		return sum/data.length;
	}
	
	public static double cov(double[] data1,double[] data2) {
		double m1=mean(data1);
		double m2=mean(data2);
		double sum=0;
		int len=data1.length;
		
		for(int i = 0; i<len;i++) {
			sum += (data1[i]-m1)*(data2[i]-m2);
		}

		return sum/len;
	}
	
	public static double pearson(double[] data1,double[] data2) {
		double m1=mean(data1);
		double m2=mean(data2);
		double sum=0;
		int len=data1.length;
		for(int i = 0; i<len;i++) {
			sum += (data1[i]-m1)*(data2[i]-m2);
		}
		
		double sum2 = 0;
		for(int i = 0; i<len;i++) {
			sum2 += (data1[i]-m1)*(data1[i]-m1);
		}
		
		double sum3 = 0;
		for(int i = 0; i<len;i++) {
			sum3 += (data2[i]-m2)*(data2[i]-m2);
		}
		
		double sum4=Math.sqrt(sum2*sum3);
		return sum/sum4;
		
	}
	public static void main(String[] args) {
		
		double[] data1={3,4,2,6,8,2,5};
		double r1 = var(data1);
		System.out.println(r1);
		double[] data2={5,5.5,4,7,10,5,7.5};
		double r2 = var(data2);
		System.out.println(r2);
		
		double r3 = cov(data1,data2);
		System.out.println(r3);
		
		double[] data3={1,2,3};
		double[] data4={4,5,6};
		double ret = pearson(data3,data4);
		System.out.println(ret);
	}

}
