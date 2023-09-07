package views;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import metier.LectureFichier;
import metier.Recherche;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.ArbreBinaire;
import model.Noeud;
import model.NoeudBinaire;
import model.NoeudBinaire;

public class ListUserPan extends BorderPane {
	private Button btn = new Button("Changer de scene");
	ArrayList<String> finds;
	List<NoeudBinaire> arrayList ;
	
	private Label label = new Label(" liste d'utilisateur");
	private int sizeParam = 5;
	public ListUserPan() throws IOException {
		super();
		setPrefSize(400, 200);
		//setAlignment(Pos.CENTER);
		setStyle("-fx-background-color:white");
		label.setTextFill(Color.WHITE);
		getChildren().add(label);
	
		LectureFichier lectureFichier = new LectureFichier() ;
		
		//Attention, supprimer fichier bin des tests precedents avant de lancer ce main

		ArbreBinaire arbreAnnuaire = new ArbreBinaire() ;
		
		arbreAnnuaire.lectureFichierDon(arbreAnnuaire);

		ArrayList<NoeudBinaire> listeStagiaires = new ArrayList<NoeudBinaire>();
		NoeudBinaire noeudVersArrayList = new NoeudBinaire();
		noeudVersArrayList = noeudVersArrayList.lireNoeudFichierBinVersObjetNoeudBinaire(arbreAnnuaire.getRaf());
		listeStagiaires = noeudVersArrayList.fichierBinVersArrayList(arbreAnnuaire.getRaf());
		ObservableList<NoeudBinaire> obsListeStagiaires= FXCollections.observableArrayList(listeStagiaires);
		obsListeStagiaires.setAll(listeStagiaires);
		//arbreAnnuaire.testLecture();
		
		
    	Label lblBottom = new Label(" ");
    	Label lblRight = new Label (" ");
    	Label lblCenter = new Label(" ");
        
    	Pane topPannel = new Pane();
        topPannel.setPrefSize(800, 100);
       
        Pane leftPannel = new Pane();
        leftPannel.setPrefSize(100, 700);
        
        Label el = new Label("ici");
        el.setPrefWidth(0);

        
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
                Recherche r = new Recherche();
                
                // TO DO : fonction recherche
    	        //List<NoeudBinaire> resultList = r.search(arbreAnnuaire,finds);

    	       /* if (!resultList.isEmpty()) {
    	        	
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
    	            //System.out.println("Aucun élément trouvé");
    	            searchField.setText("Aucun élément trouvé");
    	        }*/
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
      
        TableView<NoeudBinaire> table = new TableView<NoeudBinaire>();

        TableColumn<NoeudBinaire, String> userlastNameCol //
        		= new TableColumn<NoeudBinaire, String>("Nom");
        		userlastNameCol.prefWidthProperty().bind(table.widthProperty().divide(sizeParam ));
        		userlastNameCol.setCellValueFactory(new PropertyValueFactory<NoeudBinaire,String>("stagiaire"));

        		System.out.println(new PropertyValueFactory<NoeudBinaire,String>("stagiaire"));

        TableColumn<NoeudBinaire, String> userNameCol //
        		= new TableColumn<NoeudBinaire, String>("Prénom");
        		userNameCol.prefWidthProperty().bind(table.widthProperty().divide(sizeParam ));
        		userNameCol.setCellValueFactory(new PropertyValueFactory<NoeudBinaire,String>("prenom"));
        
        TableColumn<NoeudBinaire, String> DepCol//
                = new TableColumn<NoeudBinaire, String>("Département");
                DepCol.prefWidthProperty().bind(table.widthProperty().divide(sizeParam ));
                DepCol.setCellValueFactory(new PropertyValueFactory<NoeudBinaire,String>("departement"));
                          
        TableColumn<NoeudBinaire, String> promoNameCol//
                = new TableColumn<NoeudBinaire, String>("Promotion");
                promoNameCol.prefWidthProperty().bind(table.widthProperty().divide(sizeParam ));               
                promoNameCol.setCellValueFactory(new PropertyValueFactory<NoeudBinaire,String>("promotion"));
                
        TableColumn<NoeudBinaire, Integer> YearCol//
                = new TableColumn<NoeudBinaire, Integer>("Année");
                YearCol.prefWidthProperty().bind(table.widthProperty().divide(sizeParam ));
                YearCol.setCellValueFactory(new PropertyValueFactory<NoeudBinaire,Integer>("annee"));    
                YearCol.setStyle(
                		"-fx-border: 0;"
                		+ "-fx-box-shadow: none;"
                		//+ "-fx-background-color: white;"
                		+ "-fx-font-size:1em;"
                		);
                
               

        table.getColumns().addAll(userlastNameCol,userNameCol, DepCol, promoNameCol,YearCol); 
    	table.setItems(obsListeStagiaires);
    	
    	
     	
    	Card root = new Card();
    	root.setPrefWidth(1);
    	
   	 	TranslateTransition scaleTransition = new TranslateTransition(Duration.seconds(1),root); 
   	 	
   	   
     
   	
        
    	table.setOnMouseClicked(event -> {
    	    if (event.getClickCount() == 1) { // Clic simple
    	        NoeudBinaire stagiaire = table.getSelectionModel().getSelectedItem();
    	        if (stagiaire != null) {
    	        	
    	        	
    	        	root.afficher( 
    	        			stagiaire.getStagiaire().getNom(),
    	        			stagiaire.getStagiaire().getPrenom(),
    	        			stagiaire.getStagiaire().getDepartement(),
    	        			stagiaire.getStagiaire().getPromotion(),
    	        			stagiaire.getStagiaire().getAnnee()
    	        			);
    	        	 scaleTransition.setToX(40);
    	        	scaleTransition.play();
    	        	root.setPrefWidth(400);
    	        	//scaleTransition.setByX(0); // Augmente le facteur d'échelle X
    	            //scaleTransition.setByY(0);
    	        // System.out.println("Stagiaire sélectionné : " + stagiaire.getNom() + " " + stagiaire.getPrenom());

    	            sizeParam = 1;
    	            
    	        }
    	    }
    	});
    	
/**
 * Sections template
 * 
 * 
 * 		
 * 
 */  
        setTop(head);
        setCenter(table);
        setRight(root);
        setBottom(lblBottom);

		btn.setOnAction(new EventHandler<ActionEvent>() { 
			@Override
		public void handle(ActionEvent arg0) {
		        
		try {
			ListUserPan secondPan = new ListUserPan();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Stage stage = (Stage) ListUserPan.this.getScene().getWindow(); 
		stage.setScene(getScene());
		}
			});
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}
	
	
}
