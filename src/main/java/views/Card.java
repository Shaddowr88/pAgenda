package views;


import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
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
		BoutonSupprimer btns=new BoutonSupprimer();

		this.nom = new Label("");
		this.premon = new Label("");
		this.annee = new Label("");
		this.depart = new Label("");
		this.promo = new Label("");
	    this.mofierInfoStagiaire = new Button("Modifier");
	    this.deleteInfoStagiaire = new Button("poubelle");
	    
	    this.setMinWidth(260);
		this.setMinHeight(510);
		this.setMaxWidth(260);
		this.setMaxHeight(510);
	     
	     HBox Validate = new HBox();
		
		btns.getTransforms().add(new Rotate(45, 50, 45));

		this.setStyle("-fx-background-color:#f1d278");
		//this.setPadding(new Insets(150, 50, 150, 50));
		
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
	        VBox vBoxFicheStagiaire = new VBox(); 
	        
	        HBox hBoxNom = new HBox();
	        
	        Label titreNomLabel = new Label("Nom : ");
	        Label nameLabel = new Label(item.getNom());
	        hBoxNom.getChildren().addAll(titreNomLabel, nameLabel) ;
	        hBoxNom.setStyle("-fx-background-color: white;"
	        			);
	        
	        HBox hBoxPrenom = new HBox();
	        Label titrePrenomLabel = new Label("Prénom : ");
	        Label premonLabel = new Label(item.getPrenom());
	        hBoxPrenom.getChildren().addAll(titrePrenomLabel, premonLabel) ;
	       hBoxPrenom.setStyle("-fx-background-color: #EEECD6;");
	        
	        HBox hBoxDpt = new HBox();
	        Label titreDptLabel = new Label("Département : ");
	        Label nameDepartement = new Label(item.getDepartement());
	        hBoxDpt.getChildren().addAll(titreDptLabel, nameDepartement) ;
	        hBoxDpt.setStyle("-fx-background-color: white;");
	        
	        HBox hBoxPromo = new HBox();
	        Label titrePromoLabel = new Label("Promotion : ");
	        Label promotionLabel = new Label(item.getPromotion());
	        hBoxPromo.getChildren().addAll(titrePromoLabel, promotionLabel) ;
	        hBoxPromo.setStyle("-fx-background-color: #EEECD6;");
	        
	        HBox hBoxAnnee = new HBox();
	        Label titreAnneeLabel = new Label("Année : ");
	        Label yearLabel = new Label(Integer.toString(item.getAnnee()));
	        hBoxAnnee.getChildren().addAll(titreAnneeLabel, yearLabel) ;
	        hBoxAnnee.setStyle("-fx-background-color: white;");
	        
	        ModificationScreen f = new ModificationScreen(this, item,this.root); 
	        Button mofierInfoStagiaire = new Button("Modifier");
	        Button deleteInfoStagiaire = new Button("Supprimer");
	        Button annulerInfoStagiaire = new Button("Annuler");
	        
	        
	        mofierInfoStagiaire.setPrefWidth(260);
	        deleteInfoStagiaire.setPrefWidth(260);
	        annulerInfoStagiaire.setPrefWidth(260);
	        
	        HBox Validate = new HBox();
	        VBox vBoxSupprimer = new VBox();
	        
	        Button validateDelet = new Button("Supprimer");
	        Button unValidateDelet = new Button("Annuler");
	        vBoxSupprimer.getChildren().addAll(validateDelet, unValidateDelet);
	        validateDelet.setPrefWidth(260);
	        unValidateDelet.setPrefWidth(260);
	        
	        vBoxFicheStagiaire.getChildren().addAll(hBoxNom, hBoxPrenom, hBoxDpt, hBoxPromo, hBoxAnnee);
	        //vBoxFicheStagiaire.setPrefWidth(260);
	        vBoxFicheStagiaire.setPadding(new Insets(10, 10, 10, 10));
	        vBoxFicheStagiaire.setStyle("-fx-font-size: 18px;");
     
	         if (!(Log == true)) {
	        	 
	        	 this.getChildren().clear();
	        		 this.getChildren().addAll(vBoxFicheStagiaire);

	        		
	 
	         } else {
	        	 this.getChildren().addAll(vBoxFicheStagiaire,mofierInfoStagiaire, deleteInfoStagiaire);
	        	 mofierInfoStagiaire.setOnMouseClicked(event -> {
	 	            if (event.getClickCount() == 1) {
	 	            	this.getChildren().clear();
	 	            	this.getChildren().addAll(f);
	 	            }
	 	       
	 	        });
	        	 
	        	 deleteInfoStagiaire.setOnMouseClicked(event -> {
	        		 this.getChildren().clear();
	        		 this.getChildren().addAll(vBoxFicheStagiaire, Validate);
		 	            
	        		 if (event.getClickCount() == 1) {
	        			 	this.getChildren().clear();
		 	            	this.getChildren().addAll( nameLabel, premonLabel , vBoxSupprimer );
		 	            	
		 	   
		 	            }
		 	        });
	        	 unValidateDelet.setOnMouseClicked(event -> {
	        		 this.getChildren().clear();
	        		 this.getChildren().addAll(vBoxFicheStagiaire, Validate);
		 	            
	        		 if (event.getClickCount() == 1) {
	        			 	this.getChildren().clear();
		 	            	this.getChildren().addAll( nameLabel, premonLabel , vBoxSupprimer );
		 	            	
		 	   
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
									ListS listS2 = new ListS(root, listeStagiaires);
									root.setCenter(listS2);
							} catch (IOException e) {
				
								e.printStackTrace();
							}
	        			 
		 	            	this.getChildren().addAll( nameLabel, premonLabel , vBoxSupprimer );
		 	            }
		 	        });
 
	         }
	        
	        
	        
	        
	    }



	




	
	

}
