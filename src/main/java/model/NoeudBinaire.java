package model;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * NoeudBinaire est la classe qui définit l'objet Noeud Binaire utilisé par la classe ArbreBinaire dans le cas d'ajout d'objets "Stagiaire"
 * au sein d'un fichier binaire stockant un arbre binaire. 
 * Un NoeudBinaire possède ainsi : un objet (ici un Stagiaire) issu d'une autre classe, plus des index "FilsGauche" et "FilsDroit" situant les places des Noeuds Binaires dans l'arbre Binaire du fichier binaire.
 * Cette classe est caractérisée par les informations suivantes :
 * <ul>
 * <li>Les attributs de le classe :</li>
 * <li>L'attribut stagiaire permets de récupérer l'objet et ses attributs afin de les ajouter au Noeud Binaire</li>
 * <li>L'attribut filsGauche indique l'emplacement (index) du fils gauche sur le fichier binaire.</li>
 * <li>L'attribut filsGauche indique l'emplacement (index) du fils gauche sur le fichier binaire.</li>
 * 
 * <li>Les constantes de classe :</li>
 * <li>Les constantes TAILLE_MAX_FILS et TAILLE_MAX_NOEUD fixent la taille maximum que peut utiliser un Noeud Binaire dsur le fichier binaire. Ces constantes réfèrent des tailles en octet à partir de constantes issues de la classe Objet Stagiaire. </li>
 * </ul>
 * Description des principales fonctionnalités de la classe :
 *  - La méthode lireNoeudFichierBinVersObjetNoeudBinaire(RandomAccessFile raf), permets, à l'aide de la classe JAVA RandomAccessFile, de lire un Noeud Binaire depuis un fichier binaire et de le traduire en UTF_8.
 *  - La méthode ecrireNoeudDansFichierBin(RandomAccessFile raf, Stagiaire stagiaire), permets l'écriture des Noeuds dans le fichier binaire.
 *  - Les méthodes getNomLong, getPrenomLong, getDepartementLong, getPromoLong et getAnneeLong, permettent de formater les informations de chaque attribut de l'objet stagiaire en ajoutant des espaces ou en tronquant des caractères de manière à ce qu'ils soient adaptés à l'ajout dans le fichier binaire via les constantes TAILLE_MAX.
 *  - La méthode fichierBinVersArrayList(RandomAccessFile raf) permets de récupérer les Noeuds Binaires présents sur le fichier binaire en une ArrayList d'objets Stagiaires, classée par ordre alphabétique en fonction des attributs "nom" de chaque Stagiaire, utilisable pour l'affichage sur le front.
 * </p>
 * Les attributs statiques :
 * L'attribut : "estDejaPasse" , est de type boolean et est utilisé dans la méthode fichierBinVersArrayList pour gérer le curseur du RandomAccessFile.
 * </p>
 * @author Deltombe Floriane
 * @author Difilippo Gabriel
 * @version 1
 */
public class NoeudBinaire {
	
	//attributs
	
	/** 
	 * L'attribut stagiaire permets de récupérer l'objet de type Stagiaire et ses attributs depuis sa classe dans le but de les ajouter à l'objet Noeud Binaire.
	 */
	private Stagiaire stagiaire;
	 /** 
     * L'attribut filsGauche est un entier qui détermine l'index d'un autre Noeud Binaire dans le fichier binaire. Dans la constitution de l'arbre binaire, cet index pointe vers le fils gauche.
     */
	private int filsGauche;
	   /** 
     * L'attribut filsDroit est un entier qui détermine l'index d'un autre Noeud Binaire dans le fichier binaire. Dans la constitution de l'arbre binaire, cet index pointe vers le fils droit.
     */ 
	private int filsDroit;
	
	/** 
	 * L'attribut static : "estDejaPasse" , est de type boolean et est utilisé dans la méthode fichierBinVersArrayList pour gérer le curseur du RandomAccessFile.
	 */
	private static boolean estDejaPasse = false;
	
	//constantes pour le fichier binaire
	public final static int TAILLE_MAX_FILS = 4 ; 
	public final static int TAILLE_MAX_NOEUD = Stagiaire.TAILLE_OCTET_STAGIAIRE + 2 * TAILLE_MAX_FILS ;
	
