package nfracgen.model;


import java.util.ArrayList;

import nfracgen.statistic.Stat;
import nfracgen.util.ArrayOperation;
import nfracgen.util.DataSCL;
import nfracgen.util.FreqAperture;
import nfracgen.util.Interval;
import nfracgen.util.OpenScanlineData;
import nfracgen.util.PowerLaw;
import nfracgen.util.ScanlineUtils;

public class PowerLawOrtega {



	public static PowerLaw findCoefficients(String file){

		PowerLaw pl = new PowerLaw();

		Double[][] freqAperture = findFreqApertureLogOrtega(file);

		Double[] x = new Double[freqAperture.length];
		Double[] y = new Double[freqAperture.length];
		for (int i = 0; i < freqAperture.length; i++) {
			y[i] = freqAperture[i][0];
			x[i] = freqAperture[i][1];
		}

		double slop = Stat.getSlope(x, y);

		double[] linearFit = Stat.getLinearFit(x, y);

		double corr = Stat.getCorrelation(x, y);

		pl.setA(Math.pow(10, linearFit[0]));
		//pl.setA(linearFit[0]);

		pl.setK(linearFit[1]);
		pl.setR2(corr*corr);

		System.out.println("K = " + linearFit[1]);
		System.out.println("a = " + Math.pow(10, linearFit[0]));
		return pl;
	}

	public static PowerLaw findCoefficients(ArrayList<Scl> sclnew){

		PowerLaw pl = new PowerLaw();

		Double[][] freqAperture = findFreqApertureLogOrtega(sclnew);

		Double[] x = new Double[freqAperture.length];
		Double[] y = new Double[freqAperture.length];
		for (int i = 0; i < freqAperture.length; i++) {
			y[i] = freqAperture[i][0];
			x[i] = freqAperture[i][1];
		}

		double slop = Stat.getSlope(x, y);

		double[] linearFit = Stat.getLinearFit(x, y);

		double corr = Stat.getCorrelation(x, y);

		pl.setA(Math.pow(10, linearFit[0]));
		//pl.setA(linearFit[0]);
		pl.setK(linearFit[1]);
		pl.setR2(corr*corr);

		System.out.println("K = " + linearFit[1]);
		System.out.println("a = " + Math.pow(10, linearFit[0]));
		return pl;
	}

	public static void showValues(PowerLaw pl){
		System.out.println(" y = a * x ^ - k ");
		System.out.println("value a = " + pl.getA() + " ; " + "value k = " + pl.getK());
		System.out.println("value R2 = " + pl.getR2());
	}


	public static Double[][] findFreqApertureLogOrtega(String fileName){

		DataSCL d = OpenScanlineData.openScl(fileName);

		Double[] ap = ArrayOperation.arrayListToArray(d.getAperture());
		Double[] sp = ArrayOperation.arrayListToArray(d.getSpacing());

		double sclLenght = (Stat.sum(d.getAperture()) + Stat.sum(d.getSpacing()))/1000;

		double[] classe = Interval.intervArray(1, 1, sp.length);

		Double[] freqAcum = ArrayOperation.divArrayByNumber(classe, sclLenght);


		ArrayList<Double> freqAcList = ArrayOperation.arrayToArrayList(freqAcum);

		ArrayList<Double> apertureSort = ArrayOperation.sort(d.getAperture(),true);

		FreqAperture freqAcum_aperture = ScanlineUtils.unique(freqAcList, apertureSort);


		ArrayList<Double> aplog = ArrayOperation.log10(freqAcum_aperture.getAperture());
//		ArrayList<Double> aplog = freqAcum_aperture.getAperture();

		ArrayList<Double> freqlog = ArrayOperation.log10(freqAcum_aperture.getFrequency());
//		ArrayList<Double> freqlog = freqAcum_aperture.getFrequency();

		Double[] x = ArrayOperation.arrayListToArray(aplog);
		Double[] y = ArrayOperation.arrayListToArray(freqlog);

		Double[][] freqAp = new Double[x.length][y.length];

		for (int i = 0; i < freqAp.length; i++) {
			freqAp[i][0] = y[i]; //freq
			freqAp[i][1] = x[i]; //aperture
		}

		return freqAp;
	}


	public static Double[][] findFreqApertureLogOrtega(ArrayList<Scl> scldata){

//		DataSCL d = OpenScanlineData.openScl(file);

		ArrayList<Double> aplist = new ArrayList<>();
		ArrayList<Double> splist = new ArrayList<>();

		for (int i = 0; i < scldata.size(); i++) {
			aplist.add(scldata.get(i).getAp());
			splist.add(scldata.get(i).getSp());
		}

		Double[] ap = ArrayOperation.arrayListToArray(aplist);
		Double[] sp = ArrayOperation.arrayListToArray(splist);

		double sclLenght = (Stat.sum(aplist) + Stat.sum(splist))/1000;

		double[] classe = Interval.intervArray(1, 1, sp.length);

		Double[] freqAcum = ArrayOperation.divArrayByNumber(classe, sclLenght);


		ArrayList<Double> freqAcList = ArrayOperation.arrayToArrayList(freqAcum);

		ArrayList<Double> apertureSort = ArrayOperation.sort(aplist,true);

		FreqAperture freqAcum_aperture = ScanlineUtils.unique(freqAcList, apertureSort);


		ArrayList<Double> aplog = ArrayOperation.log10(freqAcum_aperture.getAperture());

		ArrayList<Double> freqlog = ArrayOperation.log10(freqAcum_aperture.getFrequency());

		Double[] x = ArrayOperation.arrayListToArray(aplog);
		Double[] y = ArrayOperation.arrayListToArray(freqlog);

		Double[][] freqAp = new Double[x.length][y.length];

		for (int i = 0; i < freqAp.length; i++) {
			freqAp[i][0] = y[i];
			freqAp[i][1] = x[i];
		}

		return freqAp;
	}



}
