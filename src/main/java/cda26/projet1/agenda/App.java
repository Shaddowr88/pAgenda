package cda26.projet1.agenda;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.HomePan;
import views.ListUserPan;


/**
 * JavaFX App
 */


public class App extends Application {
	
	//public static boolean isAdmin=false;

    @Override
    public void start(Stage stage)throws Exception  {
    	
    	ListUserPan root = new ListUserPan();
        Scene scene = new Scene(root, 800, 600);
        stage.setResizable(false) ;
        stage.setTitle("Annuaire ISIKA");
        stage.setScene(scene);
        stage.show();
    }
    
    public void openMainWindow(Stage primaryStage) {
        // Implémentez la logique de la fenêtre principale ici
    }
    
    public static void main(String[] args) {
    	
        launch();
    }

}