	//Contructeur
	/** 
     * <b>Constructeur surchargé de NoeudBinaire</b> 
     *  
     * Construction d'un objet NoeudBinaire à partir d'un objet de type Stagiaire.
     * 
     * @param stagiaire 
     *     L'attribut stagiaire permets de récupérer l'objet de type Stagiaire et ses attributs depuis sa classe dans le but de les ajouter à l'objet NoeudBinaire.
     * @param filsGauche 
     *     L'attribut filsGauche est un entier qui détermine l'index d'un autre Noeud Binaire dans le fichier binaire. Dans la constitution de l'arbre binaire, cet index pointe vers le fils gauche.
     * @param filsDroit
     * 	   L'attribut filsDroit est un entier qui détermine l'index d'un autre Noeud Binaire dans le fichier binaire. Dans la constitution de l'arbre binaire, cet index pointe vers le fils droit.
     */ 
	public NoeudBinaire(Stagiaire stagiaire, int filsGauche, int filsDroit) {
		
		this.stagiaire = stagiaire;
		this.filsGauche = filsGauche;
		this.filsDroit = filsDroit;
	}
	/** 
     * <b>Constructeur de NoeudBinaire</b> 
     *  
     * Construction d'un objet NoeudBinaire à partir d'un objet de type Stagiaire.
     * 
     * @param stagiaire 
     *     L'attribut stagiaire permets de récupérer l'objet de type Stagiaire et ses attributs depuis sa classe dans le but de les ajouter à l'objet NoeudBinaire.
     * @param filsGauche 
     *     L'attribut filsGauche est initié à -1 avec ce constructeur.
     * @param filsDroit
     * 	   L'attribut filsDroit est initié à -1 avec ce constructeur.
     */ 
	public NoeudBinaire(Stagiaire stagiaire) {
			
			this.stagiaire = stagiaire;
			this.filsGauche = -1;
			this.filsDroit = -1;
		}


	public NoeudBinaire() {
	}

	//Getters & Setters
	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public int getFilsGauche() {
		return filsGauche;
	}

	public void setFilsGauche(int filsGauche) {
		this.filsGauche = filsGauche;
	}

	public int getFilsDroit() {
		return filsDroit;
	}

	public void setFilsDroit(int filsDroit) {
		this.filsDroit = filsDroit;
	}
	/**
	 * La toString permets d'afficher dans une String tous les attributs de l'objet Stagiaire
	 */
	@Override
	public String toString() {
		return "NoeudBinaire [stagiaire=" + stagiaire + ", filsGauche=" + filsGauche + ", filsDroit=" + filsDroit + "]";
	}
	/**
	 * ajouterNoeudDansFichierBin est une méthode appelée lors de l'ajout d'un stagiaire dans un fichier binaire en tant que NoeudBinaire. Dans le processus d'ajout, cette méthode fonctionne de manière récursive et permets de positionner le curseur du RandomAccessFile à l'endroit voulu et de modifier les index fils droits et fils gauches des Noeuds déjà placés dans le fichier binaire.
	 * La méthode appelle deux autres méthodes : ecrireNoeudDansFichierBin et lireNoeudFichierBinVersObjetNoeudBinaire.
	 * @param stagiaire
	 * Donne en paramètre l'objet Stagiaire à ajouter au fichier binaire.
	 * @param raf
	 * Donne en paramètre le RandomAccessFile utilisé pour la lecture et l'écriture du fichier binaire.
	 * @throws IOException
	 * Possède une propagation de l'exception IOException liée au curseur du RandomAccessFile.
	 */
	public void ajouterNoeudDansFichierBin(Stagiaire stagiaire, RandomAccessFile raf) throws IOException 
	{
		if (stagiaire.getNom().compareTo(this.stagiaire.getNom()) < 0) //compare les 2 string dans l'ordre alphabetique 
												  						//et s'arrete des qu'il y a une difference
		{
			//je pars a gauche
			//je regarde si j'ai la place d'inserer a gauche (si c'est egal à -1, il n'y a pas de fils)
			if (filsGauche == -1)
			{
				//il y a la place : j'ajoute le nouveau Noeud
				//je remonte mon curseur de -8 ( 2 * 4 octets = les 2 fils)
				raf.seek((raf.getFilePointer() - (TAILLE_MAX_FILS * 2)));
				//j'écris l'index du fils cad du stagiaire
				raf.writeInt((int)raf.length() / TAILLE_MAX_NOEUD);
				//j'écris le nouveau noeud Noeud
				ecrireNoeudDansFichierBin(raf, stagiaire) ;
			}
			else
			{
				//pas de place, il y a deja un fils, je fais un appel recursif sur le noeud suivant
				//je met le curseur à la position fils gauche
				raf.seek(this.filsGauche * TAILLE_MAX_NOEUD);
				//je lis le noeud et je le stocke dans une variable : 
				//creation d'un noeud vide
				NoeudBinaire noeudGaucheBinaire = new NoeudBinaire() ;
				//lecture du noeud du fils gauche dans le noeud vide
				noeudGaucheBinaire = lireNoeudFichierBinVersObjetNoeudBinaire(raf) ;
				//appel recursif de la methode sur ce noeud
				noeudGaucheBinaire.ajouterNoeudDansFichierBin(stagiaire, raf) ;
			}
		}
		else
		{
			//meme logique à droite mais on remonte de -4 (un seul fils)
			//je pars a droite
			//je regarde si j'ai la place d'inserer a droite
			if (filsDroit == -1 )
			{
				//je remonte mon curseur de - 4
				raf.seek((raf.getFilePointer() - TAILLE_MAX_FILS));
				//j'écris l'index du fils cad du stagiaire
				raf.writeInt((int)raf.length()/TAILLE_MAX_NOEUD);
				//j'écris le nouveau noeud 
				ecrireNoeudDansFichierBin(raf, stagiaire) ;
			}
			else
			{
				//pas de place : je fais un appel recursif sur le noeud suivant
				//je met le curseur à la position fils gauche
				raf.seek(this.filsDroit * TAILLE_MAX_NOEUD);
				//je lis le noeud et je le stocke dans une variable
				NoeudBinaire noeudDroitBinaire = new NoeudBinaire() ;
				noeudDroitBinaire = lireNoeudFichierBinVersObjetNoeudBinaire(raf) ;
				
				noeudDroitBinaire.ajouterNoeudDansFichierBin(stagiaire, raf) ;
			}	
		}
	}
	
