package com.tao.ml.nn;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.tao.ml.common.ActivationFunction;
import com.tao.ml.common.sigmoid;

public class HiddenNode implements Node{

	public double _offset;
	public double _output;
	private ActivationFunction activationFunction;
	public Map<Node,Double> inlinks = new HashMap<Node,Double>();
	
	public HiddenNode(double x) {
		_offset = x;
	}
	
	public void addLinks(double w,Node node) {
		inlinks.put(node, w);
	}
	
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
		double err = activationFunction.derivation(_output)*y;
		_offset += NeuralNetwork.learnRate*err;
		for (Entry<Node,Double> entry: inlinks.entrySet()) {
			double w = entry.getValue();
			entry.getKey().backward(w);
			w +=  NeuralNetwork.learnRate*err*entry.getKey().getOutput();
			entry.setValue(w);
			System.out.println(w);
		}
			
		return 0;
	}
	
	public void setActivationFunction(ActivationFunction activationFunction) {
		this.activationFunction = activationFunction;
	}

	public static void main(String[] args) {
		
		InputNode in1 = new InputNode(1);
		InputNode in2 = new InputNode(1);
		
		HiddenNode h1 = new HiddenNode(-0.4);
		ActivationFunction af = new sigmoid();
		h1.setActivationFunction(af);
		
		h1.addLinks(0.2, in1);
		h1.addLinks(-0.5, in2);
		
		System.out.println(h1.forward());
		
	}

	@Override
	public double getOutput() {
		return _output;
	}

}
