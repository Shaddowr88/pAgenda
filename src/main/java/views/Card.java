package views;


import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import model.NoeudBinaire;
import model.Stagiaire;

public class Card extends VBox {
	
	String Title;	
	
	public Label nom;
	private Label premon;
	private Label annee;
	private Label depart;
	private Label promo;
    private ModificationScreen f; 
    private Button mofierInfoStagiaire;
    private Button deleteInfoStagiaire;
    private ListUserPan root;

    
    HBox Validate = new HBox();
	
	 
	
	Boolean Log = ListUserPan.isAdmin;
	public Card (ListUserPan root) {
		
		
		
		
		this.root =root;
		///System.out.println(Log);
		BoutonSupprimer btns=new BoutonSupprimer();

		this.nom = new Label("");
		this.premon = new Label("");
		this.annee = new Label("");
		this.depart = new Label("");
		this.promo = new Label("");
	    this.mofierInfoStagiaire = new Button("Modifier");
	    this.deleteInfoStagiaire = new Button("poubelle");
	     
	     
	     HBox Validate = new HBox();
		
		
		
		btns.getTransforms().add(new Rotate(45, 50, 45));

		// System.out.println(Log);
		this.setStyle("-fx-background-color:cyan");
		btns.setOnAction(new EventHandler<ActionEvent>() {
			@Override
            public void handle(ActionEvent event) {
                // Réponse lorsque le bouton est cliqué
                System.out.println("ajouter");
            }
		});

	}



	public String getTitle() {
		return Title;
	}
	
	public void setTitle(String title) {
		this.Title = title;
	}
	
	 public void afficher(String nom, String prenom, String année, String dep, int i) {
		
		 this.nom.setText(nom); 
		 this.premon.setText(prenom); 
		 this.annee.setText(année); 
		 this.depart.setText(dep); 
		 this.promo.setText(Integer.toString(i));
		 
		 
		 
	 }
	 
	 
	 public void showDetails(Stagiaire item) {
	        // Effacez le contenu précédent de la carte
	        this.getChildren().clear();

	        // Affichez les détails de l'élément sélectionné dans la carte
	        Label nameLabel = new Label(item.getNom());
	        Label premonLabel = new Label(item.getPrenom());
	        Label yearLabel = new Label(Integer.toString(item.getAnnee()));
	        Label promotionLabel = new Label(item.getPromotion());
	        Label nameDepartement = new Label(item.getDepartement());
	        ModificationScreen f = new ModificationScreen(this, item,this.root); 
	        Button mofierInfoStagiaire = new Button("Modifier");
	        Button deleteInfoStagiaire = new Button("poubelle");
	        Button annulerInfoStagiaire = new Button("Annuler");
	        
	        
	        HBox Validate = new HBox();
	        StackPane window = new StackPane();
	        
	        
	        Button validateDelet = new Button("Supprimer");
	        Button unValidateDelet = new Button("Annuler");
	        window.getChildren().addAll(validateDelet);
     
	      //  System.out.println(" "+nameLabel + premonLabel +yearLabel+promotionLabel+nameDepartement );
	       // this.getChildren().addAll(nameLabel, premonLabel ,yearLabel,nameDepartement, promotionLabel);
	        
	         if (!(Log == true)) {
	        	 
	        	 this.getChildren().clear();
	        		 this.getChildren().addAll(nameLabel, premonLabel ,yearLabel,nameDepartement, promotionLabel);

	        		
	 
	         } else {
	        	 this.getChildren().addAll(nameLabel, premonLabel ,yearLabel,nameDepartement, promotionLabel,mofierInfoStagiaire, deleteInfoStagiaire);
	        	 mofierInfoStagiaire.setOnMouseClicked(event -> {
	 	            if (event.getClickCount() == 1) {
	 	            	this.getChildren().clear();
	 	            	this.getChildren().addAll(f);
	 	            }
	 	       
	 	        });
	        	 
	        	 deleteInfoStagiaire.setOnMouseClicked(event -> {
	        		 this.getChildren().clear();
	        		 this.getChildren().addAll(nameLabel, premonLabel ,yearLabel,nameDepartement, promotionLabel, Validate);
		 	            
	        		 if (event.getClickCount() == 1) {
	        			 	this.getChildren().clear();
		 	            	this.getChildren().addAll( nameLabel, premonLabel ,window );
		 	            	
		 	            	//this.getChildren().addAll(Validate);
		 	   
		 	            }
		 	        });
	        	 
	        	 
	        	 validateDelet.setOnMouseClicked(event -> {
	        		 this.getChildren().clear();
	
		 	            
	        		 if (event.getClickCount() == 1) {
	        			 	this.getChildren().clear();
	        			 	
	        			 	
	        			 	try {
	        			 		ListUserPan.arbreAnnuaire.supprimerStagiaire(item,root.arbreAnnuaire.getRaf());
								 NoeudBinaire noeudVersArrayList = new NoeudBinaire();
									ListUserPan.arbreAnnuaire.getRaf().seek(0);
									noeudVersArrayList = noeudVersArrayList.lireNoeudFichierBinVersObjetNoeudBinaire(ListUserPan.arbreAnnuaire.getRaf());
									ListUserPan.arbreAnnuaire.getRaf().seek(0);
									ArrayList<Stagiaire> listeStagiaires = noeudVersArrayList.fichierBinVersArrayList(ListUserPan.arbreAnnuaire.getRaf());
									//ObservableList<Stagiaire> obsListeStagiaires= FXCollections.observableArrayList(listeStagiaires);
									System.out.println("taille liste ap modif " + listeStagiaires.size());
									ListS listS2 = new ListS(root, listeStagiaires);
									root.setCenter(listS2);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	        			 
		 	            	this.getChildren().addAll( nameLabel, premonLabel ,window );
		 	            	
		 	            	//this.getChildren().addAll(Validate);
		 	   
		 	            }
		 	        });
 
	         }
	        
	        
	        
	        
	    }



	




	
	

}
