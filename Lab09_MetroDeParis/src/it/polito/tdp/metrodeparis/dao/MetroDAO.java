package it.polito.tdp.metrodeparis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javadocmd.simplelatlng.LatLng;


import it.polito.tdp.metrodeparis.model.CoppiaFermate;
import it.polito.tdp.metrodeparis.model.Fermata;

public class MetroDAO {

	//da cancellare --------------------------------
	private int cont2=0;
	
	public int getCont2(){
		return cont2;
	}
	
	//----------------------------------
	
	public List<Fermata> getAllFermate() {

		final String sql = "SELECT id_fermata, nome, coordx, coordy FROM fermata ORDER BY nome ASC";
		List<Fermata> fermate = new ArrayList<Fermata>();

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				cont2++;
				Fermata f = new Fermata(rs.getInt("id_Fermata"), rs.getString("nome"), new LatLng(rs.getDouble("coordx"), rs.getDouble("coordy")));
				fermate.add(f);
			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}

		return fermate;
	}
	//da cancellare ----------------------------
	private int cont=0;
	
	public int getCont(){
		return cont;
	}
	//--------------------------
	
	//TODO: METODO PER PRENDERE DALLA TABELLA CONNESSIONI I VERTICI ADIACENTI E CREARE GLI ARCHI DEL GRAFO NEL MODEL.
	public List <CoppiaFermate> listCoppieFermateAdiacenti(){
		
		final String sql ="SELECT c.id_stazP, c.id_stazA, f.nome, f1.nome, f.coordx, f1.coordx, f.coordy, "
				+ "f1.coordy, c.id_linea, l.nome, l.velocita, l.colore, l.intervallo"
				+ " FROM connessione c, fermata f, fermata f1, linea l "
				+ "WHERE c.id_stazP=f.id_fermata AND c.id_stazA=f1.id_fermata AND c.id_linea=l.id_linea ";
				
		try {
					Connection conn = DBConnect.getInstance().getConnection();
					PreparedStatement st = conn.prepareStatement(sql);
								
					ResultSet rs = st.executeQuery() ;
					
					List<CoppiaFermate> list = new ArrayList<>() ;
					
					while(rs.next()) {
						cont++;
						list.add(new CoppiaFermate(
								new Fermata(rs.getInt("id_stazP"), rs.getString("f.nome"), new LatLng(rs.getDouble("f.coordx"), rs.getDouble("f.coordy"))),
								new Fermata(rs.getInt("id_stazA"), rs.getString("f1.nome"), new LatLng(rs.getDouble("f1.coordx"), rs.getDouble("f1.coordy"))),
								new Linea (rs.getInt("c.id_linea"),rs.getString("l.nome"),rs.getInt("l.velocita"), rs.getString("l.colore"),rs.getInt("l.intervallo")))); 
					}
					
					rs.close();
					conn.close();
					
					return list ;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null ;
				}
		
	}
	
}