	/**
	 * lireNoeudFichierBinVersObjetNoeudBinaire est une méthode qui permets de lire des informations contenues dans un fichier binaire, de les traduire en UTF-8 depuis le language binaire et de le retourner en tant qu'objet NoeudBinaire.
	 * @param raf
	 * Donne en paramètre le RandomAccessFile utilisé pour la lecture et l'écriture du fichier binaire.
	 * @return les informations lues sur le fichier binaire en tant qu'objet NoeudBinaire
	 * @throws IOException
	 * Possède une propagation de l'exception IOException liée au curseur du RandomAccessFile.
	 */
	public NoeudBinaire lireNoeudFichierBinVersObjetNoeudBinaire(RandomAccessFile raf) throws IOException
	{
		
		String nom = "" ; 
		String prenom = "" ; 
		String dpt = "" ;
		String promo = "" ;
		int annee = 0 ;
		
		//on utilise TAILLE_MAX_NOM car readChar() lit deja 2 octets
		for (int i = 0 ; i < Stagiaire.TAILLE_MAX_NOM  ; i++)
		{
			nom += raf.readChar() ;
		}
		
		
		for (int i = 0 ; i < Stagiaire.TAILLE_MAX_PRENOM  ; i++)
		{
			prenom += raf.readChar() ;
		}
		
		
		for (int i = 0 ; i < Stagiaire.TAILLE_MAX_DPT ; i++)
		{
			dpt += raf.readChar() ;
		}
		
		
		for (int i = 0 ; i < Stagiaire.TAILLE_MAX_PROMO ; i++)
		{
			promo += raf.readChar() ;
		}
		
		annee = raf.readInt();
		//annee = new Integer(promoTab) ;
		NoeudBinaire noeudLu = new NoeudBinaire(new Stagiaire(nom.trim(), prenom.trim(), dpt.trim(), promo.trim(), annee)) ;

		noeudLu.filsGauche = raf.readInt();
		noeudLu.filsDroit = raf.readInt();
		
		return noeudLu;
		
		}
	/**
	 * ecrireNoeudDansFichierBin est une méthode permettant le placement spécifique et l'écriture d'informations contenues dans les attributs, préalablement formatés selon des tailles fixes, d'un objet Stagiaire dans un fichier binaire. L'écriture de deux "-1" successifs sert d'initialisation dans l'indexation des informations pour la construction d'un arbre binaire via le fichier binaire.
	 * Cette méthode appelle les méthodes getNomLong, getPrenomLong, getDepartementLong et getPromoLong, permettant l'ajout ou la suppression de caractères selon des formats précis répondant à une taille maximale de place en octet à occuper dans le fichier binaire.
	 * @param raf
	 * Donne en paramètre le RandomAccessFile utilisé pour la lecture et l'écriture du fichier binaire.
	 * @param stagiaire
	 * Donne en paramètre l'objet Stagiaire à ajouter au fichier binaire.
	 * @throws IOException
	 * Possède une propagation de l'exception IOException liée au curseur du RandomAccessFile.
	 */
	public void ecrireNoeudDansFichierBin(RandomAccessFile raf, Stagiaire stagiaire) throws IOException
	{
		//je me place à la fin du fichier bin
		raf.seek(raf.length());
		//j'ajoute le fils à la fin du fichier 
		raf.writeChars(getNomLong(stagiaire));
		raf.writeChars(getPrenomLong(stagiaire));
		raf.writeChars(getDepartementLong(stagiaire));
		raf.writeChars(getPromoLong(stagiaire));
		raf.writeInt(stagiaire.getAnnee());
		raf.writeInt(-1);
		raf.writeInt(-1);
	}
	
