package views;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.control.Button;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.layout.VBox;
import model.ArbreBinaire;
import model.Stagiaire;

public class FormulaireAjout extends VBox {
	private Stagiaire maPromo;
	private TableView<Stagiaire> maTable;

	public FormulaireAjout(Stagiaire stagiaire) {
		super();

		this.maPromo = maPromo;
		this.maTable = new TableView<Stagiaire>();
		maTable.setEditable(true);



		//j'ai fini de créer ma table

				VBox formulaireAjout = new VBox();
				TextField nomTf = new TextField("nom");
				TextField prenomTf = new TextField("prenom");
				TextField departementTf = new TextField("département");
				TextField promotionTf = new TextField("promotion");
				TextField anneeTf = new TextField("annee");
				

				Button ajouterStagiaire = new Button("Ajouter");
				formulaireAjout.getChildren().addAll(nomTf, prenomTf, departementTf,promotionTf,anneeTf,ajouterStagiaire );
				
				this.getChildren().add(formulaireAjout);
				ajouterStagiaire.setPrefWidth(260);
				ajouterStagiaire.setOnAction(new EventHandler<ActionEvent>() {

					public void handle(ActionEvent event) {
						ArbreBinaire ajout = new ArbreBinaire();
						Stagiaire nouveauStagiaire = new Stagiaire(nomTf.getText(), prenomTf.getText(),
								departementTf.getText(),promotionTf.getText(),Integer.valueOf(anneeTf.getText()));
							System.out.println();
						try {
							ajout.ajouterStagiaireDeFichierDonAFichierBin(nouveauStagiaire);
						} catch (IOException e) {
							
							e.printStackTrace();
						}
						maTable.getItems().add(nouveauStagiaire);
					}
				});

	}



}
