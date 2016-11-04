package br.com.fracgen.util;

import java.util.ArrayList;

public class DataSCL {

	// main property data of scanlines
	private ArrayList<Double> aperture;
	private ArrayList<Double> spacing;
	private int size;


	public ArrayList<Double> getAperture() {
		return aperture;
	}

	public void setAperture(ArrayList<Double> aperture) {
		this.aperture = aperture;
	}

	public ArrayList<Double> getSpacing() {
		return spacing;
	}

	public void setSpacing(ArrayList<Double> spacing) {
		this.spacing = spacing;
	}

}
