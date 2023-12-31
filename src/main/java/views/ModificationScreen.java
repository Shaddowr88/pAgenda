package views;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.FlowPane;
import model.ArbreBinaire;
import model.NoeudBinaire;
import model.Stagiaire;

public class ModificationScreen extends FlowPane {
	private Stagiaire maPromo;
	private TableView<Stagiaire> maTable;


	public ModificationScreen(Card Card,Stagiaire item, ListUserPan root) {
		super();

		this.maPromo = maPromo;
		this.maTable = new TableView<Stagiaire>();
		maTable.setEditable(true);
		this.setStyle("-fx-background-color: #f1d278");

		//j'ai fini de créer ma table

				FlowPane formulaireAjout = new FlowPane();
				TextField nomTf = new TextField(item.getNom());
				TextField prenomTf = new TextField(item.getPrenom());
				TextField departementTf = new TextField(item.getDepartement());
				TextField promotionTf = new TextField(item.getPromotion());
				TextField anneeTf = new TextField(Integer.toString(item.getAnnee()));
				Button mofierInfoStagiaire = new Button("Modifier");
				
				this.getChildren().addAll(nomTf, prenomTf, departementTf,promotionTf,anneeTf,mofierInfoStagiaire);
				
				nomTf.setPrefWidth(260);
				prenomTf.setPrefWidth(260);
				departementTf.setPrefWidth(260);
				promotionTf.setPrefWidth(260);
				anneeTf.setPrefWidth(260);
				mofierInfoStagiaire.setPrefWidth(260);

				/*
				 *  je crée un évènement afin de changer les infos du stagiaire avec une méthode handle
				 */
				
				 TextFormatter<String> numberFormat = new TextFormatter<>(change -> {
			            String newText = change.getControlNewText();
			            if (newText.matches("\\d*")) { // Seuls les chiffres sont autorisés
			                return change;
			            }
			            return null; // Bloque le changement s'il contient des caractères non numériques
			        });
				      
				 /*
				  *   Evènement  texte formatter Uppercase
				  */
				 
				 nomTf.textProperty().addListener((observable, oldValue, newValue) -> {
			            if (newValue != null) {
			            	nomTf.setText(newValue.toUpperCase());
			            }
			        });
				 
				 /*
				  *   Evènement  texte formatter integer
				  */
				
				 anneeTf.setTextFormatter(numberFormat);
						
						anneeTf.textProperty().addListener((observable, oldValue, newValue) -> {
						    if (!newValue.matches("\\d*")) {
						        
						    	anneeTf.setText(newValue.replaceAll("[^\\d]", ""));
						    }
						});
						
			// Submit Action
				mofierInfoStagiaire.setOnAction(new EventHandler<ActionEvent>() {

					public void handle(ActionEvent event) {
						Stagiaire nouveauStagiaire = new Stagiaire(nomTf.getText(), prenomTf.getText(), departementTf.getText(),
								promotionTf.getText(), Integer.valueOf(anneeTf.getText()));

					int validcount=0;
					
						if (nomTf.getText()==null) { nomTf.setText(" veuiller renseigner le Nom"); validcount++;}  
						if (prenomTf.getText()==null) { prenomTf.setText(" veuiller renseigner le Prénom"); validcount++;}
						if (departementTf.getText()==null) { departementTf.setText(" veuiller renseigner le Departement"); validcount++;}
						if (promotionTf.getText()==null) { promotionTf.setText(" veuiller renseigner la Promotion"); validcount++;}
						if (anneeTf.getText()==null) { anneeTf.setText(" veuiller renseigner le Année"); validcount++;}
					
					if (!(validcount>0)) {
					
					try {

						if(!item.getPrenom().equals(prenomTf.getText().toUpperCase())) {
							
							ListUserPan.arbreAnnuaire.modifierPrenom(item, prenomTf.getText(),  ListUserPan.arbreAnnuaire.getRaf());
						}
						if(!item.getDepartement().equals(departementTf.getText())) {
							
							ListUserPan.arbreAnnuaire.modifierDepartement(item, departementTf.getText() ,  ListUserPan.arbreAnnuaire.getRaf());
						}
						if(!item.getPromotion().equals(promotionTf.getText())) {
							
							ListUserPan.arbreAnnuaire.modifierPromotion(item, promotionTf.getText(), ListUserPan.arbreAnnuaire.getRaf());
						}
						if(item.getAnnee()== Integer.valueOf(anneeTf.getText())) {
							
							ListUserPan.arbreAnnuaire.modifierAnnee(item,Integer.valueOf(anneeTf.getText()),ListUserPan.arbreAnnuaire.getRaf());
						}
						if(!item.getNom().equals(nomTf.getText())) {
							
							ListUserPan.arbreAnnuaire.modifierNom(item, nomTf.getText(),  ListUserPan.arbreAnnuaire.getRaf());
						}
						
						NoeudBinaire noeudVersArrayList = new NoeudBinaire();
						ListUserPan.arbreAnnuaire.getRaf().seek(0);
						noeudVersArrayList = noeudVersArrayList.lireNoeudFichierBinVersObjetNoeudBinaire(ListUserPan.arbreAnnuaire.getRaf());
						ListUserPan.arbreAnnuaire.getRaf().seek(0);
						ArrayList<Stagiaire> listeStagiaires = noeudVersArrayList.fichierBinVersArrayList(ListUserPan.arbreAnnuaire.getRaf());				
						ListS listS2 = new ListS(root, listeStagiaires);
						root.setCenter(listS2);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
					}
				});
				
				

	}



}
