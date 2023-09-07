package model;
import java.io.IOException;
import java.io.RandomAccessFile;


public class NoeudBinaire {
	
	//attributs
	private Stagiaire stagiaire;
	private int filsGauche;
	private int filsDroit;
	
	//constantes pour le fichier binaire
	public final static int TAILLE_MAX_FILS = 4 ; 
	public final static int TAILLE_MAX_NOEUD = Stagiaire.TAILLE_OCTET_STAGIAIRE + 2 * TAILLE_MAX_FILS ;
	
	//Contructeur
	public NoeudBinaire(Stagiaire stagiaire, int filsGauche, int filsDroit) {
		
		this.stagiaire = stagiaire;
		this.filsGauche = filsGauche;
		this.filsDroit = filsDroit;
	}
	
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
	
	@Override
	public String toString() {
		return "NoeudBinaire [stagiaire=" + stagiaire + ", filsGauche=" + filsGauche + ", filsDroit=" + filsDroit + "]";
	}

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
				raf.seek((raf.getFilePointer() - TAILLE_MAX_NOEUD));
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
	
	
	/*public int taille() 
	{
		int resultat = 0 ; 
		
		//regle parcours infixe GND
		if (this.filsGauche != null)
		{
			resultat += this.filsGauche.taille() ; //G
		}
		
		resultat += 1 ; //N
		
		if (this.filsDroit != null)
		{
			resultat += this.filsDroit.taille() ; //D
		}
		
		return resultat;
	}
	
	
	
	public int hauteur()
	{
		//je suis une feuille -> cas de terminaison
		if (this.filsGauche == null && this.filsDroit == null)
		{
			return 0 ;
		}
		//je n'ai qu'un fils gauche
		else if (filsDroit == null && this.filsGauche != null)
		{
			return 1 + this.filsGauche.hauteur() ;
		}
		//je n'ai qu'un fils droit
		else if (filsGauche == null && this.filsDroit != null)
		{
			return 1 + this.filsDroit.hauteur() ;
		}
		//j'ai deux fils, je garde le maximum entre les deux
		else
		{
			return 1 + Math.max(this.filsGauche.hauteur() , this.filsDroit.hauteur()); 
		}
	}*/
	

}