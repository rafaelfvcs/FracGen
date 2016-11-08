package nfracgen.application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.jfree.data.xy.XYSeries;
import org.jfree.ui.RefineryUtilities;

import nfracgen.model.PowerLawOrtega;
import nfracgen.statistic.Stat;
import nfracgen.util.ArrayOperation;
import nfracgen.util.DataSCL;
import nfracgen.util.FreqAperture;
import nfracgen.util.Interval;
import nfracgen.util.LogLog;
import nfracgen.util.OpenScanlineData;
import nfracgen.util.PowerLaw;
import nfracgen.util.RoundUtil;
import nfracgen.util.ScanlineUtils;

public class ScanlineDev {



	public static void main(String[] args) {


		DataSCL d = OpenScanlineData.openScl("src/main/resources/data.dat");

		PowerLaw pl = PowerLawOrtega.findCoefficients("src/main/resources/data.dat");

		Double[][] data = PowerLawOrtega.findFreqApertureLogOrtega("src/main/resources/data.dat");

		final XYSeries series = new XYSeries("data");

		double[] xx = new double[data.length];
		double[] yy = new double[data.length];

		System.out.println(pl.getK());
		System.out.println(pl.getA());

		System.out.println(data.length);
		for (int i = 0; i < data.length; i++) {

			//System.out.println(data[i][0] + "  " + data[i][1]);

			xx[i] = Math.pow(10,data[i][1]);

//			yy[i] = Math.pow(xx[i],pl.getK())*pl.getA();
			yy[i] = Math.pow(10,data[i][0]);

			series.add(xx[i],yy[i]);

			System.out.println(Math.pow(10, data[i][0]) + "  " + Math.pow(10,data[i][1]));

		}

		for (int i = 0; i < data.length; i++) {

			//System.out.println(data[i][0] + "  " + data[i][1]);

			xx[i] = Math.pow(10,data[i][1]);

			yy[i] = Math.pow(xx[i],pl.getK())*pl.getA();

			series.add(xx[i],yy[i]);

		}

		LogLog demo;

		demo = new LogLog("Teste",series);
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);


		/*
		Double[] ap = ArrayOperation.arrayListToArray(d.getAperture());
		Double[] sp = ArrayOperation.arrayListToArray(d.getSpacing());

		double sclLenght = (Stat.sum(d.getAperture()) + Stat.sum(d.getSpacing()))/1000;

		double[] classe = Interval.intervArray(1, 1, sp.length);

//		Arrays.sort(ap,Collections.reverseOrder());

		Double[] freqAcum = ArrayOperation.divArrayByNumber(classe, sclLenght);


		ArrayList<Double> freqAcList = ArrayOperation.arrayToArrayList(freqAcum);

		ArrayList<Double> apertureSort = ArrayOperation.sort(d.getAperture(),true);

		FreqAperture freqAcum_aperture = ScanlineUtils.unique(freqAcList, apertureSort);


		ArrayList<Double> aplog = ArrayOperation.log10(freqAcum_aperture.getAperture());

		ArrayList<Double> freqlog = ArrayOperation.log10(freqAcum_aperture.getFrequency());

		Double[] x = ArrayOperation.arrayListToArray(aplog);
		Double[] y = ArrayOperation.arrayListToArray(freqlog);

		double slop = Stat.getSlope(x, y);

		double[] linearFit = Stat.getLinearFit(x, y);

		double corr = Stat.getCorrelation(x, y);

		System.out.println(slop);

		System.out.println(Math.pow(10, linearFit[0])  + " <-> " + linearFit[1]);

		System.out.println(corr*corr); //obs temos que multiplicar por 2


		System.out.println("\n");

		for (int i = 0; i < x.length; i++) {
			System.out.println(x[i] + "\t" + y[i]);
		}

		System.out.println("\n"+ "\n");

//		PowerLaw pp = PowerLawOrtega.findCoefficients("src/main/resources/data.dat");
//
//		PowerLawOrtega.showValues(pp);


		Double[][] dd = PowerLawOrtega.findFreqApertureLogOrtega("src/main/resources/data.dat");

		for (int i = 0; i < dd.length; i++) {
			System.out.println(dd[i][0] + "\t" + dd[i][1]);
		}

		double[] da = new double[dd.length];
		for (int i = 0; i < dd.length; i++) {

			da[i] = dd[i][0];
		}

		for (int i = 0; i < dd.length; i++) {

			System.out.println(da[i]);
		}

		*/




	}
}
