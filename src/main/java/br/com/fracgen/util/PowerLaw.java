package br.com.fracgen.util;

public class PowerLaw {

	/*
	 *  y = a * x ^ -k
	 */
	private double a;
	private double k;

	private double r2;

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public double getK() {
		return k;
	}

	public void setK(double k) {
		this.k = k;
	}

	public double getR2() {
		return r2;
	}

	public void setR2(double r2) {
		this.r2 = r2;
	}
}
