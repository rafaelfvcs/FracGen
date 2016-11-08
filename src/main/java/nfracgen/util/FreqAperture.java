package nfracgen.util;

import java.util.ArrayList;

public class FreqAperture {

	private ArrayList<Double> frequency;
	private ArrayList<Double> aperture;

	private ArrayList<Integer> nullpoints;

	public ArrayList<Double> getFrequency() {
		return frequency;
	}

	public void setFrequency(ArrayList<Double> frequency) {
		this.frequency = frequency;
	}

	public ArrayList<Double> getAperture() {
		return aperture;
	}

	public void setAperture(ArrayList<Double> aperture) {
		this.aperture = aperture;
	}

	public ArrayList<Integer> getNullpoints() {
		return nullpoints;
	}

	public void setNullpoints(ArrayList<Integer> nullpoints) {
		this.nullpoints = nullpoints;
	}

}
