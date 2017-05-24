package it.polito.tdp.metrodeparis.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.graph.WeightedMultigraph;

import it.polito.tdp.metrodeparis.dao.MetroDAO;

public class Model {
	
	private MetroDAO dao = new MetroDAO();
	private List <Fermata> fermate = new ArrayList <Fermata>();
	private WeightedMultigraph <Fermata, DefaultWeightedEdge> grafo = null;
	
	private List<DefaultWeightedEdge> shortestPathEdgeList = null;
	private double shortestPathTempoTotale = -1;
	
	public List <Fermata> getStazioni() {
		if (fermate.isEmpty()){
			fermate = dao.getAllFermate();
		}
		return fermate;
	}
	
	private  WeightedMultigraph getGrafo() {
		if(this.grafo==null) {
			this.creaGrafo();
		}
		return this.grafo ;
	}

	public void creaGrafo(){
		if (this.grafo==null){
		this.grafo= new WeightedMultigraph<Fermata, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		//CREO I VERTICI
		Graphs.addAllVertices(grafo, getStazioni());
		
		//CREO GLI ARCHI
		for(CoppiaFermate cf : dao.listCoppieFermateAdiacenti()) {
			DefaultWeightedEdge e = grafo.addEdge(cf.getF1(), cf.getF2());
			grafo.setEdgeWeight(e, cf.getTempoPercorrenza());
			
		}
		
		System.out.println(grafo);
		}
	}
	
	public void printStats() {
		System.out.format("Grafo: Vertici %d, Archi %d\n", grafo.vertexSet().size(), grafo.edgeSet().size());
	}

	
	
	public void calcolaPercorso(Fermata partenza, Fermata arrivo) {
		if (partenza.equals(arrivo)){
			throw new RuntimeException("Devi selezionare una stazione di arrivo diversa da quella di partenza");
		}
		
		DijkstraShortestPath<Fermata, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<Fermata, DefaultWeightedEdge>(grafo, partenza, arrivo);

		shortestPathEdgeList = dijkstra.getPathEdgeList();
		shortestPathTempoTotale = dijkstra.getPathLength();

		if (shortestPathEdgeList == null)
			throw new RuntimeException("Non è stato possiible crare un percorso.");

		// Nel calcolo del tempo non tengo conto della prima e dell'ultima fermata
		if (shortestPathEdgeList.size() - 1 > 0) {
			shortestPathTempoTotale += (shortestPathEdgeList.size() - 1) * 30;
		}
	}

	public String getPercorsoEdgeList() {
		if (shortestPathEdgeList == null)
			throw new RuntimeException("Non è stato creato alcun percorso.");

		StringBuilder risultato = new StringBuilder();
		risultato.append("Percorso: [ ");

		for (DefaultWeightedEdge link : shortestPathEdgeList) {
			risultato.append(grafo.getEdgeTarget(link).getNome());
			risultato.append(", ");
		}
		risultato.setLength(risultato.length() - 2);
		risultato.append("]");

		return risultato.toString();
	}

	public double getPercorsoTempoTotale() {
		if (shortestPathEdgeList == null)
			throw new RuntimeException("Non è stato creato alcun percorso.");
		

		return shortestPathTempoTotale;
	}
	
}
