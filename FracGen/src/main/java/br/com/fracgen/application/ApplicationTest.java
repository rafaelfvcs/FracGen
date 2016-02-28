package br.com.fracgen.application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import br.com.fracgen.statistic.Stat;
import br.com.fracgen.util.ArrayOperation;
import br.com.fracgen.util.DataSCL;
import br.com.fracgen.util.Interval;
import br.com.fracgen.util.OpenScanlineData;

public abstract class ApplicationTest {
	public static void main(String[] args) {

		/*
		Random r = new Random();
		ArrayList<Double> vars = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			double nr = r.nextDouble()*10;
			vars.add(nr);
			System.out.println(nr);
		}
		System.out.println("");

		System.out.println(Stat.max(vars));

		System.out.println(Stat.min(vars));

		System.out.println(Stat.sum(vars));
		 */
//		DataSCL d = new DataSCL();
//		d.setName("Nova Olinda");
//		d.setSize("20 metros");


		DataSCL d = OpenScanlineData.openScl("src/main/resources/data.dat");

//		for (int i = 0; i < d.getAperture().size(); i++) {
//			System.out.print("Spacing :" + d.getSpacing().get(i));
//			System.out.print("\t");
//			System.out.println("Aperture :" + d.getAperture().get(i));
//		}

		System.out.println();
		Double[] ap = ArrayOperation.arrayListToArray(d.getAperture());
		Double[] sp = ArrayOperation.arrayListToArray(d.getSpacing());

		for (int i = 0; i < sp.length; i++) {
			System.out.println(sp[i]);
		}





		Arrays.sort(sp,Collections.reverseOrder());

		System.out.println("\n");
		for (int i = 0; i < sp.length; i++) {
			System.out.println(sp[i]);
		}

		System.out.println();


		double[] lf = Stat.getLinearFit(ap, sp);

		System.out.println(lf[0] + " " + lf[1]);

		System.out.println("\n");


		double[] dt = Interval.intervArray(1, 2, 30);


		for (int i = 0; i < dt.length; i++) {
			System.out.println(dt[i]);
		}

		//System.out.println("Novos :" + d.getSpacing().get(2));

//		ArrayList<Double> v;
//		ArrayList<Double> v1;
//		ArrayList<Double> values;
//		ArrayList<Double> values2;
//
//		v = Interval.linspace(1, 10, 5);

//		v1 = Interval.interv(1, 20, 200);
//
//		for (int i = 0; i < v1.size(); i++) {
//			System.out.println(v1.get(i));
//		}
//		for (int i = 0; i < v.size(); i++) {
//			System.out.println(v.get(i));
//		}
//		values = Interval.interv(1, 1, 100);
//		values2 = Interval.interv(1, 1, 100);

//		ArrayList<Double> av = ArrayOperation.divArray(values, values2);

//		for (int i = 0; i < values.size(); i++) {
//			System.out.println(values.get(i));
//		}
		/*
		for (Double d : av) {
			System.out.println(d);
		}
		System.out.println("O tamanho do veto é = " + values.size());
		*/

		/*
		Double[] v = {12.0,32.,54.,6.,8.,89.,64.,64.,6.};

		ArrayList<Double> v2 = Interval.interv(0, 1, 4);

		Double[] vd = ArrayOperation.arrayListToArray(v2);

		double res = Stat.calculateMean(v);


		double resMed = Stat.calculateMean(vd);
		double resStd = Stat.getStdDev(vd);

		System.out.println(resMed);
		System.out.println();
		System.out.println(resStd);

		for (int i = 0; i < vd.length; i++) {
			System.out.println(vd[i]);
		}
		*/


	}
}
