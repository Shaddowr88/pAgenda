package views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.ArbreBinaire;
import model.NoeudBinaire;
import model.Stagiaire;

public class ListS extends Pane {
	
	ArrayList<String> finds;
	List<NoeudBinaire> arrayList ;
	
	private Label label = new Label(" liste d'utilisateur");
	private int sizeParam = 5;
	public ArrayList<Stagiaire> lUser;
	ArbreBinaire arbreAnnuaire = new ArbreBinaire();
	
	
	public ListS() throws IOException {
	
	ArbreBinaire arbreAnnuaire = new ArbreBinaire() ;
	
	
	arbreAnnuaire.lectureFichierDon(arbreAnnuaire);

	ArrayList<Stagiaire> listeStagiaires = new ArrayList<Stagiaire>();
	NoeudBinaire noeudVersArrayList = new NoeudBinaire();
	arbreAnnuaire.getRaf().seek(0);
	noeudVersArrayList = noeudVersArrayList.lireNoeudFichierBinVersObjetNoeudBinaire(arbreAnnuaire.getRaf());
	listeStagiaires = noeudVersArrayList.fichierBinVersArrayList(arbreAnnuaire.getRaf());
	
	ObservableList<Stagiaire> obsListeStagiaires= FXCollections.observableArrayList(listeStagiaires);
	
	lUser =listeStagiaires;
	
	obsListeStagiaires.setAll(lUser);
	
    TableView<Stagiaire> table = new TableView<Stagiaire>();

    TableColumn<Stagiaire, String> userlastNameCol //
    		= new TableColumn<Stagiaire, String>("Nom");
    		userlastNameCol.prefWidthProperty().bind(table.widthProperty().divide(sizeParam ));
    		userlastNameCol.setCellValueFactory(new PropertyValueFactory<Stagiaire,String>("nom"));

    TableColumn<Stagiaire, String> userNameCol //
    		= new TableColumn<Stagiaire, String>("Prénom");
    		userNameCol.prefWidthProperty().bind(table.widthProperty().divide(sizeParam ));
    		userNameCol.setCellValueFactory(new PropertyValueFactory<Stagiaire,String>("prenom"));
    
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
            		//+ "-fx-background-color: white;"
            		+ "-fx-font-size:1em;"
            		);
            
    table.getColumns().addAll(userlastNameCol,userNameCol, DepCol, promoNameCol,YearCol); 
	table.setItems(obsListeStagiaires);
 	

}
}
