package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import model.Stagiaire;

public class Delete extends FlowPane {
	private Stagiaire maPromo;
	private TableView<Stagiaire> maTable;

	public Delete(Card Card,Stagiaire item) {
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

					public void handle(ActionEvent event) {
						Stagiaire stagiaire = new Stagiaire(nomTf.getText(), prenomTf.getText(), departementTf.getText(),
								promotionTf.getText(), Integer.valueOf(anneeTf.getText()));
						Card.getChildren().addAll(deleteYes);
					System.out.println("j'efface"+stagiaire);

					}
				});
				
	
	        	 
	        	 deleteNo.setOnMouseClicked(event -> {
		 	            if (event.getClickCount() == 1) {
		 	            	this.getChildren().clear();
		 	            	this.getChildren().addAll();
		 	            	
		 	            }
		 	        });

	}



}
