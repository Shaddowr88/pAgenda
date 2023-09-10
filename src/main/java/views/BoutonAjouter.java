package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import model.Stagiaire;

/**
 * 
 * @author Bitjoka Vincent
 *
 */

//Le premier panneau est la VBox
public class BoutonAjouter extends VBox {
	
	// il possède en attribut un boutonAjouter
		private Button boutonAjouter = new Button("Ajouter");
		
		
		/*
		 * dans son constructeur  
		 */
		public BoutonAjouter() {
			super();
			//action lorsqu'on click sur le bouton Ajouter
			boutonAjouter.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					
					// selection d'un stagiaire
					//Stagiaire stagiaire = ((Stagiaire) BoutonAjouter.this
						//.getTableView().getStagiaire().get(BoutonAjouter.this.getId()));
					
					//supprimer le stagiaire selectionné 
				//	Stagiaire.add(stagiaire);
				}
			});
			
		}


		protected Object getTableView() {
			// TODO Auto-generated method stub
			return null;
		}

}