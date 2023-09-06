package view;

import java.util.ArrayList;
import java.util.List;

import controller.Recherhe;
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
import model.ArbreBinaire;
import model.Noeud;
import model.Stagiaire;


public class HomePan extends BorderPane {
	
	private Button btn = new Button("Changer de scene");
	ArrayList<String> finds;
	List<Stagiaire> arrayList ;
	
	
	
	public HomePan() {
		
		super();
		getChildren().add(btn); 
		setPrefSize(400, 200); 
		
 ObservableList<Stagiaire> datas= FXCollections.observableArrayList();  
 
 ArbreBinaire stagiaires = new ArbreBinaire(new Noeud(new Stagiaire("Doe", "John", "65", "ATL200", 1985)));

	stagiaires.ajouterNoeudDansArbre(new Stagiaire("Smith", "Alice", "93", "AOB L200", 2000));
	stagiaires.ajouterNoeudDansArbre (new Stagiaire("Johnson", "Bob", "95", "ATL 200", 2500));
	stagiaires.ajouterNoeudDansArbre(new Stagiaire("Doe", "Eva", "60", "Zalt 200", 542));
	stagiaires.ajouterNoeudDansArbre(new Stagiaire("AUGEREAU","Kevin"," 76", "AI 78", 2010));
	stagiaires.ajouterNoeudDansArbre(new Stagiaire("AKHIAD","Brahim", "92", "AI 60", 2003));
	stagiaires.ajouterNoeudDansArbre(new Stagiaire("BOUAMAMA","Yahya", "93", "AROBAS", 2008));
	stagiaires.ajouterNoeudDansArbre(new Stagiaire("BOUCHET","Laurent", "91", "ET 34847", 1998));
	stagiaires.ajouterNoeudDansArbre(new Stagiaire("CHAVENEAU","Kim Anh", "92", "ATOD 2", 2014));
	stagiaires.ajouterNoeudDansArbre(new Stagiaire("CHONE","Martin", "92", "ATOD 24 cp", 2015));
	stagiaires.ajouterNoeudDansArbre(new Stagiaire("GARIJO","Rosie", "79", "AI 79", 2011));
	stagiaires.ajouterNoeudDansArbre(new Stagiaire("LACROIX","Pascale", "91", "BOBI 5", 2008));
	stagiaires.ajouterNoeudDansArbre(new Stagiaire("LECLERC","Dominique", "75", "ATOD 12", 2011));
	stagiaires.ajouterNoeudDansArbre(new Stagiaire("NOUAR","Adel", "94", "ATOD 5", 2009));
	stagiaires.ajouterNoeudDansArbre(new Stagiaire("POTIN","Thomas", "75", "ATOD 21", 2014));
	stagiaires.ajouterNoeudDansArbre(new Stagiaire("PUCELLE","David", "75", "ERE 234", 2001));
	stagiaires.ajouterNoeudDansArbre(new Stagiaire("ROIGNANT","Pierre-Yves", "77", "ATOD 26 cp", 2015));
	stagiaires.ajouterNoeudDansArbre(new Stagiaire("ROIGNANT","Pierre-Yves", "93", "AI 95", 2015));
	stagiaires.ajouterNoeudDansArbre(new Stagiaire("UNG","Jet-Ming", "75", "ATOD 16 cp", 2012));
	
	// String[] finds = {"Johnson"};
	List<Stagiaire> arrayList = stagiaires.convertirEnArrayList();
	
	/* for (Stagiaire stagiaire : arrayList) {
         System.out.println(stagiaire.nom + " " + stagiaire.prenom + " " + stagiaire.annee);
     }*/
         
		datas.setAll(arrayList);
		
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
                
               // System.out.println(formatResearch2.toString());
                
                ArrayList<String> finds = formatResearch2;
                //arrayList = stagiaires.convertirEnArrayList();
                
                //System.out.println(stringArray.toString());
                Recherhe r = new Recherhe();
    	        List<Stagiaire> resultList = r.search(stagiaires,finds);

    	        if (!resultList.isEmpty()) {
    	        	
    	        	String response = resultList.size() >= 2 ? "Contacts trouvés":"Contact trouvé";
    	        	System.out.println(response);
    	        	           
    	            for (Stagiaire stagiaire : resultList) {
    	                System.out.println(
    	                		"Nom: " + stagiaire.nom 
    	                		+ ", Prénom: "+ stagiaire.prenom+
    	                		", Promotion: "+ stagiaire.promotion +
    	                		", Département: "+ stagiaire.departement +
    	                		", Année: " + stagiaire.annee);
    	            }
    	        } else {
    	            System.out.println("Aucun élément trouvé");
    	        }
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
        		System.out.println(new PropertyValueFactory<Stagiaire,String>("nom"));

        TableColumn<Stagiaire, String> userNameCol //
        		= new TableColumn<Stagiaire, String>("Prénom");
        		userNameCol.prefWidthProperty().bind(table.widthProperty().divide(5));
        		userNameCol.setCellValueFactory(new PropertyValueFactory<Stagiaire,String>("prenom"));
        
        TableColumn<Stagiaire, String> DepCol//
                = new TableColumn<Stagiaire, String>("Département");
                DepCol.prefWidthProperty().bind(table.widthProperty().divide(5));
                DepCol.setCellValueFactory(new PropertyValueFactory<Stagiaire,String>("departement"));
                          
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
 * 
 * 
 * 
 * 
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
