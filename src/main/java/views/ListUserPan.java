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
	//private Button btn = new Button("Changer de scene");
	private ArrayList<String> finds;
	private List<NoeudBinaire> arrayList ;
	private Label label = new Label(" liste d'utilisateur");
	private int sizeParam = 5;
	public ArrayList<Stagiaire> lUser;
	static ArbreBinaire arbreAnnuaire;
	private Pane screen;
	public static boolean isAdmin;
	public  ListS ls;
	
	
	public ListUserPan() throws IOException {
		super();	
		this.isAdmin=true;
		setPrefSize(400, 200);
		//setAlignment(Pos.CENTER);
		setStyle("-fx-background-color:white");
		label.setTextFill(Color.WHITE);
		getChildren().add(label);
	
		//LectureFichier lectureFichier = new LectureFichier() ;
		
		//Attention, supprimer fichier bin des tests precedents avant de lancer ce main

		arbreAnnuaire = new ArbreBinaire() ;
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
    	    	   //obsListeStagiaires.setAll(listeStagiairesrecherche );
    	    	   lUser = listeStagiairesrecherche;
    	    	   try {
					ListS searchResult= new ListS(this, lUser);
					setCenter(searchResult);
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    	    	   ;
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
        
        
        Button btnAdd = new Button("+"); 
        btnLog.setStyle(
                "-fx-background-radius: 5em; " +
                "-fx-min-width: 30px; " +
                "-fx-min-height: 30px; " +
                "-fx-max-width: 30px; " +
                "-fx-max-height: 30px;"
        );
        
        
        
        LoginView logCard= new LoginView(this);
        
        
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
        
        
        
        FormulaireAjout addCard=new  FormulaireAjout();
        
        btnAdd.setOnMouseClicked(e->{
        	
        	Pane newContnent = addCard;
        	setRight(newContnent);
        	
        });

/**
 * Contenaire du menu-parametres
 */ 
        
        head.getChildren().addAll(rectangle,searchContainer,btnAdd,btnLog);
        head.setAlignment(Pos.CENTER);
        head.setMinHeight(90);
        
/**
 * Tableau
 */            
        
       ls=new ListS(this,lUser );
        
        ls.setMinHeight(100);
        
    	Card c = new Card(this);
  
/**
 * Sections template
 */  
    	
    	
        setTop(head);
        setCenter(ls);
        
        setRight(c);
        
   //  ((ListUserPan)ls.getScene().getRoot()).getS;

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
