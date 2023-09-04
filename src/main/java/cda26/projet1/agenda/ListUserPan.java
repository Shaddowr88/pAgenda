package cda26.projet1.agenda;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class ListUserPan extends HBox {
	
	private Label label = new Label(" liste d'utilisateur");

	public ListUserPan() {
		super();
		setPrefSize(400, 200);
		setAlignment(Pos.CENTER);
		setStyle("-fx-background-color:teal");
		label.setTextFill(Color.WHITE);
		getChildren().add(label);
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}
	
	
}
