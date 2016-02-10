package br.com.fracgen.application;

import java.util.ArrayList;
import java.util.Random;

import br.com.fracgen.statistic.Stat;

public abstract class ApplicationTest {
	public static void main(String[] args) {
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


	}

}