	/**
	 * getNomLong est une méthode qui autorise l'ajout de caractères "espace" ou la suppression de caractères selon un format précis répondant à une taille maximale de place en octet à occuper dans le fichier binaire pour l'attribut "nom" de l'objet Stagiaire instancié.
	 * @param stagiaire
	 * Donne en paramètre l'objet Stagiaire à ajouter au fichier binaire.
	 * @return la version formatée du nom.
	 * 
	 */
	public static String getNomLong(Stagiaire stagiaire)
	{
		String nomLong = "" ; 
		
		String nomStagiaire = stagiaire.getNom() ;
		
		if (nomStagiaire.length() <= Stagiaire.TAILLE_MAX_NOM)
		{
			nomLong = nomStagiaire ; 
			
			for ( int i = nomStagiaire.length() ; i < Stagiaire.TAILLE_MAX_NOM ; i++ )
			{
				//On ajoute des etoiles au nom si le nom est en dessous de la taille max
				nomLong += " " ;
			}
		}
		else 
		{
			nomLong  = nomStagiaire.substring(0, Stagiaire.TAILLE_MAX_NOM) ;
		}
		
		
		return nomLong ;
	}
	
	/**
	 * getPrenomLong est une méthode qui autorise l'ajout de caractères "espace" ou la suppression de caractères selon un format précis répondant à une taille maximale de place en octet à occuper dans le fichier binaire pour l'attribut "prenom" de l'objet Stagiaire instancié.
	 * @param stagiaire
	 * Donne en paramètre l'objet Stagiaire à ajouter au fichier binaire.
	 * @return la version formatée du prenom.
	 * 
	 */
	public static String getPrenomLong(Stagiaire stagiaire)
	{
		String prenomLong = "" ; 
		
		String prenomStagiaire = stagiaire.getPrenom() ;
		
		if (prenomStagiaire.length() <= Stagiaire.TAILLE_MAX_PRENOM)
		{
			prenomLong = prenomStagiaire ; 
			
			for ( int i = prenomStagiaire.length() ; i < Stagiaire.TAILLE_MAX_PRENOM ; i++ )
			{
				//On ajoute des espaces au nom si le nom est en dessous de la taille max
				prenomLong += " " ;
			}
		}
		else 
		{
			prenomLong  = prenomStagiaire.substring(0, Stagiaire.TAILLE_MAX_PRENOM) ;
		}
		
		
		return prenomLong ;
	}
	
	/**
	 * getDepartementLong est une méthode qui autorise l'ajout de caractères "espace" ou la suppression de caractères selon un format précis répondant à une taille maximale de place en octet à occuper dans le fichier binaire pour l'attribut "nom" de l'objet Stagiaire instancié.
	 * @param stagiaire
	 * Donne en paramètre l'objet Stagiaire à ajouter au fichier binaire.
	 * @return la version formatée du département.
	 * 
	 */
	public static String getDepartementLong(Stagiaire stagiaire)
	{
		String dptLong = "" ; 
		
		String dptStagiaire = stagiaire.getDepartement() ;
		
		if (dptStagiaire.length() <= Stagiaire.TAILLE_MAX_DPT)
		{
			dptLong = dptStagiaire ; 
			
			for ( int i = dptStagiaire.length() ; i < Stagiaire.TAILLE_MAX_DPT ; i++ )
			{
				//On ajoute des espaces au nom si le nom est en dessous de la taille max
				dptLong += " " ;
			}
		}
		else 
		{
			dptLong  = dptStagiaire.substring(0, Stagiaire.TAILLE_MAX_DPT) ;
		}
		
		return dptLong ;
	}
	
	
	/**
	 * getPromoLong est une méthode qui autorise l'ajout de caractères "espace" ou la suppression de caractères selon un format précis répondant à une taille maximale de place en octet à occuper dans le fichier binaire pour l'attribut "promotion" de l'objet Stagiaire instancié.
	 * @param stagiaire
	 * Donne en paramètre l'objet Stagiaire à ajouter au fichier binaire.
	 * @return la version formatée de la promo.
	 * 
	 */
	public static String getPromoLong(Stagiaire stagiaire)
	{
		String promoLong = "" ; 
		String promoStagiaire = stagiaire.getPromotion() ;
		
		if (promoStagiaire.length() <= Stagiaire.TAILLE_MAX_PROMO)
		{
			promoLong = promoStagiaire ; 
			
			for ( int i = promoStagiaire.length() ; i < Stagiaire.TAILLE_MAX_PROMO ; i++ )
			{
				//On ajoute des espaces au nom si le nom est en dessous de la taille max
				promoLong += " " ;
			}
		}
		else 
		{
			promoLong  = promoStagiaire.substring(0, Stagiaire.TAILLE_MAX_PROMO) ;
		}
		
		return promoLong ;
	}
	
