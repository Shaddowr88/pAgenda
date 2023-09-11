package views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.ArbreBinaire;
import model.NoeudBinaire;
import model.Stagiaire;

public class ListS extends Pane {
	
	
	private Label label = new Label(" liste d'utilisateur");
	private int sizeParam = 5;
	public ArrayList<Stagiaire> lUser;
	public ArrayList<Stagiaire> listeStagiaires;
	public NoeudBinaire noeudVersArrayList = new NoeudBinaire();
	public TableView<Stagiaire> table = new TableView<Stagiaire>();
	private ListUserPan listUserPan;
	
	public ListS(ListUserPan listUserPan, ArrayList<Stagiaire> listeStagiaires) throws IOException {
	this.listUserPan = listUserPan;
	
	this.setStyle(
			"-fx-background-color:#f1d278;"
			);
	
	table.setMinWidth(540);
	table.setMinHeight(510);
	table.setMaxWidth(540);
	table.setMaxHeight(510);
	
	ObservableList<Stagiaire> obsListeStagiaires= FXCollections.observableArrayList(listeStagiaires);
	
	obsListeStagiaires.setAll(listeStagiaires);

    TableColumn<Stagiaire, String> userlastNameCol //
    		= new TableColumn<Stagiaire, String>("Nom");
    		userlastNameCol.prefWidthProperty().bind(table.widthProperty().divide(sizeParam ));
    		userlastNameCol.setCellValueFactory(new PropertyValueFactory<Stagiaire,String>("nom"));
    		userlastNameCol.setStyle(
             		"-fx-border: 0;"
             		+ "-fx-box-shadow: none;"
             		+ "-fx-font-size:1em;"
             		);

    TableColumn<Stagiaire, String> userNameCol //
    		= new TableColumn<Stagiaire, String>("Prénom");
    	    userNameCol.prefWidthProperty().bind(table.widthProperty().divide(sizeParam ));
    		userNameCol.setCellValueFactory(new PropertyValueFactory<Stagiaire,String>("prenom"));
    		userNameCol.setStyle(
             		"-fx-border: none;"
             		+ "-fx-box-shadow: none;"
             		+ "-fx-font-size:1em;"
             		);
    TableColumn<Stagiaire, String> DepCol//
            = new TableColumn<Stagiaire, String>("Département");
            DepCol.prefWidthProperty().bind(table.widthProperty().divide(sizeParam ));
            DepCol.setCellValueFactory(new PropertyValueFactory<Stagiaire,String>("departement"));
                      
    TableColumn<Stagiaire, String> promoNameCol//
            = new TableColumn<Stagiaire, String>("Promotion");
            promoNameCol.prefWidthProperty().bind(table.widthProperty().divide(sizeParam ));               
            promoNameCol.setCellValueFactory(new PropertyValueFactory<Stagiaire,String>("promotion"));
            
    TableColumn<Stagiaire, Integer> YearCol//
            = new TableColumn<Stagiaire, Integer>("Année");
            YearCol.prefWidthProperty().bind(table.widthProperty().divide(sizeParam ));
            YearCol.setCellValueFactory(new PropertyValueFactory<Stagiaire,Integer>("annee"));    
            YearCol.setStyle(
            		"-fx-border: 0;"
            		+ "-fx-box-shadow: none;"
            		+ "-fx-font-size:1em;"
            		);
            
    table.getColumns().addAll(userlastNameCol,userNameCol, DepCol, promoNameCol,YearCol); 
	table.setItems(obsListeStagiaires);
 	
	table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
		
		Card c = new Card(listUserPan); 
		 TranslateTransition scaleTransition = new TranslateTransition(Duration.seconds(1),c); 
		KeyValue keyValue = new KeyValue(this.prefWidthProperty(),200);
			Duration duration = Duration.seconds(0.33);
			KeyFrame keyFrame = new KeyFrame(duration, keyValue);
		    Timeline timeline = new Timeline(keyFrame);

		
	    if (newValue != null) {
	            
	        c.showDetails(newValue);
		    scaleTransition.setToX(-100);
        	
            c.setPrefWidth(220); 
	        listUserPan.setRight(c); // Mettre à jour le contenu du setRight
	    }

	});
	
	
	this.getChildren().addAll(table);
}
	
	




	public TableView<Stagiaire> getListS() {
	        return table;
	    }
}
