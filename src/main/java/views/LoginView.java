package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class LoginView extends VBox {

	private TextField loginTxF;
	private String[] adminUsername = { "Loic", "Vincent", "Floriane", "Gabriel" };
	private String adminPassword = "admin";
	public Boolean isAdmin;

	public LoginView() {
		super();

		// Top pane
		Pane topPannel = new Pane();
		topPannel.setPrefSize(800, 200);
		topPannel.setStyle("-fx-background-color: yellow");
		Text titrePannel = new Text("ANNUAIRE STAGIAIRES");
		titrePannel.setFont(Font.font(null, FontWeight.BOLD, 40));
		titrePannel.setFill(Color.BLUE);
		titrePannel.setX(100);
		titrePannel.setY(110);
		topPannel.getChildren().addAll( titrePannel);
		
		

		// HBox login
		Label loginLb1 = new Label("Login");
		TextField loginTxF1 = new TextField("Prenom");
		HBox loginHBox = new HBox(5);
		loginHBox.setPrefSize(250, 30);
		loginHBox.setAlignment(Pos.CENTER_RIGHT);
		loginLb1.setStyle("-fx-background-color: red");
		loginHBox.getChildren().addAll(loginLb1, loginTxF1);

		// HBox password

		Label passwordLb1 = new Label("password");
		PasswordField passwordPWF = new PasswordField();
		HBox passwordHBox = new HBox(5);
		passwordHBox.setPrefSize(250, 30);
		passwordHBox.setAlignment(Pos.CENTER_RIGHT);
		passwordLb1.setStyle("-fx-background-color: red");
		passwordHBox.getChildren().addAll(passwordLb1, passwordPWF);

		// HBox valider

		Button validerBtn = new Button("valider");
		HBox validerHBox = new HBox(validerBtn);
		validerHBox.setPrefSize(220, 30);
		validerHBox.setAlignment(Pos.CENTER_RIGHT);
		validerBtn.setStyle("-fx-background-color: violet");

		// VBox root

		this.setSpacing(5);
		this.setPadding(new Insets(5, 5, 5, 5));
		this.setPrefSize(220, 240);
		this.setAlignment(Pos.CENTER);
		this.setStyle("-fx-background-color: teal");

		this.getChildren().addAll(topPannel, loginHBox, passwordHBox, validerHBox);

		/********************************/

		validerBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				isAdmin = false;
				for (int i = 0; i < adminUsername.length; i++) {
					if (loginTxF1.getText().equals(adminUsername[i]) && passwordPWF.getText().equals("admin")) {
						isAdmin = true;

					} else {

					}

				}
				System.out.println(isAdmin);
			}
		});

	}

}