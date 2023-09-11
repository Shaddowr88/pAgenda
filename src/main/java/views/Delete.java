package views;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import model.ArbreBinaire;
import model.NoeudBinaire;
import model.Stagiaire;

public class Delete extends FlowPane {
	private Stagiaire maPromo;
	private TableView<Stagiaire> maTable;

	public Delete(Card Card,Stagiaire item, ListUserPan root) {
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

				Button deleteYes = new Button("Oui");
		        Button deleteNo = new Button("Non");
				
				
				
				this.getChildren().addAll(nomTf, prenomTf, departementTf,promotionTf,anneeTf);
				
				deleteYes.setPrefWidth(260);
				deleteNo.setPrefWidth(260);

				/*
				 *  je crée un évènement afin de changer les infos du stagiaire avec une méthode handle
				 */
				
				deleteYes.setOnAction(new EventHandler<ActionEvent>() {
				
					ArbreBinaire arbreSuppression = new ArbreBinaire() ;
					
			//Handle Submit
					
					public void handle(ActionEvent event) {
						Stagiaire stagiaire = new Stagiaire(nomTf.getText(), prenomTf.getText(), departementTf.getText(),
								promotionTf.getText(), Integer.valueOf(anneeTf.getText()));
						System.out.println("j'efface"+stagiaire);
						
						NoeudBinaire noeudVersArrayList = new NoeudBinaire();
						try {
							arbreSuppression.supprimerStagiaire(stagiaire, ListUserPan.arbreAnnuaire.getRaf());
							ListUserPan.arbreAnnuaire.getRaf().seek(0);
						
							noeudVersArrayList = noeudVersArrayList.lireNoeudFichierBinVersObjetNoeudBinaire(ListUserPan.arbreAnnuaire.getRaf());
							ListUserPan.arbreAnnuaire.getRaf().seek(0);
							ArrayList<Stagiaire> listeStagiaires = noeudVersArrayList.fichierBinVersArrayList(ListUserPan.arbreAnnuaire.getRaf());
							//ObservableList<Stagiaire> obsListeStagiaires= FXCollections.observableArrayList(listeStagiaires);
							ListS listS2 = new ListS(root, listeStagiaires);
							root.setCenter(listS2);
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				});
				
			//Handle Not Submit	
	        	 deleteNo.setOnMouseClicked(event -> {
		 	            if (event.getClickCount() == 1) {
		 	            	this.getChildren().clear();
		 	            	this.getChildren().addAll();
		 	            	
		 	            }
		 	        });

	}



}
