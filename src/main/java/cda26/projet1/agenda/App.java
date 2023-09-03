package cda26.projet1.agenda;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage)throws Exception  {

/*******************************   Datas   **************************************************/    	
   
    	 ObservableList<Stagiaire> datas= FXCollections.observableArrayList();  
         
         datas.add(new Stagiaire("pipo","alo", 78, "ET 34847", 2001));
         datas.add(new Stagiaire("Tiago","delo", 972, "ET 34847", 1998));
         datas.add(new Stagiaire("finalo","Zellot", 60, "ET 34847", 1998));
         datas.add(new Stagiaire("groco","ralto", 78, "ET 34847", 2001));
         datas.add(new Stagiaire("Glyphto","malhermao", 78, "ET 34847", 2003));
         datas.add(new Stagiaire("minetto","walpo", 93, "ET 34847", 2003));
         datas.add(new Stagiaire("Fredo","Mongolo", 75, "ET 34847", 1984));
         datas.add(new Stagiaire("tyipo","Dingo", 93, "ERE 234", 2001));
         
/****************************************************************************************/              	
 
        BorderPane root = new BorderPane();
        
    	Label lblBottom = new Label(" ");
    	Label lblRight = new Label (" ");
    	Label lblCenter = new Label(" ");
        
    	Pane topPannel = new Pane();
        topPannel.setPrefSize(800, 100);
       
        Pane leftPannel = new Pane();
        leftPannel.setPrefSize(100, 700);
        
 /******************************** Subs ********************************************************/    
        
        // Substitue logo Isika
        
        Rectangle rectangle = new Rectangle(90, 30);
        rectangle.setFill(Color.GREEN);
        rectangle.setStroke(Color.DARKGREEN);
        rectangle.relocate(0, 5000);
        
        // substitue Picto loupe 
        
        Circle Cercle = new Circle(10);
        Cercle.setFill(Color.GREEN);
        Cercle.setStroke(Color.DARKGREEN);
        Cercle.relocate(1000, 5333);
        
/****************************************************************************************/    
       
/*********************************   Contenaire du menu   *********************************/
        
        HBox head = new HBox(30);
        
/*********************************   contenaire du champ de recherche  *********************************/
        
        HBox seachField = new HBox(0);
       
/*************************************   champ de recherche  ******************************************/ 
        
        TextField recherche = new TextField( " rechercher ");

/*********************************   contenaire du champ de recherche / parametres  ********************************/
        
        seachField.getChildren().addAll(recherche, Cercle );
        seachField.setAlignment(Pos.CENTER);
        seachField.setMaxHeight(15);
        seachField.setStyle(
        		"-fx-background-radius: 5em; "+ 
        		"-fx-background-color: white;"+
        		"-fx-padding: 0.5em;"
        		);

/*********************************   champ de recherche / parametres  *********************************/  
        
        recherche.setPrefSize(450,10);
        recherche.setStyle(
        		"-fx-border: 0;"
        		+ "-fx-box-shadow: none;"
        		+ "-fx-background-color: white;"
        		);       
        
        recherche.setOnKeyPressed(e ->{
        	
        	if (e.getCode()== KeyCode.ENTER) {
        		
        		String search = recherche.getText();
        		String[] terms = search.split(" ");

        		StringBuilder formatReserch = new StringBuilder();

                for (String term : terms) {
                	
                    if (formatReserch.length() > 0) {
                    	formatReserch.append(",");
                    }

                    if (term.contains(" ")) {
                        
                    	formatReserch.append("\"").append(term).append("\"");
                    } else {
                    	formatReserch.append(term);
                    }
                 }
                
                System.out.println(formatReserch.toString());
        		} 	
        	}
        ); 

/*****************************************  bouton Login  *******************************************************/  
        
        Button btnLog = new Button("| Log |"); 
        btnLog.setStyle(
                "-fx-background-radius: 5em; " +
                "-fx-min-width: 30px; " +
                "-fx-min-height: 30px; " +
                "-fx-max-width: 30px; " +
                "-fx-max-height: 30px;"
        );
        
/************************************   Contenaire du menu / parametres  ********************************************/ 
        
        head.getChildren().addAll(rectangle,seachField, btnLog);
        head.setAlignment(Pos.CENTER);
        head.setMinHeight(90);
        
/************************************  Tableau  *******************************************************/            
        
        TableView<Stagiaire> table = new TableView<Stagiaire>();

        TableColumn<Stagiaire, String> userlastNameCol //
        		= new TableColumn<Stagiaire, String>("Nom");
        		userlastNameCol.prefWidthProperty().bind(table.widthProperty().divide(5));
        		userlastNameCol.setCellValueFactory(new PropertyValueFactory<Stagiaire,String>("nom"));

        TableColumn<Stagiaire, String> userNameCol //
        		= new TableColumn<Stagiaire, String>("Prénom");
        		userNameCol.prefWidthProperty().bind(table.widthProperty().divide(5));
        		userNameCol.setCellValueFactory(new PropertyValueFactory<Stagiaire,String>("prenom"));
        
        TableColumn<Stagiaire, Integer> DepCol//
                = new TableColumn<Stagiaire, Integer>("Département");
                DepCol.prefWidthProperty().bind(table.widthProperty().divide(5));
                DepCol.setCellValueFactory(new PropertyValueFactory<Stagiaire,Integer>("departement"));
                          
        TableColumn<Stagiaire, String> promoNameCol//
                = new TableColumn<Stagiaire, String>("Promotion");
                promoNameCol.prefWidthProperty().bind(table.widthProperty().divide(5));               
                promoNameCol.setCellValueFactory(new PropertyValueFactory<Stagiaire,String>("promotion"));
                
        TableColumn<Stagiaire, Integer> YearCol//
                = new TableColumn<Stagiaire, Integer>("Année");
                YearCol.prefWidthProperty().bind(table.widthProperty().divide(5));
                YearCol.setCellValueFactory(new PropertyValueFactory<Stagiaire,Integer>("annee"));    
                YearCol.setStyle(
                		"-fx-border: 0;"
                		+ "-fx-box-shadow: none;"
                		//+ "-fx-background-color: white;"
                		+ "-fx-font-size:1em;"
                		);

        table.getColumns().addAll(userlastNameCol,userNameCol, DepCol, promoNameCol,YearCol);
        
    	table.setItems(datas);
    	
/*********************************  Sections template *******************************************************/  
    	
        root.setTop(head);
   
        root.setCenter(table);
 
        root.setBottom(lblBottom);

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Caller");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
    	
        launch();
    }

}