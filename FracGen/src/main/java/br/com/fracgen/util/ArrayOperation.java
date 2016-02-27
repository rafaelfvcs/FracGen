package br.com.fracgen.util;

import java.util.ArrayList;

public class ArrayOperation {

	/*
	 *
	 */
	public static ArrayList<Double> sumNumber(ArrayList<Double> ar, double num){

		ArrayList<Double> result = new ArrayList<>();

		for (Double d : ar) {
			result.add(d + num);
		}
		return result;
	}

	/*
	 *
	 */
	public static ArrayList<Double> subtractNumber(ArrayList<Double> ar, double num){

		ArrayList<Double> result = new ArrayList<>();

		for (Double d : ar) {
			result.add(d - num);
		}
		return result;
	}

	/*
	 *
	 */
	public static ArrayList<Double> multiplyNumber(ArrayList<Double> ar, double num){

		ArrayList<Double> result = new ArrayList<>();

		for (Double d : ar) {
			result.add(d * num);
		}
		return result;
	}

	/*
	 *
	 */
	public static ArrayList<Double> divNumber(ArrayList<Double> ar, double num){

		ArrayList<Double> result = new ArrayList<>();

		for (Double d : ar) {
			result.add(d/ num);
		}
		return result;
	}

	/*
	 *
	 */
	public static ArrayList<Double> sumArray(ArrayList<Double> array1, ArrayList<Double> array2){

		ArrayList<Double> result = new ArrayList<>();

		for (int i = 0; i < array1.size(); i++) {
			result.add( array1.get(i) + array2.get(i));
		}
		return result;
	}

	/*
	 *
	 */
	public static ArrayList<Double> subtractArray(ArrayList<Double> array1, ArrayList<Double> array2){

		ArrayList<Double> result = new ArrayList<>();

		for (int i = 0; i < array1.size(); i++) {
			result.add( array1.get(i) - array2.get(i));
		}
		return result;
	}

	/*
	 *
	 */
	public static ArrayList<Double> divArray(ArrayList<Double> array1, ArrayList<Double> array2){

		ArrayList<Double> result = new ArrayList<>();

		for (int i = 0; i < array1.size(); i++) {
			result.add( array1.get(i) / array2.get(i));
		}
		return result;
	}



}
