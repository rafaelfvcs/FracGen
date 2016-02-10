package br.com.fracgen.application;

import java.util.ArrayList;
import java.util.Random;

import br.com.fracgen.statistic.Stat;
import br.com.fracgen.util.DataSCL;
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

		for (int i = 0; i < d.getAperture().size(); i++) {
			System.out.println("Novos :" + d.getSpacing().get(i));
		}
		//System.out.println("Novos :" + d.getSpacing().get(2));

	}

}
