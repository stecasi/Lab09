package it.polito.tdp.metrodeparis.model;

public class TestModel {
	
	

	public static void main(String[] args) {
		
		Model model = new Model();
		System.out.println("GRAFO:");
		model.creaGrafo();
		model.printStats();
		
		model.calcolaPercorso(model.getStazioni().get(2), model.getStazioni().get(5));
		System.out.println(model.getPercorsoEdgeList());
		System.out.println(model.getPercorsoTempoTotale());

	}

}
