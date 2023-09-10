package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import model.Stagiaire;

// Le premier panneau est la VBox
public class BoutonSupprimer extends Button {
	// il possède en attribut un bouton
	private Button boutonSupprimer = new Button("Supprimer");

	// dans son constructeur , on stylise le panneau, on lui ajoute le bouton en
	// tant que Node enfant
	public BoutonSupprimer() {
		super();
		
		 // Créer un cercle plus grand pour le bouton
        Circle circle = new Circle(100, 100, 50);
        circle.setFill(Color.LIGHTGRAY);

        // Créer une ligne horizontale (croix)
        Line line1 = new Line(90, 100, 150, 100);
        line1.setStrokeWidth(5);
        line1.setStroke(Color.DARKGRAY);
        line1.setRotate(90); // Faire pivoter de 90 degrés

        // Créer une ligne verticale (croix)
        Line line2 = new Line(100, 90, 100, 150);
        line2.setStrokeWidth(5);
        line2.setStroke(Color.DARKGRAY);
        line2.setRotate(90); // Faire pivoter de 90 degrés

        // Superposer le cercle et les lignes avec StackPane
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(circle, line1, line2);

        // Créer le bouton en utilisant le StackPane comme contenu
        Button roundButton = new Button();
        
        this.setStyle(
                "-fx-background-radius: 5em; " +
                "-fx-min-width: 30px; " +
                "-fx-min-height: 30px; " +
                "-fx-max-width: 30px; " +
                "-fx-max-height: 30px;"
        );
        
        this.setGraphic(stackPane);


        this.getChildren().addAll(stackPane);
		// action lorsqu'on click sur le bouton
		

	}

}