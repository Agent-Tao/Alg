package com.tao.ml.nn;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.tao.ml.common.ActivationFunction;

public class OutputNode implements Node {

	public double _offset;
	public double _Y;
	public double _output;
	private ActivationFunction activationFunction;
	public Map<Node,Double> inlinks = new HashMap<Node,Double>();
	
	public OutputNode(double x,double y) {
		_offset = x;
		_Y = y;
	}
	
	public void addLinks(double w,Node node) {
		inlinks.put(node, w);
	}
	
	public void setActivationFunction(ActivationFunction activationFunction) {
		this.activationFunction = activationFunction;
	}
	
	@Override
	public double forward() {
		double sum=_offset;
		for (Entry<Node,Double> entry: inlinks.entrySet()) {
			sum+=entry.getKey().forward()*entry.getValue();
		}
		_output = activationFunction.calc(sum);
		return _output;
	}

	@Override
	public double backward(double y) {
		double err = activationFunction.derivation(_output)*(_Y-_output);
		_offset += NeuralNetwork.learnRate*err;
		for (Entry<Node,Double> entry: inlinks.entrySet()) {
			double w = entry.getValue();
			entry.getKey().backward(err*w);
			w +=  NeuralNetwork.learnRate*err*entry.getKey().getOutput();
			entry.setValue(w);
			System.out.println(w);
		}
			
		return 0;
	}

	@Override
	public double getOutput() {
		// TODO Auto-generated method stub
		return _output;
	}

}
