package views;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Stagiaire;

public class FormulaireAjout extends VBox {
	private Stagiaire maPromo;
	private TableView<Stagiaire> maTable;

	public FormulaireAjout(Stagiaire stagiaire) {
		super();

		this.maPromo = maPromo;
		this.maTable = new TableView<Stagiaire>();
		maTable.setEditable(true);

		/*
		 * création de la colonne Nom
		 */

		// je crée la colonne
		TableColumn<Stagiaire, String> colonneNom = new TableColumn<Stagiaire, String>();
		// je donne une lageur à ma colonne
		colonneNom.setMinWidth(100);
		// je précise comment remplir ma colonnne
		colonneNom.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("nom"));
		// j'ajoute la colonne au TableView
		maTable.getColumns().add(colonneNom);

		// je crée la colonne prenom
		TableColumn<Stagiaire, String> colonnePrenom = new TableColumn<Stagiaire, String>();
		// je donne une lageur à ma colonne
		colonnePrenom.setMinWidth(100);
		// je précise comment remplir ma colonnne
		colonnePrenom.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("prenom"));
		// j'ajoute la colonne au TableView
		maTable.getColumns().add(colonnePrenom);

		// je crée la colonne departement
		TableColumn<Stagiaire, String> colonneDepartement = new TableColumn<Stagiaire, String>();
		// je donne une lageur à ma colonne
		colonnePrenom.setMinWidth(10);
		// je précise comment remplir ma colonnne
		colonneDepartement.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("Département"));
		// j'ajoute la colonne au TableView
		maTable.getColumns().add(colonneDepartement);

		// je crée la colonne promotion
		TableColumn<Stagiaire, String> colonnePromotion = new TableColumn<Stagiaire, String>();
		// je donne une lageur à ma colonne
		colonnePrenom.setMinWidth(100);
		// je précise comment remplir ma colonnne
		colonnePromotion.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("promotion"));
		// j'ajoute la colonne au TableView
		maTable.getColumns().add(colonnePromotion);

		// je crée la colonne année
		TableColumn<Stagiaire, Integer> colonneAnnee = new TableColumn<Stagiaire, Integer>();
		// je donne une lageur à ma colonne
		colonneAnnee.setMinWidth(10);
		// je précise comment remplir ma colonnne
		colonneAnnee.setCellValueFactory(new PropertyValueFactory<Stagiaire, Integer>("Département"));
		// j'ajoute la colonne au TableView
		maTable.getColumns().add(colonneAnnee);

		/* Variable Datas a remplacer */
		
		// maTable.setItems(FXCollections.observableList(maPromo.datas) );
		this.getChildren().add(maTable);


		//j'ai fini de créer ma table

				HBox formulaireAjout = new HBox();
				TextField nomTf = new TextField("nom");
				TextField prenomTf = new TextField("prenom");
				TextField departementTf = new TextField("département");
				TextField promotionTf = new TextField("promotion");
				TextField anneeTf = new TextField("annee");


				formulaireAjout.getChildren().addAll(nomTf, prenomTf, departementTf,promotionTf,anneeTf );

				Button ajouterStagiaire = new Button("Ajouter");
				this.getChildren().add(ajouterStagiaire);
				ajouterStagiaire.setPrefWidth(260);
				ajouterStagiaire.setOnAction(new EventHandler<ActionEvent>() {

					public void handle(ActionEvent event) {
						Stagiaire nouveauStagiaire = new Stagiaire(nomTf.getText(), prenomTf.getText(),
								departementTf.getText(),promotionTf.getText(),Integer.valueOf(anneeTf.getText()));


						maTable.getItems().add(nouveauStagiaire);
					}
				});

	}



}
