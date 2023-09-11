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

/*
 * Le premier panneau est la VBox
 */
public class FormulaireDeModification extends VBox {
	/*
	 * il possède en attribut:Stagiaire et TableView<Stagiaire>
	 */
	private Stagiaire maPromo;
	private TableView<Stagiaire> maTable;

	/*
	 * le constructeur
	 */
	public FormulaireDeModification(Stagiaire stagiaire) {
		super();

		this.maPromo = maPromo;
		this.maTable = new TableView<Stagiaire>();
		maTable.setEditable(true);

		/*
		 * Je crée ma table
		 */
	/******************************************************************************/
	
		/*
		 * je crée la colonne
		 */
		
		TableColumn<Stagiaire, String> colonneNom = new TableColumn<Stagiaire, String>();
		
		/*
		 *  je donne une lageur à ma colonne
		 */
		colonneNom.setMinWidth(100);
		
		/*
		 *  je précise comment remplir ma colonnne
		 */
		colonneNom.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("nom"));
		
		/*
		 *  j'ajoute la colonne au TableView
		 */
		maTable.getColumns().add(colonneNom);

		/*
		 *  je crée la colonne prenom
		 */
		TableColumn<Stagiaire, String> colonnePrenom = new TableColumn<Stagiaire, String>();
		
		/*
		 *  je donne une lageur à ma colonne
		 */
		colonnePrenom.setMinWidth(100);
		
		/*
		 *  je précise comment remplir ma colonnne
		 */
		colonnePrenom.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("prenom"));
		
		/*
		 *  j'ajoute la colonne au TableView
		 */
		maTable.getColumns().add(colonnePrenom);

		/*
		 *  je crée la colonne departement
		 */
		TableColumn<Stagiaire, String> colonneDepartement = new TableColumn<Stagiaire, String>();
		
		/*
		 *  je donne une lageur à ma colonne
		 */
		colonnePrenom.setMinWidth(10);
		
		/*
		 *  je précise comment remplir ma colonnne
		 */
		colonneDepartement.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("Département"));
		
		/*
		 *  j'ajoute la colonne au TableView
		 */
		maTable.getColumns().add(colonneDepartement);

		/*
		 *  je crée la colonne promotion
		 */
		TableColumn<Stagiaire, String> colonnePromotion = new TableColumn<Stagiaire, String>();
		
		/*
		 *  je donne une lageur à ma colonne
		 */
		colonnePrenom.setMinWidth(100);
		
		/*
		 *  je précise comment remplir ma colonnne
		 */
		colonnePromotion.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("promotion"));
		
		/*
		 *  j'ajoute la colonne au TableView
		 */
		maTable.getColumns().add(colonnePromotion);

		/*
		 *  je crée la colonne année
		 */
		TableColumn<Stagiaire, Integer> colonneAnnee = new TableColumn<Stagiaire, Integer>();
		
		/*
		 *  je donne une lageur à ma colonne
		 */
		colonneAnnee.setMinWidth(10);
		
		/*
		 *  je précise comment remplir ma colonnne
		 */
		colonneAnnee.setCellValueFactory(new PropertyValueFactory<Stagiaire, Integer>("Département"));
		/*
		 *  j'ajoute la colonne au TableView
		 */
		maTable.getColumns().add(colonneAnnee);

		maTable.setItems(FXCollections.observableList(null));
		this.getChildren().add(maTable);
 
		/***************************************************************************************************/
		/*
		 *  j'ai fini de créer ma table
		 */
		
		
		/*
		 * je crée une HBox pour ranger mes TextField
		 */
		HBox formulaireDeModification = new HBox();
		TextField nomTf = new TextField("nom");
		TextField prenomTf = new TextField("prenom");
		TextField departementTf = new TextField("département");
		TextField promotionTf = new TextField("promotion");
		TextField anneeTf = new TextField("annee");

		formulaireDeModification.getChildren().addAll(nomTf, prenomTf, departementTf, promotionTf, anneeTf);

		/*
		 *  Je crée un bouton Modifier et le stylise
		 */

		Button mofierInfoStagiaire = new Button("Modifier");
		this.getChildren().add(mofierInfoStagiaire);
		mofierInfoStagiaire.setPrefWidth(260);

		/*
		 *  je crée un évènement afin de changer les infos du stagiaire avec une méthode handle
		 */
		
		mofierInfoStagiaire.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				Stagiaire nouveauStagiaire = new Stagiaire(nomTf.getText(), prenomTf.getText(), departementTf.getText(),
						promotionTf.getText(), Integer.valueOf(anneeTf.getText()));

				((Stagiaire) maTable.getItems()).setNom("");
				((Stagiaire) maTable.getItems()).setPrenom("");
				((Stagiaire) maTable.getItems()).setDepartement("");
				((Stagiaire) maTable.getItems()).setPromotion("");
				((Stagiaire) maTable.getItems()).setAnnee(0);

			}
		});

	}

}