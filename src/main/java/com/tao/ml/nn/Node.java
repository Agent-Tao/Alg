package com.tao.ml.nn;

public interface Node {
	public double forward();
	public double backward(double y);
	public double getOutput();
}
