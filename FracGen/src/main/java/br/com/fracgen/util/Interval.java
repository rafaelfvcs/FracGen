package br.com.fracgen.util;

import java.util.ArrayList;

public abstract class Interval {


	/*
	 * methods Ok! igual ao Matlab
	 */
	public static ArrayList<Double> interv(double min,  double delta, double max){

		ArrayList<Double> vet = new ArrayList<>();

		vet.add(min);

		long d = Math.round(((max - min)/delta));

		for(int i=1; i< d+1; i++){

			min +=delta;

			if(min > max){
				break;
			}else{
				vet.add(min);
			}
		}
		return vet;
	}


	/*
	 * Funcao Ok Igual ao Matlab
	 */
	public static ArrayList<Double> linspace(double min, double max, int numInt){

		double delta = (max-min)/(numInt-1);

		ArrayList<Double> vet = new ArrayList<>();

		for(int i=0; i< numInt; i++){
			vet.add(min + i*delta);
		}
		return vet;
	}

	public static ArrayList<Double> logspace(double min, double max, int numInt){

		return null;
	}
}
