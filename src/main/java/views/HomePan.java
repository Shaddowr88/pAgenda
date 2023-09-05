package views;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Stagiaire;

public class HomePan extends BorderPane {
	
	private Button btn = new Button("Changer de scene");

	public HomePan() {
		
		super();
		getChildren().add(btn); 
		setPrefSize(400, 200); 
		
 ObservableList<Stagiaire> datas= FXCollections.observableArrayList();  
         
 datas.add(new Stagiaire("AUGEREAU","Kevin"," 76", "AI 78", 2010));
 datas.add(new Stagiaire("AKHIAD","Brahim", "92", "AI 60", 2003));

 datas.add(new Stagiaire("BOUAMAMA","Yahya", "93", "AROBAS", 2008));
 datas.add(new Stagiaire("BOUCHET","Laurent", "91", "ET 34847", 1998));

 datas.add(new Stagiaire("CHAVENEAU","Kim Anh", "92", "ATOD 2", 2014));
 datas.add(new Stagiaire("CHONE","Martin", "92", "ATOD 24 cp", 2015));
 
 datas.add(new Stagiaire("GARIJO","Rosie", "79", "AI 79", 2011));

 datas.add(new Stagiaire("LACROIX","Pascale", "91", "BOBI 5", 2008));
 datas.add(new Stagiaire("LECLERC","Dominique", "75", "ATOD 12", 2011));
 
 datas.add(new Stagiaire("NOUAR","Adel", "94", "ATOD 5", 2009));
 
 datas.add(new Stagiaire("POTIN","Thomas", "75", "ATOD 21", 2014));
 datas.add(new Stagiaire("PUCELLE","David", "75", "ERE 234", 2001));
 
 datas.add(new Stagiaire("ROIGNANT","Pierre-Yves", "77", "ATOD 26 cp", 2015));
 datas.add(new Stagiaire("ROIGNANT","Pierre-Yves", "93", "AI 95", 2015));
 
 datas.add(new Stagiaire("UNG","Jet-Ming", "75", "ATOD 16 cp", 2012));
		
        
    	Label lblBottom = new Label(" ");
    	Label lblRight = new Label (" ");
    	Label lblCenter = new Label(" ");
        
    	Pane topPannel = new Pane();
        topPannel.setPrefSize(800, 100);
       
        Pane leftPannel = new Pane();
        leftPannel.setPrefSize(100, 700);
        
        
 /**
  * Subs
  */    
        
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
 
/**
 * Contenaire du menu
 */
        
        HBox head = new HBox(30);
        
/**
 * contenaire du champ de recherche
 */
        
        HBox searchContainer = new HBox(0);
       
/**
 * champ de recherche 
 */ 
        
        TextField searchField = new TextField( " rechercher ");

/*
 * contenaire du champ de recherche parametres 
 */
        
        searchContainer.getChildren().addAll(searchField, Cercle );
        searchContainer.setAlignment(Pos.CENTER);
        searchContainer.setMaxHeight(15);
        searchContainer.setStyle(
        		"-fx-background-radius: 5em; "+ 
        		"-fx-background-color: white;"+
        		"-fx-padding: 0.5em;"
        		);

/**
 * champ de recherche / parametres
 */  
        
        searchField.setPrefSize(450,10);
        searchField.setStyle(
        		"-fx-border: 0;"
        		+ "-fx-box-shadow: none;"
        		+ "-fx-background-color: white;"
        		);       
 
        searchField.setOnKeyPressed(e ->{
        	
        	ArrayList<String>  formatResearch2= new ArrayList<>();
        	
        	if (e.getCode()== KeyCode.ENTER) {
        		
        		String search = searchField.getText();
        		String[] terms = search.split(" ");

        		StringBuilder formatResearch = new StringBuilder();

                for (String term : terms) {
                	
                    if (formatResearch.length() > 0) {
                    	
                    	formatResearch2.add(term);
                    	formatResearch.append(",");
                    }

                    if (term.contains(" ")) {
                        
                    	formatResearch.append("\"").append(term).append("\"");
                    } else {
                    	formatResearch.append(term);
                    	formatResearch2.add(term);
                    }
                 }
                
                System.out.println(formatResearch2.toString());
        		} 	
        	}
        ); 

/**
 * bouton Login
 */  
        
        Button btnLog = new Button("| Log |"); 
        btnLog.setStyle(
                "-fx-background-radius: 5em; " +
                "-fx-min-width: 30px; " +
                "-fx-min-height: 30px; " +
                "-fx-max-width: 30px; " +
                "-fx-max-height: 30px;"
        );
        
/**
 * Contenaire du menu-parametres
 */ 
        
        head.getChildren().addAll(rectangle,searchContainer, btnLog);
        head.setAlignment(Pos.CENTER);
        head.setMinHeight(90);
        
/**
 * Tableau
 */            
        
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
    	
/**
 * Sections template
 */  
    	
        setTop(head);
        setCenter(table);
        setBottom(lblBottom);

		btn.setOnAction(new EventHandler<ActionEvent>() { 
			@Override
		public void handle(ActionEvent arg0) {
		        
		ListUserPan secondPan = new ListUserPan();
		
		Stage stage = (Stage) HomePan.this.getScene().getWindow(); 
		stage.setScene(getScene());
		}
			});
	}
	
	
	
	
	

}
