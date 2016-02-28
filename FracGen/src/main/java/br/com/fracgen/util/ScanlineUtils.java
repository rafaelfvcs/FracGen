package br.com.fracgen.util;

import java.util.ArrayList;
import java.util.Arrays;

import br.com.fracgen.statistic.Stat;

public class ScanlineUtils {


	public static FreqAperture unique(ArrayList<Double> freq, ArrayList<Double> ap1){

		ArrayList<Double> apf = new ArrayList<>();
		FreqAperture freqAp = new FreqAperture();

		ArrayList<Double> freq2 = ArrayOperation.sort(freq, true);
		apf = ArrayOperation.unique(ap1);

		ArrayList<Double> apn = new ArrayList<>();
		ArrayList<Double> frecn = new ArrayList<>();

		for (int i = 0; i < freq2.size(); i++) {

			if(apf.get(i) != 0.0){
				apn.add(apf.get(i));
				frecn.add(freq2.get(i));
			}
		}


		freqAp.setAperture(apn);
		freqAp.setFrequency(frecn);


        return freqAp;

	}



}
