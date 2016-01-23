package br.com.fracgen.statistic;

import java.util.ArrayList;

public abstract class Stat {



	public static double min(ArrayList<Double> array){
		double minV = array.get(0);
		for (int i = 0; i < array.size(); i++) {
			if(array.get(i)<minV){
				minV = array.get(i);
			}
		}
		return minV;
	}

	public static double max(ArrayList<Double> array){
		double maxV = array.get(0);
		for (int i = 0; i < array.size(); i++) {
			if(array.get(i)>maxV){
				maxV = array.get(i);
			}
		}
		return maxV;
	}

	public static double sum(ArrayList<Double> array){
		double sumv = 0;
		for (int i = 0; i < array.size(); i++) {
			sumv += array.get(i);
		}
		return sumv;
	}

}
