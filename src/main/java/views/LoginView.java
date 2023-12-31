
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
import javafx.scene.layout.VBox;



public class LoginView extends VBox {
	
	// il possède en attribut :loginTxF,adminUsername,adminPassword ,isAdmin
	private TextField loginTxF;
	private final String[] ADMIN_USER = { "Loic", "Vincent", "Floriane", "Gabriel" };
	private final String ADMIN_PASSWORD = "admin";
	public static boolean isAdmin;

	// Dans le constructeur je crée un panneau, Trois HBox que je range dans une
	// VBox et les stylise respectivement
	public LoginView(ListUserPan listUserPan ) {
		super();
		
		VBox vboxLogin = new VBox() ;
		// HBox login
		Label loginLb1 = new Label("Login");
		TextField loginTxF1 = clean (new TextField("Prenom"));
		HBox loginHBox = new HBox(5);
		loginHBox.setPrefSize(220, 30);
		loginHBox.setAlignment(Pos.CENTER_RIGHT);
		loginHBox.getChildren().addAll(loginLb1, loginTxF1);

		// HBox password

		Label passwordLb1 = new Label("Mot de passe");
		PasswordField passwordPWF = new PasswordField();
		HBox passwordHBox = new HBox(5);
		passwordHBox.setPrefSize(220, 30);
		passwordHBox.setAlignment(Pos.CENTER_RIGHT);
		passwordHBox.getChildren().addAll(passwordLb1, passwordPWF);

		// HBox valider

		Button validerBtn = new Button("Valider");
		HBox validerHBox = new HBox(validerBtn);
		validerHBox.setPrefSize(100, 30);
		validerHBox.setAlignment(Pos.CENTER_RIGHT);
		
		vboxLogin.getChildren().addAll(loginHBox,passwordHBox,validerHBox);
		vboxLogin.setAlignment(Pos.CENTER);
		vboxLogin.setPrefSize(100, 100);
		vboxLogin.setPadding(new Insets(0, 0, 0, 0));
		
		
		//Submit Action
		validerBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				for (int i = 0; i < ADMIN_USER.length; i++) {
					if (loginTxF1.getText().equals(ADMIN_USER[i]) && passwordPWF.getText().equals("admin")) {					
						ListUserPan.isAdmin=true;
						listUserPan.setRight(null);
					} 

				}
				
			}
		});

		// VBox root

		this.setSpacing(5);
		this.setPadding(new Insets(5, 5, 5, 5));
		this.setPrefSize(260, 240);
		this.setAlignment(Pos.CENTER);
		this.setStyle("-fx-background-color: #f1d278");
		this.getChildren().addAll(vboxLogin);
	}
	
private TextField clean (TextField field) {
		
		field.setOnMouseClicked(event -> {
			if (event.getClickCount() == 1) {
				field.clear();
			}
		});
		return field;
	}

}