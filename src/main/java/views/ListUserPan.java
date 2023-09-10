package views;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import java.util.List;

import metier.Recherche;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
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
import model.NoeudBinaire;
import model.Personnel;
import model.Stagiaire;
import model.NoeudBinaire;

public class ListUserPan extends BorderPane {
	private Button btn = new Button("Changer de scene");
	ArrayList<String> finds;
	List<NoeudBinaire> arrayList ;
	
	private Label label = new Label(" liste d'utilisateur");
	private int sizeParam = 5;
	public ArrayList<Stagiaire> lUser;
	ArbreBinaire arbreAnnuaire = new ArbreBinaire();
	
//	private Personnel isAdmin;
	private Pane screen;
	public static boolean isAdmin;
	
	

	
	
	public ListUserPan() throws IOException {
		super();	
		this.isAdmin=false;
		setPrefSize(400, 200);
		//setAlignment(Pos.CENTER);
		setStyle("-fx-background-color:white");
		label.setTextFill(Color.WHITE);
		getChildren().add(label);
	
		//LectureFichier lectureFichier = new LectureFichier() ;
		
		//Attention, supprimer fichier bin des tests precedents avant de lancer ce main

		ArbreBinaire arbreAnnuaire = new ArbreBinaire() ;
		
		
		arbreAnnuaire.lectureFichierDon(arbreAnnuaire);

		ArrayList<Stagiaire> listeStagiaires = new ArrayList<Stagiaire>();
		NoeudBinaire noeudVersArrayList = new NoeudBinaire();
		arbreAnnuaire.getRaf().seek(0);
		noeudVersArrayList = noeudVersArrayList.lireNoeudFichierBinVersObjetNoeudBinaire(arbreAnnuaire.getRaf());
		listeStagiaires = noeudVersArrayList.fichierBinVersArrayList(arbreAnnuaire.getRaf());
		
		ObservableList<Stagiaire> obsListeStagiaires= FXCollections.observableArrayList(listeStagiaires);
		
		lUser =listeStagiaires;
		
		obsListeStagiaires.setAll(lUser);
		
    	Label lblBottom = new Label(" ");
        
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
        
        searchField.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
            	 searchField.clear();
            }
            obsListeStagiaires.setAll(lUser);
        });
 
        searchField.setOnKeyPressed(e ->{
        	
        	ArrayList<String>  formatResearch2= new ArrayList<>();
        	
        	if (e.getCode()== KeyCode.ENTER) {

        		String search = searchField.getText();       		
        		
        		String[] terms = search.split(" ");
        		 //System.out.println(search);
        		StringBuilder formatResearch = new StringBuilder();

                for (String term : terms) {
                	
                    if (formatResearch.length() > 0) {
                    	System.out.println(term);
                    	
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
                
                ArrayList<String> finds = formatResearch2;
                Recherche r = new Recherche();
                
                arbreAnnuaire.lectureFichierDon(arbreAnnuaire);
            	
            
    	       List<Stagiaire> resultList = null;
    	       try {
    	    	   resultList = r.search(arbreAnnuaire.getRaf(),finds);
    	    	   } catch (IOException e1) {
    	    		   e1.printStackTrace();
    	    		   }
    	       if (!resultList.isEmpty()) {
    	    	   String response = resultList.size() >= 2 ? "Contacts trouvés":"Contact trouvé";
    	    	   System.out.println(response);
    	    	   ArrayList<Stagiaire> listeStagiairesrecherche = new ArrayList<Stagiaire>();
    	    	   for (Stagiaire stagiaire : resultList) {
    	    		   listeStagiairesrecherche.add(stagiaire);
    	    		   }
    	    	   obsListeStagiaires.setAll(listeStagiairesrecherche );
    	    	   } else 
    	    	   {
    	            searchField.setText("Aucun élément trouvé");
    	             return;     	
    	            }
    	       } 	
        	}
        ); 
            
/**
 * bouton Login
 */  
        
       

        // Démarrer la transition
      
        Button btnLog = new Button("| Log |"); 
        btnLog.setStyle(
                "-fx-background-radius: 5em; " +
                "-fx-min-width: 30px; " +
                "-fx-min-height: 30px; " +
                "-fx-max-width: 30px; " +
                "-fx-max-height: 30px;"
        );
        
        
        LoginView logCard= new LoginView();
        
        
        btnLog.setOnMouseClicked(event -> {		
    		Pane newContent = logCard;
    		//logCard.setPrefWidth(400);
    		setRight(newContent);
    		
    		 Duration duration = Duration.seconds(0.33);
    	        KeyValue keyValue = new KeyValue(logCard.prefWidthProperty(), 400);
    	        KeyFrame keyFrame = new KeyFrame(duration, keyValue);
    	        Timeline timeline = new Timeline(keyFrame);
    				
    	        timeline.play();
    	        
    	  
    	});
    	
        
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
        		userlastNameCol.prefWidthProperty().bind(table.widthProperty().divide(sizeParam ));
        		userlastNameCol.setCellValueFactory(new PropertyValueFactory<Stagiaire,String>("nom"));

        TableColumn<Stagiaire, String> userNameCol //
        		= new TableColumn<Stagiaire, String>("Prénom");
        		userNameCol.prefWidthProperty().bind(table.widthProperty().divide(sizeParam ));
        		userNameCol.setCellValueFactory(new PropertyValueFactory<Stagiaire,String>("prenom"));
        
        TableColumn<Stagiaire, String> DepCol//
                = new TableColumn<Stagiaire, String>("Département");
                DepCol.prefWidthProperty().bind(table.widthProperty().divide(sizeParam ));
                DepCol.setCellValueFactory(new PropertyValueFactory<Stagiaire,String>("departement"));
                          
        TableColumn<Stagiaire, String> promoNameCol//
                = new TableColumn<Stagiaire, String>("Promotion");
                promoNameCol.prefWidthProperty().bind(table.widthProperty().divide(sizeParam ));               
                promoNameCol.setCellValueFactory(new PropertyValueFactory<Stagiaire,String>("promotion"));
                
        TableColumn<Stagiaire, Integer> YearCol//
                = new TableColumn<Stagiaire, Integer>("Année");
                YearCol.prefWidthProperty().bind(table.widthProperty().divide(sizeParam ));
                YearCol.setCellValueFactory(new PropertyValueFactory<Stagiaire,Integer>("annee"));    
                YearCol.setStyle(
                		"-fx-border: 0;"
                		+ "-fx-box-shadow: none;"
                		//+ "-fx-background-color: white;"
                		+ "-fx-font-size:1em;"
                		);
                
        table.getColumns().addAll(userlastNameCol,userNameCol, DepCol, promoNameCol,YearCol); 
    	table.setItems(obsListeStagiaires);
     	
    	Card carteStagiaire = new Card();
    	TranslateTransition scaleTransition = new TranslateTransition(Duration.seconds(1),carteStagiaire); 
    	carteStagiaire.setPrefWidth(1);
    	table.setOnMouseClicked(event -> {		
    	
    		KeyValue keyValue = new KeyValue(carteStagiaire.prefWidthProperty(), 400);
    		Duration duration = Duration.seconds(0.33);
    		KeyFrame keyFrame = new KeyFrame(duration, keyValue);
    	    Timeline timeline = new Timeline(keyFrame);
    	   
    	    if (event.getClickCount() == 1) { 
    	    	Stagiaire stagiaire = table.getSelectionModel().getSelectedItem();
    	        if (stagiaire != null) {  	
    	        	carteStagiaire.afficher( 
    	        			stagiaire.getNom(),
    	        			stagiaire.getPrenom(),
    	        			stagiaire.getDepartement(),
    	        			stagiaire.getPromotion(),
    	        			stagiaire.getAnnee()
    	        			);
    	        	Pane newContent = carteStagiaire;
    	    		setRight(newContent);
    	    		scaleTransition.setToX(40);
    	        	scaleTransition.play();
    	        	timeline.play();
    	            sizeParam = 1;
    	            carteStagiaire.setPrefWidth(400); 
    	        } 
    	    }
    	});
    	
/**
 * Sections template
 */  
    	
    	
        setTop(head);
        setCenter(table);
        
        setRight(screen);
        
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

	public static boolean isAdmin() {
		return isAdmin;
	}

	public static void setAdmin(boolean isAdmin2) {
		ListUserPan.isAdmin = isAdmin;
	}

	
	
	
	
}
