package com.tao.ml.common;

public interface ActivationFunction {
	public double calc(double in);
	public double derivation(double in);
	public String getName();
}
