package br.com.fracgen.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import br.com.fracgen.statistic.Stat;

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
	public static Double[] divArrayByNumber(double[] ar, double num){

		Double[] result = new Double[ar.length];

		for (int i = 0; i < result.length; i++) {
			result[i] = ar[i] /num;
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

	/*
	 * Parse ArrayList to Array[]
	 */
	public static Double[] arrayListToArray(ArrayList<Double> arraylist){

		int sizeArray = arraylist.size();

		Double[] res = new Double[sizeArray];

		for (int i = 0; i < res.length; i++) {
			res[i] = arraylist.get(i);
		}

		return res;
	}

	public static ArrayList<Double> arrayToArrayList(Double[] array){

		ArrayList<Double> result = new ArrayList<>();

		for (int i = 0; i < array.length; i++) {
			result.add(array[i]);
		}

		return result;
	}


	/*
	 * Array com unicos valores
	 */


	public static ArrayList<Double> unique(ArrayList<Double> array1){



		ArrayList<Double> array = new ArrayList<>(array1);
		ArrayList<Double> result = new ArrayList<>();

		ArrayList<Double> resultf = new ArrayList<>();

		ArrayList<Integer> nullpoints = new ArrayList<>();


		int max = array.size();

		for (int i = 0; i < max; i++) {

			double minValue = Stat.min(array);

			if(!result.contains(minValue)){
				result.add(minValue);

			}else{
				result.add(0.0);
				nullpoints.add(i); //index that repeat
			}

			array.remove(minValue);
		}


		for (int i = 0; i < max; i++) {
			if(!result.get(i).equals(0.0)){
				resultf.add(result.get(i));
			}
		}

        return result;
	}


	public static ArrayList<Double> sort(ArrayList<Double> array1, boolean reverseOrder){

		ArrayList<Double> array = new ArrayList<>(array1);
		ArrayList<Double> result = new ArrayList<>();

		int max = array.size();

		if (reverseOrder) {
			for (int i = 0; i < max; i++) {

				result.add(Stat.max(array));

				array.remove(Stat.max(array));
			}


		} else {
			for (int i = 0; i < max; i++) {

				result.add(Stat.min(array));

				array.remove(Stat.min(array));
			}
		}


		return result;
	}

	public static ArrayList<Double> log10(ArrayList<Double> array){

		ArrayList<Double> result = new ArrayList<>();



		for (int i = 0; i < array.size(); i++) {

			result.add(Math.log10(array.get(i)));


		}



		return result;
	}




}