	//Methode pour envoyer les informations du fichier binaire vers une arrayList transposable vers l'observableList (front)
	/**
	 * fichierBinVersArrayList est une methode permettant de récupérer les informations du fichier binaire pour les mettre en forme sur une arrayList transposable vers une observableList afin de les afficher sur le front.
	 * @param raf
	 * Donne en paramètre l'objet Stagiaire à ajouter au fichier binaire.
	 * @return une liste d'objets Stagiaires comprenant tous les attributs.
	 * @throws IOException
	 * Possède une propagation de l'exception IOException liée au curseur du RandomAccessFile.
	 */
	public ArrayList<Stagiaire> fichierBinVersArrayList(RandomAccessFile raf) throws IOException 
	{
		//Je cree une nouvelle Arraylist de Noeud Binaires
		ArrayList<Stagiaire> listeAffichageFichierBin = new ArrayList<Stagiaire>();
		
		// Si c'est la première fois que la méthode se lance, on remet le curseur à 0 dans le fichier binaire
		if (estDejaPasse == false)
		{
			raf.seek(0);	
			estDejaPasse = true;
			
		}
		//Je cree un nouveau noeud binaire temporaire pour stocker les informations du noeud courant.
		NoeudBinaire noeudCourant = new NoeudBinaire();	
		//j'utilise la méthode LireNoeud pour traduire les informations du noeud, du binaire, vers le noeud courant provisoire.
		noeudCourant = lireNoeudFichierBinVersObjetNoeudBinaire(raf);
		
		//maintenant, il faut lire les fils gauche pour récupérer les noeuds par ordre alphabétique.
		//regle de lecture en parcours infixe GND -> ordre alphabetique
		if (noeudCourant.filsGauche !=-1)
		{
			//Je place le curseur au début du noeud filsGauche grâce à l'index récupéré sur le noeud courant
			raf.seek(TAILLE_MAX_NOEUD * noeudCourant.filsGauche);
			//Je cree un noeud temporaire qui va stocker les informations du filsGauche
			NoeudBinaire noeudFilsGauche = new NoeudBinaire();
			//Je lis et stocke les informations du fils Gauche
			noeudFilsGauche = lireNoeudFichierBinVersObjetNoeudBinaire(raf);
			//Je replace le curseur au début du noeud fils gauche.
			raf.seek(TAILLE_MAX_NOEUD * noeudCourant.filsGauche);
			//J'ajoute le noeud fils gauche récupéré à l'arrayList et initie la récursivité pour les fils Gauches du Noeud fils Gauche.
			listeAffichageFichierBin.addAll(noeudFilsGauche.fichierBinVersArrayList(raf));
			
		}
		
		//Selon le parcours GND, j'ajoute maintenant le noeud courant à l'arrayList.
		listeAffichageFichierBin.add(noeudCourant.getStagiaire()) ; //N
		
		//Puis je refais la même chose pour les fils Droits.
		if (noeudCourant.filsDroit != -1)
		{
		
			raf.seek(TAILLE_MAX_NOEUD* noeudCourant.filsDroit); //D
			NoeudBinaire noeudFilsDroit = new NoeudBinaire();
			noeudFilsDroit = lireNoeudFichierBinVersObjetNoeudBinaire(raf);
			raf.seek(TAILLE_MAX_NOEUD* noeudCourant.filsDroit);
			listeAffichageFichierBin.addAll(noeudFilsDroit.fichierBinVersArrayList(raf));
			
			
		}
		return listeAffichageFichierBin;
	}
	
}