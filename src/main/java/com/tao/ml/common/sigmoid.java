package com.tao.ml.common;

public class sigmoid implements ActivationFunction{

	@Override
	public double calc(double in) {
		return (1.0 / (1 + Math.exp(-in)));
	}

	@Override
	public double derivation(double in) {
		return in*(1-in);
	}

	@Override
	public String getName() {
		return "sigmod";
	}

}
