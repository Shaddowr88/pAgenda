package views;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.control.Button;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.layout.VBox;
import model.ArbreBinaire;
import model.NoeudBinaire;
import model.Stagiaire;

public class FormulaireAjout extends VBox {
	private Stagiaire maPromo;
	private TableView<Stagiaire> maTable;

	public FormulaireAjout(ListUserPan root) {
		super();

		this.maPromo = maPromo;
		this.maTable = new TableView<Stagiaire>();
		maTable.setEditable(true);

		//j'ai fini de créer ma table

				VBox formulaireAjout = new VBox();
				TextField nomTf = clean (new TextField("nom"));
				TextField prenomTf = clean (new TextField("prenom"));
				TextField departementTf = clean (new TextField("département"));
				TextField promotionTf =clean ( new TextField("promotion"));
				TextField anneeTf =clean ( new TextField("annee"));
				this.setStyle("-fx-background-color: #f1d278");
				
				nomTf.setOnMouseClicked(event -> {
					if (event.getClickCount() == 1) {
						nomTf .clear();
					}
				});

				Button ajouterStagiaire = new Button("Ajouter");
				formulaireAjout.getChildren().addAll(nomTf, prenomTf, departementTf,promotionTf,anneeTf,ajouterStagiaire );
				
				this.getChildren().add(formulaireAjout);
				ajouterStagiaire.setPrefWidth(260);
				ajouterStagiaire.setOnAction(new EventHandler<ActionEvent>() {

					public void handle(ActionEvent event) {
						ArbreBinaire ajout = new ArbreBinaire();
						Stagiaire nouveauStagiaire = new Stagiaire(nomTf.getText(), prenomTf.getText(),
								departementTf.getText(),promotionTf.getText(),Integer.valueOf(anneeTf.getText()));
					
						try {
							ajout.ajouterStagiaireDeFichierDonAFichierBin(nouveauStagiaire);
							NoeudBinaire noeudVersArrayList = new NoeudBinaire();
							ListUserPan.arbreAnnuaire.getRaf().seek(0);
							noeudVersArrayList = noeudVersArrayList.lireNoeudFichierBinVersObjetNoeudBinaire(ListUserPan.arbreAnnuaire.getRaf());
							ListUserPan.arbreAnnuaire.getRaf().seek(0);
							ArrayList<Stagiaire> listeStagiaires = noeudVersArrayList.fichierBinVersArrayList(ListUserPan.arbreAnnuaire.getRaf());
							ListS listS2 = new ListS(root, listeStagiaires);
							root.setCenter(listS2);
							
						} catch (IOException e) {
							
							e.printStackTrace();
						}
						maTable.getItems().add(nouveauStagiaire);
					}
				});

	}
	
	
	private TextField clean (TextField field) {
		
		field.setOnMouseClicked(event -> {
			if (event.getClickCount() == 1) {
				field.clear();
			}
		});
		return field;
	}



}
