package com.tao.ml.nn;

public class InputNode implements Node{
	
	public double _X;
	
	public InputNode(double x) {
		_X = x;
	}
	
	@Override
	public double forward() {
		return _X;
	}

	@Override
	public double backward(double y) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getOutput() {
		// TODO Auto-generated method stub
		return _X;
	}

}
