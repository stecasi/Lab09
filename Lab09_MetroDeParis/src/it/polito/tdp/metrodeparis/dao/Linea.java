package it.polito.tdp.metrodeparis.dao;

public class Linea {
	
	private int idLinea;
	private String nomeLinea;
	private int velocitaLinea;
	private String coloreLinea;
	private int intervalloLinea;
	
	public Linea(int idLinea, String nomeLinea, int velocitaLinea, String coloreLinea, int intervalloLinea){
		this.idLinea=idLinea;
		this.nomeLinea=nomeLinea;
		this.velocitaLinea=velocitaLinea;
		this.coloreLinea=coloreLinea;
		this.intervalloLinea=intervalloLinea;
		
	}

	public int getIdLinea() {
		return idLinea;
	}

	public void setIdLinea(int idLinea) {
		this.idLinea = idLinea;
	}

	public String getNomeLinea() {
		return nomeLinea;
	}

	public void setNomeLinea(String nomeLinea) {
		this.nomeLinea = nomeLinea;
	}

	public int getVelocitaLinea() {
		return velocitaLinea;
	}

	public void setVelocitaLinea(int velocitaLinea) {
		this.velocitaLinea = velocitaLinea;
	}

	public String getColoreLinea() {
		return coloreLinea;
	}

	public void setColoreLinea(String coloreLinea) {
		this.coloreLinea = coloreLinea;
	}

	public int getIntervalloLinea() {
		return intervalloLinea;
	}

	public void setIntervalloLinea(int intervalloLinea) {
		this.intervalloLinea = intervalloLinea;
	}

	@Override
	public String toString() {
		return "Linea [idLinea=" + idLinea + "]";
	}
	
	

}
