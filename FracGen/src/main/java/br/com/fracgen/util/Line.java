package br.com.fracgen.util;

/*
 * Cria retas --> y = ax + b
 */
public class Line {

	public static double[] horizontal(double xc, double yc, double length){

		double[] points = new double[4]; // p1, p2
		points[0] = xc - length/2;
		points[1] = yc;

		points[2] = xc + length/2;
		points[3] = yc;

//		points[4] = aperture;

		return points;
	}

	public static double[] vertical(double xc, double yc, double length){

		double[] points = new double[4]; // p1, p2
		points[0] = xc;
		points[1] = yc - length/2;

		points[2] = xc ;
		points[3] = yc + length/2;

//		points[4] = aperture;

		return points;
	}

	public static double[] oblique(double xc, double yc,  double length, double theta){

		theta = 90 + theta; // para azimuth relativo ao norte
		double[] points = new double[4]; // p1, p2
		points[0] = xc + length/2*Math.cos(Math.toRadians(theta));
		points[1] = yc + length/2*Math.sin(Math.toRadians(theta));

		points[2] = xc - length/2*Math.cos(Math.toRadians(theta));
		points[3] = yc - length/2*Math.sin(Math.toRadians(theta));

		return points;
	}

}
