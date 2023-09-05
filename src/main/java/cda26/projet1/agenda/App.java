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

    @Override
    public void start(Stage stage)throws Exception  {
    	
    	ListUserPan root = new ListUserPan();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Caller");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
    	
        launch();
    }

}