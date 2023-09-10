package views;

import java.util.List;

import cda26.projet1.agenda.App;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.util.Duration;
import model.Personnel;

public class Card extends VBox {
	
	String Title;	
	
	private Label nom;
	private Label premon;
	private Label annee;
	private Label depart;
	private Label promo;
	

	public Card () {
		
		Boolean Log = LoginView.isAdmin;
		BoutonSupprimer btns=new BoutonSupprimer();

		this.nom = new Label("");
		this.premon = new Label("");
		this.annee = new Label("");
		this.depart = new Label("");
		this.promo = new Label("");
		
		btns.getTransforms().add(new Rotate(45, 50, 45));

		this.getChildren().addAll(this.nom, this.premon,this.annee,this.depart, this.promo,btns);
		
		 System.out.println(Log);
		this.setStyle("-fx-background-color:cyan");
		btns.setOnAction(new EventHandler<ActionEvent>() {
		
			@Override
            public void handle(ActionEvent event) {
                // Réponse lorsque le bouton est cliqué
                System.out.println("ajouter");
            }
		});

	}



	public String getTitle() {
		return Title;
	}



	public void setTitle(String title) {
		this.Title = title;
	}
	
	
	 public void afficher(String nom, String prenom, String année, String dep, int i) {
		
		 this.nom.setText(nom); 
		 this.premon.setText(prenom); 
		 this.annee.setText(année); 
		 this.depart.setText(dep); 
		 this.promo.setText(Integer.toString(i));
		 
		 
		 
	 }








	
	

}
