package com.tao.ml;

import java.util.Arrays;

public class LogisticRegress {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[][] data = {
        		{0.1,1.1,2},
        		{0.2000,0.3000,0.2000},
        		{0.4000,0.0000,-0.1000},
        		{-0.5000,-0.6000,0.3000}
        };
		float[] labels = {1,0,1,0};
		LogisticRegress lr = new LogisticRegress();
	    lr.train(data,labels, 0.01f, 3000, (short)1);
	}
	
	
	public void train(double[][] datas,float[] labels, float step, int maxIt, short algorithm) {
		//数量
		int size = datas.length;
		//维度
		int dim = datas[0].length;
		//所求系数
		double[] w = new double[dim];

		for(int i = 0; i < dim; i++) {
			w[i] = 1;
		}

		switch(algorithm){
		//批量梯度下降
		case 1: 
			for(int i = 0; i < maxIt; i++) {
				//求输出
				double[] out = new double[size];
				for(int s = 0; s < size; s++) {
					double lire = innerProduct(w, datas[s]);
					out[s] = sigmoid(lire);
				}
				//梯度下降
				for(int d = 0; d < dim; d++) {
					double sum = 0;
					for(int s = 0; s < size; s++) {
						sum  += (labels[s] - out[s]) * datas[s][d];
					}
					w[d] = w[d] + step * sum;
				}
				System.out.println(Arrays.toString(w));
			}
			break;
			//随机梯度下降
		case 2: 
			for(int i = 0; i < maxIt; i++) {
				for(int s = 0; s < size; s++) {
					double lire = innerProduct(w, datas[s]);
					double out = sigmoid(lire);
					double error = labels[s] - out;
					for(int d = 0; d < dim; d++) {
						w[d] += step * error * datas[s][d];
					}
				}
				System.out.println(Arrays.toString(w));
			}
			break;
		}
	}
	
	private double innerProduct(double[] w, double[] x) {
		float sum = 0;
		for(int i = 0; i < w.length; i++) {
			sum += w[i] * x[i];
		}

		return sum;
	}

	private double sigmoid(double src) {
		return (float) (1.0 / (1 + Math.exp(-src)));
	}

}
