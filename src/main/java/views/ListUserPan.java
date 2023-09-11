package views;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import metier.Recherche;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.ArbreBinaire;
import model.NoeudBinaire;

import model.Stagiaire;


public class ListUserPan extends BorderPane {

	private ArrayList<String> finds;
	private List<NoeudBinaire> arrayList ;
	private Label label = new Label(" liste d'utilisateur");

	public ArrayList<Stagiaire> lUser;
	static ArbreBinaire arbreAnnuaire;
	private Pane screen;
	public static boolean isAdmin;
	public  ListS ls;


	public ListUserPan() throws IOException {
		super();	
		this.isAdmin=false;
		setPrefSize(400, 200);
		setStyle("-fx-background-color:white");
		label.setTextFill(Color.WHITE);
		getChildren().add(label);

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

		Pane topPannel = new Pane();
		topPannel.setPrefSize(800, 100);

		Pane leftPannel = new Pane();
		leftPannel.setPrefSize(100, 700);

		Label el = new Label("ici");
		el.setPrefWidth(0);

		/**
		 * Subs
		 */    
		
		// logo Isika
		File file = new File("src/Files/logo_isika_petit2.png");
		// --> file:/C:/MyImages/myphoto.jpg
		String localUrl = file.toURI().toURL().toString();
		Image image = new Image(localUrl);
		ImageView imageView = new ImageView(image);
		imageView.relocate(0, 5000) ;

		// Picto loupe 
		File fileLoupe = new File("src/Files/pictogramme_loupe_petit.png");
		// --> file:/C:/MyImages/myphoto.jpg
		String localUrlLoupe = fileLoupe.toURI().toURL().toString();
		Image imageLoupe = new Image(localUrlLoupe);
		ImageView imageViewLoupe = new ImageView(imageLoupe);
		imageViewLoupe.relocate(1000, 5333) ;


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

		searchContainer.getChildren().addAll(searchField,imageViewLoupe);
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
		
		
		// click event init Textfield

		searchField.setOnMouseClicked(event -> {
			if (event.getClickCount() == 1) {
				searchField.clear();
			}
			obsListeStagiaires.setAll(lUser);
		});

		// Enter Keyword event

		searchField.setOnKeyPressed(e ->{

			ArrayList<String>  formatResearch2= new ArrayList<>();

			if (e.getCode()== KeyCode.ENTER) {

				String search = searchField.getText();       		

				String[] terms = search.split(" ");
		
				
		// system de recherche				
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

					lUser = listeStagiairesrecherche;
					try {
						ListS searchResult= new ListS(this, lUser);
						setCenter(searchResult);


					} catch (IOException e1) {
			
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

		Button btnLog = new Button("Connexion"); 
		btnLog.setStyle(
				"-fx-background-radius: 5em; " +
						"-fx-min-width: 80px; " +
						"-fx-min-height: 30px; " +
						"-fx-max-width: 80px; " +
						"-fx-max-height: 30px;"
				);


		Button btnAdd = new Button("Ajouter"); 
		btnAdd.setStyle(
				"-fx-background-radius: 5em; " +
						"-fx-min-width: 80px; " +
						"-fx-min-height: 30px; " +
						"-fx-max-width: 80px; " +
						"-fx-max-height: 30px;"
				);

		// Login Screen
		LoginView logCard= new LoginView(this);


		btnLog.setOnMouseClicked(event -> {		
			Pane newContent = logCard;
			logCard.setPrefWidth(260);
			setRight(newContent);

			Duration duration = Duration.seconds(0.33);
			KeyValue keyValue = new KeyValue(logCard.prefWidthProperty(), 260);
			KeyFrame keyFrame = new KeyFrame(duration, keyValue);
			Timeline timeline = new Timeline(keyFrame);

			timeline.play();


		});


		// AddStagiaire Screen
		FormulaireAjout addCard=new  FormulaireAjout(this);

		btnAdd.setOnMouseClicked(e->{

			Pane newContnent = addCard;
			setRight(newContnent);

		});

		/**
		 * Contenaire du menu-parametres
		 */ 

		head.getChildren().addAll(imageView,searchContainer,btnAdd,btnLog);
		head.setAlignment(Pos.CENTER);
		head.setMinHeight(90);
		head.setStyle( "-fx-background-color: #ff8d64;");
        head.setPadding(new Insets(0, 30, 0, 50));

		/**
		 * Tableau
		 */            

		ls=new ListS(this,lUser );

		//instance de eéléments de droite;

		Card c = new Card(this);

		/**
		 * Sections template
		 */  


		setTop(head);
		setCenter(ls);
		setRight(c);

	
	}


}
