package br.com.fracgen.util;

public class RoundUtil {

	public static double trunc(double valor, int casas) {
		double p = Math.pow(10, casas);
		return Math.floor(((valor + 0.00000001) * p)) / p;
	}

	public static double round(double valor, int casas) {
		double p = Math.pow(10, casas);
		return Math.round(valor * p) / p;
	}
}
