package views;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import model.ArbreBinaire;
import model.Stagiaire;

public class ModificationScreen extends FlowPane {
	private Stagiaire maPromo;
	private TableView<Stagiaire> maTable;

	public ModificationScreen(Card Card,Stagiaire item) {
		super();

		this.maPromo = maPromo;
		this.maTable = new TableView<Stagiaire>();
		maTable.setEditable(true);



		//j'ai fini de créer ma table

				FlowPane formulaireAjout = new FlowPane();
				TextField nomTf = new TextField(item.getNom());
				TextField prenomTf = new TextField(item.getPrenom());
				TextField departementTf = new TextField(item.getDepartement());
				TextField promotionTf = new TextField(item.getPromotion());
				TextField anneeTf = new TextField(Integer.toString(item.getAnnee()));


				
				Button mofierInfoStagiaire = new Button("Modifier");
				
				this.getChildren().addAll(nomTf, prenomTf, departementTf,promotionTf,anneeTf,mofierInfoStagiaire);
				
				mofierInfoStagiaire.setPrefWidth(260);

				/*
				 *  je crée un évènement afin de changer les infos du stagiaire avec une méthode handle
				 */
				      
						
						anneeTf.textProperty().addListener((observable, oldValue, newValue) -> {
						    if (!newValue.matches("\\d*")) {
						        
						    	anneeTf.setText(newValue.replaceAll("[^\\d]", ""));
						    }
						});
						
						
						
				mofierInfoStagiaire.setOnAction(new EventHandler<ActionEvent>() {

					public void handle(ActionEvent event) {
						Stagiaire nouveauStagiaire = new Stagiaire(nomTf.getText(), prenomTf.getText(), departementTf.getText(),
								promotionTf.getText(), Integer.valueOf(anneeTf.getText()));
						Card.getChildren().addAll(mofierInfoStagiaire);
					System.out.println(nouveauStagiaire);
					
					ArbreBinaire modif = new ArbreBinaire();
					
					int validcount=0;
					
						if (nomTf.getText()==null) { nomTf.setText(" veuiller renseigner le Nom"); validcount++;}  
						if (prenomTf.getText()==null) { prenomTf.setText(" veuiller renseigner le Prénom"); validcount++;}
						if (departementTf.getText()==null) { departementTf.setText(" veuiller renseigner le Departement"); validcount++;}
						if (promotionTf.getText()==null) { promotionTf.setText(" veuiller renseigner la Promotion"); validcount++;}
						if (anneeTf.getText()==null) { anneeTf.setText(" veuiller renseigner le Année"); validcount++;}
					
					System.out.println(validcount);
					 
					try {
						
						modif.modifierNom(item, nomTf.getText(),  modif.getRaf());
						modif.modifierPrenom(item, prenomTf.getText(),  modif.getRaf());
						modif.modifierDepartement(item, departementTf.getText() ,  modif.getRaf());
						modif.modifierPromotion(item, promotionTf.getText(),  modif.getRaf());
						modif.modifierAnnee(item,Integer.valueOf(anneeTf.getText()),modif.getRaf());

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					

					}
				});

	}



}
