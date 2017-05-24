package it.polito.tdp.metrodeparis.dao;

import java.util.List;

import it.polito.tdp.metrodeparis.model.CoppiaFermate;
import it.polito.tdp.metrodeparis.model.Fermata;

public class TestDAO {

	public static void main(String[] args) {
		
		MetroDAO metroDAO = new MetroDAO();
		
		System.out.println("Lista fermate");
		System.out.println(metroDAO.getAllFermate());
		System.out.println("Le fermate totali sono:");
		System.out.println(metroDAO.getCont2());
		
		
		System.out.println("Lista coppia fermate");
		
		System.out.println(metroDAO.listCoppieFermateAdiacenti());
		
		System.out.println("Le coppie totali sono:");
		System.out.println(metroDAO.getCont());
		
	}

}
