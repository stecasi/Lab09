package it.polito.tdp.metrodeparis.model;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.metrodeparis.dao.Linea;

public class CoppiaFermate {
	
	private Fermata f1;
	private Fermata f2;
	private double tempoPercorrenza;
	private double distanza;
	private Linea l;
	
	public CoppiaFermate(Fermata f1, Fermata f2, Linea linea){
		this.f1=f1;
		this.f2=f2;
		this.l=linea;
		this.distanza = LatLngTool.distance(f1.getCoords(),f2.getCoords(),LengthUnit.KILOMETER);
		this.tempoPercorrenza = this.calcolaTempo(f1, f2);
	}

	

	private double calcolaTempo(Fermata ferm1, Fermata ferm2) {
		double tempotemp = this.distanza/this.l.getVelocitaLinea();
		
		return tempotemp;
	}

	public Fermata getF1() {
		return f1;
	}

	public void setF1(Fermata f1) {
		this.f1 = f1;
	}

	
	public Fermata getF2() {
		return f2;
	}

	public void setF2(Fermata f2) {
		this.f2 = f2;
	}

	public double getTempoPercorrenza() {
		return tempoPercorrenza;
	}

	public void setTempoPercorrenza(double tempoPercorrenza) {
		this.tempoPercorrenza = tempoPercorrenza;
	}

	
	public String toString() {
		return "CoppiaFermate [" + f1 + ", " + f2 + ", "+l+ "]";
	}
	
	

}
