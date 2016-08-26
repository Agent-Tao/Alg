package com.tao.ml.nn;

import com.tao.ml.common.ActivationFunction;
import com.tao.ml.common.sigmoid;

public class NeuralNetwork {

	static double learnRate = 0.9; 
	
	public static void main(String[] args) {
		InputNode in1 = new InputNode(1);
		InputNode in3 = new InputNode(1);
		
		HiddenNode h4 = new HiddenNode(-0.4);
		HiddenNode h5 = new HiddenNode(0.2);
		OutputNode o6 = new OutputNode(0.1,1);
		
		ActivationFunction af = new sigmoid();
		h4.setActivationFunction(af);
		h5.setActivationFunction(af);
		o6.setActivationFunction(af);
		
		h4.addLinks(0.2, in1);
		h4.addLinks(-0.5, in3);
		
		h5.addLinks(-0.3, in1);
		h5.addLinks(0.2, in3);
		
		o6.addLinks(-0.3, h4);
		o6.addLinks(-0.2, h5);
		
		System.out.println(h4.forward());
		System.out.println(h5.forward());
		System.out.println("!!!"+o6.forward());
		
		for(int i=0;i<10000;i++) {
			o6.backward(1);
			System.out.println("!!!"+o6.forward());
		}
	}

}
