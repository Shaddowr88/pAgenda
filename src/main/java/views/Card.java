package views;

import javafx.animation.ScaleTransition;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
import javafx.util.Duration;

public class Card extends VBox {
	
	String Title;
	
	private Label nom;
	private Label premon;
	private Label annee;
	private Label depart;
	private Label promo;

	
	
	
	
	
	public Card () {
		
		
		
		
		//this.Title = t;
		
		this.nom = new Label("");
		this.premon = new Label("");
		this.annee = new Label("");
		this.depart = new Label("");
		this.promo = new Label("");
		

		
		this.getChildren().addAll(this.nom, this.premon,this.annee,this.depart, this.promo);
		
		
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
