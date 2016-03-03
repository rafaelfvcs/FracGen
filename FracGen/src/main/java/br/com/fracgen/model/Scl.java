package br.com.fracgen.model;

import javafx.beans.property.SimpleDoubleProperty;

public class Scl {

	private SimpleDoubleProperty ap;
	private SimpleDoubleProperty sp;


	public Scl(double ap, double sp){
		this.ap = new SimpleDoubleProperty(ap);
		this.sp = new SimpleDoubleProperty(sp);
	}


	public Double getAp() {
		return ap.get();
	}


	public Double getSp() {
		return sp.get();
	}







}
