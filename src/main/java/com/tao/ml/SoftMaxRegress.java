package com.tao.ml;

import java.util.Arrays;

public class SoftMaxRegress {

	//数量
	int _size = 0;
	//维度
	int _dim = 0;
	//数据
	double[][] _data;
	
	int[] _Y = {0};
	
	int _kind = 0;
	double[][] _theta = {
			{ 0.3, 0.3, 0.01, 0.01 },
			{ 0.5, 0.5, 0.01, 0.01 } };
			
	public static void main(String[] args) {
		double[][] data = {
				{ 1, 47, 76, 24 }, //include x0=1
				{ 1, 46, 77, 23 },
				{ 1, 48, 74, 22 },
				{ 1, 34, 76, 21 },
				{ 1, 35, 75, 24 },
				{ 1, 34, 77, 25 },
				{ 1, 55, 76, 21 },
				{ 1, 56, 74, 22 },
				{ 1, 55, 72, 22 },
        };
		int[] labels = {1,1,1,2,2,2,3,3,3};
		SoftMaxRegress softMaxRegress = new SoftMaxRegress(data,labels);
		softMaxRegress.train(0.001, 100000);
		
		for(double[] d:data) {
			softMaxRegress.predict(d);
		}
		double pre[] = { 1, 20, 80, 50 };
		softMaxRegress.predict(pre);
	}
	
	public SoftMaxRegress(double[][] data,int[] y) {
		_size = data.length;
		_dim = data[0].length;	
		_data = data;
		_Y = y;
		//Arrays.asList(y).stream().forEach(x->System.out.println("1"));
		
		//int i = Arrays.asList(y).size();
		//System.out.println("i:"+i);
		_kind = 2;
	}
	
	public void train(double step, int maxIt) {
		//所求系数
		for(int i = 0; i < maxIt; i++) {
	
			gradientDescent(step);
		}
	}
	
	void predict(double[] pre)
	{
		//输出预测向量
		int i;
		double[] h = hypothesisFunc(_theta,pre);
		for (i = 0; i < _kind; i++) {
			System.out.print(h[i]+"  ");
		}
		System.out.println(1 - h[0] - h[1]);
	}
	
	public void gradientDescent(double step) {
		//批量梯度下降，训练参数
		int i, j, k;
		for (i = 0; i < _kind; i++)
		{
			double sum[] = new double[_dim];
			for (j = 0; j < _size; j++)
			{
				double[] h = hypothesisFunc(_theta,_data[j]);
				for (k = 0; k < _dim; k++)
				{
					sum[k] += _data[j][k] * ((_Y[j] == i + 1 ? 1 : 0) - h[i]);
				}
			}
			for (k = 0; k < _dim; k++)
			{
				_theta[i][k] += step * sum[k] / _dim;
			}
		}
		//System.out.print(Arrays.toString(_theta[0]));
		//System.out.println(Arrays.toString(_theta[1]));
	}
	
	public double[] hypothesisFunc(double[][] theta,double[] x) {
		double total=1;
		double[] hvalue = new double[_kind];
		for(int i=0;i<_kind;i++) {
			hvalue[i]=Math.exp(innerProduct(theta[i],x));
			total+=hvalue[i];	
		}
		for(int i=0;i<_kind;i++)
		{
			hvalue[i]=hvalue[i]/total;
		}
		return hvalue;
	}
	
	private double innerProduct(double[] w, double[] x) {
		float sum = 0;
		for(int i = 0; i < w.length; i++) {
			sum += w[i] * x[i];
		}
		return sum;
	}
}
