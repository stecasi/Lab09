package it.polito.tdp.metrodeparis;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.metrodeparis.model.Fermata;
import it.polito.tdp.metrodeparis.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class MetroDeParisController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="lblPartenza"
    private Label lblPartenza; // Value injected by FXMLLoader

    @FXML // fx:id="cmbPartenza"
    private ComboBox<Fermata> cmbPartenza; // Value injected by FXMLLoader

    @FXML // fx:id="lblArrivo"
    private Label lblArrivo; // Value injected by FXMLLoader

    @FXML // fx:id="cmbArrivo"
    private ComboBox<Fermata> cmbArrivo; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="btnCalcola"
    private Button btnCalcola; // Value injected by FXMLLoader

	private Model model;

    @FXML
    void calcolaPercorso(ActionEvent event) {
    	
    	Fermata partenza = cmbPartenza.getValue();
    	Fermata arrivo = cmbArrivo.getValue();
    	
    	if (partenza==null || arrivo==null ){
    		txtResult.setText("Devi inserire una stazione di partenza e una di arrivo");
    		return;
    	}
    	if (partenza.equals(arrivo)){
    		txtResult.setText("La stazione di arrivo deve essere diversa dalla stazione di partenza");
    		return;
    	}
    	
    	model.creaGrafo();
    	model.calcolaPercorso(partenza, arrivo);
    	txtResult.appendText("Il miglior percorso calcolato è: \n");
    	txtResult.appendText(model.getPercorsoEdgeList()+"\n");
    	txtResult.appendText("Il tempo di percorrenza totale è: \n");
    	txtResult.appendText(model.getPercorsoTempoTotale()+" secondi");

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert lblPartenza != null : "fx:id=\"lblPartenza\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert cmbPartenza != null : "fx:id=\"cmbPartenza\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert lblArrivo != null : "fx:id=\"lblArrivo\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert cmbArrivo != null : "fx:id=\"cmbArrivo\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert btnCalcola != null : "fx:id=\"btnCalcola\" was not injected: check your FXML file 'MetroDeParis.fxml'.";

    }

	public void setModel(Model model) {
		this.model=model;
		cmbPartenza.getItems().addAll(this.model.getStazioni());
		cmbArrivo.getItems().addAll(this.model.getStazioni());
		
	}
}
