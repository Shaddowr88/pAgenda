package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class ArbreBinaire {
	// attributs
	String nom = "";
	String prenom= "";
	String dpt = "";
	String promo = "";
	int annee = 0;
	int compteurLigne = 1;
	int compteurStagiaire = 0;
	private NoeudBinaire premierNoeud ;
	private RandomAccessFile raf = null ;
	static boolean premierNoeudCreeOuNon = false ;
	private final String BIN_PATH = "src/Files/annuaire.bin";
	private final String DON_PATH = "src/Files/STAGIAIRESTEST.DON";
	private static int calculIndex = 0 ; 
	private static int indexPere = 0 ;
	private static int indexFils = 0 ;
	
	// contructeur
	public ArbreBinaire(NoeudBinaire premierNoeud) 
	{
		this.premierNoeud = premierNoeud;
	}

	// constructeur vide 
	public ArbreBinaire() 
	{
		try 
		{
			raf = new RandomAccessFile(BIN_PATH, "rw");
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Getters & Setters
	public NoeudBinaire getPremierNoeud() 
	{
		return premierNoeud;
	}

	public void setPremierNoeud(NoeudBinaire premierNoeud) {
		this.premierNoeud = premierNoeud;
	}

	public RandomAccessFile getRaf() 
	{
		return raf;
	}

	public void setRaf(RandomAccessFile raf) 
	{
		this.raf = raf;
	}

	@Override
	public String toString() 
	{
		if (this.premierNoeud == null) 
		{// cas où l'arbre est vide
			return "L'arbre est vide";
		}
		else
		{
			return this.premierNoeud.toString();
		}
	}

	// Methode appelee dans LectureFichier pour ajouter chaque stagiaire lu dans le
	// fichier DON au fichier binaire
	public void ajouterStagiaireDeFichierDonAFichierBin(Stagiaire stagiaire) throws IOException 
	{

		// si l'arbre est vide = > si le fichier est vide
		if (premierNoeudCreeOuNon == false) 
		{
			// dans ce cas j'écris le stagiaire au début du fichier
			raf.seek(0);
			// creer le premier noeud dans l'arbre
			this.setPremierNoeud(new NoeudBinaire(stagiaire));
			// ecrire ce noeud dans le fichier binaire
			this.getPremierNoeud().ecrireNoeudDansFichierBin(raf, stagiaire);

			premierNoeudCreeOuNon = true;
		} 
		else 
		{
			// je me met au début du fichier
			raf.seek(0);
			// je lis le premier noeud et je le stocke dans une variable racine
			NoeudBinaire racine = new NoeudBinaire();
			racine = racine.lireNoeudFichierBinVersObjetNoeudBinaire(raf);
			//j'appelle la methode de la classe NoeudBinaire qui va ecrire les noeuds dans le fichier bin
			racine.ajouterNoeudDansFichierBin(stagiaire, raf);
		}

	}


	BufferedReader reader;


	public void lectureFichierDon(ArbreBinaire arbreAnnuaire)
	{

		try 
		{

			if (raf.length() != 0)
			{
				System.out.println("Le fichier est déjà créé");
			}
			else
			{
				System.out.println("Je créé le fichier");
				reader = new BufferedReader(new FileReader(DON_PATH ));

				String line = reader.readLine();

				while (line != null) 
				{
					//System.out.println(line);
					// si c'est la première ligne, alors remplir la variable nom;
					if (compteurLigne == 1) 
					{
						nom = line.toUpperCase(); //on stocke le nom en le mettant en majuscules pour eviter les cas particuliers (noms à particules par ex.)
					}
					// si c'est la deuxième ligne, alors remplir la variable prenom;
					if (compteurLigne == 2) 
					{
						prenom = line;
					}
					// si c'est la troisième ligne, alors remplir la variable dpt;
					if (compteurLigne ==3 ) 
					{
						dpt = line;
					}
					// si c'est la quatrième ligne, alors remplir la variable promo;
					if (compteurLigne == 4) {
						promo = line;
					}
					// si c'est la cinquième ligne, alors remplir la variable annee;
					if (compteurLigne == 5) {
						annee = Integer.valueOf(line);
					}
					if (compteurLigne == 6 && line.equals("*")) {

						Stagiaire stagiaire = new Stagiaire(nom, prenom, dpt, promo, annee);

						// 3)  Reset les variables et le compteur
						compteurLigne = 0;
						compteurStagiaire += 1;

						//Ajouter le stagiaire en tant que noeud dans notre arbre annuaire 
						//et ecrire le fichier bin
						arbreAnnuaire.ajouterStagiaireDeFichierDonAFichierBin(stagiaire);

					}
					compteurLigne += 1;
					line = reader.readLine();
				}
				reader.close();
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

	}



	public int rechercheNoeudASupprimer(Stagiaire stagiaire, int indexNoeudCourant) throws IOException //au debut, index à 0 quand on appelle la methode
	{

		//recuperer le noeud à l'indexPere grace à la methode getNoeud
		NoeudBinaire noeudCourant = getNoeud(indexNoeudCourant) ;

		//Comparer le nom du noeud à supprimer avec le nom de la racines
		//si nom du stagiaire à supprimer compareTo nom du noeud courant  < 0
		if (stagiaire.getNom().compareTo(noeudCourant.getStagiaire().getNom()) < 0)
		{				
			//on va regarder les enfants à gauche
			//si l'indexFilsGauche est = -1, alors le stagiaire à supprimer n'existe pas
			if (noeudCourant.getFilsGauche() == -1)
			{
				System.out.println("Le stagiaire à supprimer n'existe pas");
				calculIndex = -1 ;
				return calculIndex;
			}
			else //il y a un filsGauche
			{					
				//on fait un appel recursif sur la methode pour comparer le stagiaire a supprimer au fils gauche
				rechercheNoeudASupprimer(stagiaire, noeudCourant.getFilsGauche()) ;
			}
			//sinon, si c'est > 0, on part à droite
		}
		else if (stagiaire.getNom().compareTo(noeudCourant.getStagiaire().getNom()) > 0)
		{
			//on fait la meme chose mais à droite 
			//si l'indexFilsDroit est = -1, alors le stagiaire à supprimer n'existe pas
			if (noeudCourant.getFilsDroit() == -1)
			{
				System.out.println("Le stagiaire à supprimer n'existe pas");
				calculIndex = -1 ;
				return calculIndex;
			}
			else //il y a un filsDroit
			{
				//on fait un appel recursif sur la methode pour comparer le stagiaire a supprimer au fils droit
				rechercheNoeudASupprimer(stagiaire, noeudCourant.getFilsDroit()) ;
			}
		}
		else if (stagiaire.getNom().compareTo(noeudCourant.getStagiaire().getNom()) == 0)
		{
			//Sinon, si c'est = 0 , on a trouvé le bon noeud
			//donc on return l'index du noeud

			calculIndex = (int) (raf.getFilePointer() - NoeudBinaire.TAILLE_MAX_NOEUD) ;

			calculIndex = calculIndex / NoeudBinaire.TAILLE_MAX_NOEUD ;

		}
		return calculIndex ;
	}

	public int rechercheIndexPereDuNoeudASupprimer(int indexNoeudCourant, int indexNoeudASupprimer) throws IOException //au debut, index à 0 quand on appelle la methode
	{

		//recuperer le noeud à l'indexPere grace à la methode getNoeud
		NoeudBinaire noeudCourant = getNoeud(indexNoeudCourant) ;

		//si l'index du noeud a supprimer est egal au fils gauche ou fils droit du noeud courant, alors on a trouvé le pere du noeud a supprimer
		if ((indexNoeudASupprimer == noeudCourant.getFilsGauche()) || indexNoeudASupprimer == noeudCourant.getFilsDroit())
		{
			//on calcule et retourne l'indexPere
			indexPere = (int) (raf.getFilePointer() - NoeudBinaire.TAILLE_MAX_NOEUD) ;
			indexPere = indexPere / NoeudBinaire.TAILLE_MAX_NOEUD ;
			return indexPere;
		}
		else
		{
			//sinon, on fait un appel recursif à gauche si le noeud courant a des enfants à gauche
			if (noeudCourant.getFilsGauche() != -1)
			{
				rechercheIndexPereDuNoeudASupprimer(noeudCourant.getFilsGauche() , indexNoeudASupprimer) ;
			}

			//Meme chose sur le sous arbre droit
			if (noeudCourant.getFilsDroit() != -1)
			{
				rechercheIndexPereDuNoeudASupprimer(noeudCourant.getFilsDroit() , indexNoeudASupprimer) ;
			}
		}
		//Si cette methode return 0, alors on veut supprimer la racine
		return indexPere ;
	}

	//Methode supprimer(Stagiaire)
	public void supprimerStagiaire(Stagiaire stagiaire, RandomAccessFile raf) throws IOException
	{
		//Appel rechercheNoeudASupprimer(Stagiaire stagiaire, 0)
		int indexNoeudASupprimer = rechercheNoeudASupprimer(stagiaire, 0) ;
		//On recupere le noeud à l'index retourné par la methode rechercheNoeud
		NoeudBinaire noeudASupprimer = getNoeud(indexNoeudASupprimer) ;
		//Appel nbDescendants(noeudASupprimer)
		int nbDescendants = nbDescendants(noeudASupprimer) ;
		//selon le nombre, methode de suppression differente
		if (nbDescendants == 0 )
		{
			//Si nbDescendants = 0 : 
			//on recupere l'index pere
			indexPere = rechercheIndexPereDuNoeudASupprimer(0, indexNoeudASupprimer) ;
			NoeudBinaire noeudPere = getNoeud(indexPere) ;
			//On verifie si c'est le fils gauche ou le fils droit du pere qu'on doit supprimer
			if (noeudPere.getFilsGauche() == indexNoeudASupprimer)
			{
				//faire pointer l'index du pere vers -1 :
				//on place le curseur du raf a la position de l'index du fils gauche dans le noeud pere du noeud a supprimer
				raf.seek((indexPere * NoeudBinaire.TAILLE_MAX_NOEUD) + (Stagiaire.TAILLE_OCTET_STAGIAIRE));
				raf.writeInt(-1);
			}
			else if (noeudPere.getFilsDroit() == indexNoeudASupprimer)
			{
				//faire pointer l'index du pere vers -1 :
				//on place le curseur du raf a la position de l'index du fils droit dans le noeud pere du noeud a supprimer
				raf.seek((indexPere * NoeudBinaire.TAILLE_MAX_NOEUD) + (Stagiaire.TAILLE_OCTET_STAGIAIRE + NoeudBinaire.TAILLE_MAX_FILS));
				raf.write(-1);
			}
		}
		else if (nbDescendants == 1 )
		{
			//Si nbDescendants = 1 : il y a 1 fils
			//On récupère l'index du père
			indexPere = rechercheIndexPereDuNoeudASupprimer(0, indexNoeudASupprimer) ;
			NoeudBinaire noeudPere = getNoeud(indexPere) ;
			//On récupère l'index du fils (index qui n'est pas égal à -1)
			if (noeudASupprimer.getFilsGauche() != -1)
			{
				indexFils = noeudASupprimer.getFilsGauche();
			}
			else
			{
				indexFils = noeudASupprimer.getFilsDroit();
			}
			//faire pointer l'index du pere vers l'index du fils qu'on a récupéré
			if (noeudPere.getFilsGauche() == indexNoeudASupprimer)
			{
				raf.seek((indexPere * NoeudBinaire.TAILLE_MAX_NOEUD) + (Stagiaire.TAILLE_OCTET_STAGIAIRE));
				raf.writeInt(indexFils);
			}
			else if (noeudPere.getFilsDroit() == indexNoeudASupprimer)
			{
				raf.seek((indexPere * NoeudBinaire.TAILLE_MAX_NOEUD) + (Stagiaire.TAILLE_OCTET_STAGIAIRE + NoeudBinaire.TAILLE_MAX_FILS));
				raf.writeInt(indexFils);
			}
		}

		else if (nbDescendants == 2 ) //Si nbDescendants = 2 : 2 fils
		{
			// Définir le successeur de noeudASupprimer lors du parcours infixe de l'arbre (GND), 
			//c'est-à-dire le noeud suivant successeur qui a donc la plus petite clé supérieure à celle de noeudASupprimer
			NoeudBinaire noeudSuccesseur = rechercheSuccesseur(noeudASupprimer) ;

			//Supprimer le noeudSuccesseur de son emplacement d'origine qui correspond à un des 2 premiers cas possibles de suppression 
			//(suppression d'une feuille ou d'un noeud avec un fils)
			supprimerStagiaire(noeudSuccesseur.getStagiaire(), raf);

			//Remplacer les informations contenues dans le noeudASupprimer par celles du noeudSuccesseur				
			raf.seek(indexNoeudASupprimer * NoeudBinaire.TAILLE_MAX_NOEUD);
			raf.writeChars(NoeudBinaire.getNomLong(noeudSuccesseur.getStagiaire()));
			raf.writeChars(NoeudBinaire.getPrenomLong(noeudSuccesseur.getStagiaire()));
			raf.writeChars(NoeudBinaire.getDepartementLong(noeudSuccesseur.getStagiaire()));
			raf.writeChars(NoeudBinaire.getPromoLong(noeudSuccesseur.getStagiaire()));
			raf.writeInt(noeudSuccesseur.getStagiaire().getAnnee());
		}

	}

	//Methode nbDescendants() : donne le nombre d'enfants d'un noeud
	public int nbDescendants(NoeudBinaire noeudASupprimer) {
		// Je suis une feuille -> terminaison
		if ((noeudASupprimer.getFilsGauche() == -1) && (noeudASupprimer.getFilsDroit() == -1)) {
			return 0;
		} else if ((noeudASupprimer.getFilsGauche() != -1) && (noeudASupprimer.getFilsDroit() == -1)) {
			// Je n'ai qu'un fils gauche
			return 1;
		} else if ((noeudASupprimer.getFilsGauche() == -1) && (noeudASupprimer.getFilsDroit() != -1)) {
			// Je n'ai qu'un fils droit
			return 1;
		} else { // j'ai deux fils, je garde donc le maximum entre les deux
			return 2;
		}
	}

	public ArrayList<Stagiaire> afficherListe() throws IOException{
		ArrayList<Stagiaire> liste = new ArrayList<>();
		if (raf.length() == 0) {
			return null;
		} else {
			raf.seek(0);
			NoeudBinaire racine = new NoeudBinaire();
				racine =	racine.lireNoeudFichierBinVersObjetNoeudBinaire(raf);
			liste = racine.fichierBinVersArrayList(raf);
		}
		return liste;
	}
	
	
	public NoeudBinaire rechercheSuccesseur(NoeudBinaire noeudASupprimer) throws IOException 
	{
		NoeudBinaire noeudCourant = noeudASupprimer ;
		//On regarde s'il y a un fils à droite
		if (noeudCourant.getFilsDroit() != -1) 
		{
			//on stocke ce fils droit dans le noeud courant
			noeudCourant = getNoeud(noeudASupprimer.getFilsDroit()) ;
			//puis on part tout au bout du sous arbre gauche du fils droit
			while (noeudCourant.getFilsGauche() != -1) 
			{
				//et on stocke dans noeudCourant le fils gauche le plus à gauche de ce sous arbre
				//le noeud successeur est le noeud le plus à gauche du sous-arbre droit de noeudASupprimer, ne possèdant pas de fils gauche
				noeudCourant = getNoeud(noeudCourant.getFilsGauche());
			}
		}
		return noeudCourant;
	}


	public NoeudBinaire getNoeud(int indexNoeud) throws IOException
	{
		//placer le curseur à la bonne position
		raf.seek(indexNoeud * NoeudBinaire.TAILLE_MAX_NOEUD);
		//Je cree un nouveau noeud binaire temporaire pour stocker les informations du noeud courant.
		NoeudBinaire noeudCourant = new NoeudBinaire();	
		//j'utilise la méthode LireNoeud pour traduire les informations du noeud, du binaire, vers le noeud courant provisoire.
		noeudCourant = noeudCourant.lireNoeudFichierBinVersObjetNoeudBinaire(raf);

		return noeudCourant ;
	}
	//Methode pour modifier le nom d'un stagiaire depuis le front
		public void modifierNom(Stagiaire stagiaire, String newNom, RandomAccessFile raf) throws IOException
		{
	 //1ère étape : on récupère le stagiaire
	 Stagiaire ancienStagiaire = new Stagiaire(stagiaire.getNom(), stagiaire.getPrenom(), stagiaire.getDepartement(), stagiaire.getPromotion(), stagiaire.getAnnee());
	 System.out.println(ancienStagiaire);
	 //2ème étape : on créé un nouveauStagiaire avec le nouveau nom souhaité
	 Stagiaire nouveauStagiaire = new Stagiaire(newNom, stagiaire.getPrenom(), stagiaire.getDepartement(), stagiaire.getPromotion(), stagiaire.getAnnee());
	// nouveauStagiaire.setNom(NoeudBinaire.getNomLong(nouveauStagiaire));
	 System.out.println(nouveauStagiaire);
	 //3ème étape : on applique la méthode de suppression au noeud concerné
	 supprimerStagiaire(ancienStagiaire,raf);
	 //4ème étape : on ajoute le stagiaire avec le nouveauNom dans le fichier bin en tant que noeudBinaire avec la méthode ajouterNoeud.
	 ajouterStagiaireDeFichierDonAFichierBin(nouveauStagiaire);
		}

		// Methode pour modifier le prenom d'un stagiaire depuis le front
		public void modifierPrenom(Stagiaire stagiaire, String newPrenom, RandomAccessFile raf) throws IOException
		{
			//Je récupère le nouveau prenom du stagiaire et je lui impose la méthode PrenomLong pour le formater selon la taille max dans le fichier binaire
			stagiaire.prenom = newPrenom;
			newPrenom = NoeudBinaire.getPrenomLong(stagiaire);
			//Je cherche le noeud contenant le stagiaire dans le fichier binaire
			int indexNoeudAModifier = rechercheNoeudASupprimer(stagiaire, 0) ;
			//Je place le curseur à l'endroit dans le noeud où est stocké le prenom du stagiaire
			raf.seek((indexNoeudAModifier * NoeudBinaire.TAILLE_MAX_NOEUD) + (Stagiaire.TAILLE_OCTET_NOM));
			//Je remplace le prenom actuel en écrivant le nouveau prenom donné en argument
			raf.writeChars(newPrenom);
		}


		// Methode pour modifier le Departement d'un stagiaire depuis le front
		public void modifierDepartement(Stagiaire stagiaire, String newDepartement, RandomAccessFile raf) throws IOException
		{
			//Je récupère le nouveau departement du stagiaire et je lui impose la méthode DepartementLong pour le formater selon la taille max dans le fichier binaire
			stagiaire.departement = newDepartement;
			newDepartement = NoeudBinaire.getDepartementLong(stagiaire);
			//Je cherche le noeud contenant le stagiaire dans le fichier binaire
			int indexNoeudAModifier = rechercheNoeudASupprimer(stagiaire, 0) ;
			//Je place le curseur à l'endroit dans le noeud où est stocké le prenom du stagiaire
			raf.seek((indexNoeudAModifier * NoeudBinaire.TAILLE_MAX_NOEUD) + (Stagiaire.TAILLE_OCTET_NOM*2));
			//Je remplace le departement actuel en écrivant le nouveau departement donné en argument
			raf.writeChars(newDepartement);
		}

		// Methode pour modifier la Promotion d'un stagiaire depuis le front
		public void modifierPromotion(Stagiaire stagiaire, String newPromotion, RandomAccessFile raf) throws IOException
		{
			//Je récupère la nouvelle promotion du stagiaire et je lui impose la méthode PromotionLong pour le formater selon la taille max dans le fichier binaire
			stagiaire.promotion = newPromotion;
			newPromotion = NoeudBinaire.getPromoLong(stagiaire);
			//Je cherche le noeud contenant le stagiaire dans le fichier binaire
			int indexNoeudAModifier = rechercheNoeudASupprimer(stagiaire, 0) ;
			//Je place le curseur à l'endroit dans le noeud où est stocké le prenom du stagiaire
			raf.seek((indexNoeudAModifier * NoeudBinaire.TAILLE_MAX_NOEUD) + (Stagiaire.TAILLE_OCTET_NOM*2) + (Stagiaire.TAILLE_OCTET_DPT));
			//Je remplace la promotion actuelle en écrivant la nouvelle donné en argument
			raf.writeChars(newPromotion);
		}

		// Methode pour modifier l'année d'un stagiaire depuis le front
		public void modifierAnnee(Stagiaire stagiaire, int newAnnee, RandomAccessFile raf) throws IOException
		{
			//Je cherche le noeud contenant le stagiaire dans le fichier binaire
			int indexNoeudAModifier = rechercheNoeudASupprimer(stagiaire, 0) ;
			//Je place le curseur à l'endroit dans le noeud où est stocké le prenom du stagiaire
			raf.seek((indexNoeudAModifier * NoeudBinaire.TAILLE_MAX_NOEUD) + (Stagiaire.TAILLE_OCTET_NOM*2) + (Stagiaire.TAILLE_OCTET_DPT)+(Stagiaire.TAILLE_OCTET_PROMO));
			//Je remplace l'année actuelle en écrivant la nouvelle donné en argument
			raf.writeInt(newAnnee);
		}


		//methode test d'affichage console  
		public void testLecture() throws IOException 
		{
			int nbNoeud = (int)raf.length()/NoeudBinaire.TAILLE_MAX_NOEUD;
			NoeudBinaire noeud = new NoeudBinaire();
			System.out.println("il y a " + nbNoeud + " noeuds") ;
			raf.seek(0);
			for(int i =0; i < nbNoeud; i++) {
				NoeudBinaire courant = noeud.lireNoeudFichierBinVersObjetNoeudBinaire(raf);
				System.out.println(courant);
			}
		}
	}


