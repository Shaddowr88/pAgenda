package model;

public class Noeud extends ArbreBinaire {
	
	//attributs
	private Stagiaire stagiaire;
	private Noeud filsGauche;
<<<<<<< HEAD:src/main/java/cda26/projet1/agenda/Noeud.java
	private Noeud filsDroit;
	
	public final static int TAILLE_MAX_FILS = 4 ; //pour le fichier binaire
	public final static int TAILLE_MAX_NOEUD = Stagiaire.TAILLE_OCTET_STAGIAIRE + 2 * TAILLE_MAX_FILS ;
=======
	private Noeud filsDroit;	
>>>>>>> 9826d86 ( Recherche implémenter mais pas encore visible et ajout d'une transition):src/main/java/model/Noeud.java
	
	//Contructeur
	public Noeud(Stagiaire stagiaire, Noeud filsGauche, Noeud filsDroit) {
		super();
		this.stagiaire = stagiaire;
		this.filsGauche = filsGauche;
		this.filsDroit = filsDroit;
	}

	public Noeud(Noeud premierNoeud) {
		super(premierNoeud);
		
	}

	public Noeud(Stagiaire stagiaire) {
			
			this.stagiaire = stagiaire;
			this.filsGauche = null;
			this.filsDroit = null;
		}

	//Getters & Setters
	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public Noeud getFilsGauche() {
		return filsGauche;
	}

	public void setFilsGauche(Noeud filsGauche) {
		this.filsGauche = filsGauche;
	}

	public Noeud getFilsDroit() {
		return filsDroit;
	}

	public void setFilsDroit(Noeud filsDroit) {
		this.filsDroit = filsDroit;
	}
	
	
	@Override
	public String toString() 
	{
		String resultat = "" ; 
		
		//regle de lecture en parcours infixe GND -> ordre alphabetique
		if (this.filsGauche != null)
		{
			resultat += this.filsGauche.toString() ; //G
		}
		
		resultat += " " + this.stagiaire.getNom() ; //N
		
		if (this.filsDroit != null)
		{
			resultat += this.filsDroit.toString() ; //D
		}
		
		return resultat;
	}

	public void ajouterNoeud(Stagiaire stagiaire) 
	{
		if (stagiaire.getNom().compareTo(this.stagiaire.getNom()) < 0) //compare les 2 string dans l'ordre alphabetique 
												  						//et s'arrete des qu'il y a une difference
		{
			//je pars a gauche
			//je regarde si j'ai la place d'inserer a gauche
			if (filsGauche == null)
			{
				//il y a la place : j'ajoute le nouveau Noeud
				this.filsGauche = new Noeud(stagiaire) ;
			}
			else
			{
				//pas de place : je fais un appel recursif sur le noeud suivant
				this.filsGauche.ajouterNoeud(stagiaire); //je demande au fils gauche de s'en occuper
			}
		}
		else
		{
			//je pars a droite
			//je regarde si j'ai la place d'inserer a droite
			if (filsDroit == null)
			{
				//il y a la place : j'ajoute le nouveau Noeud
				this.filsDroit = new Noeud(stagiaire) ;
			}
			else
			{
				//pas de place : je fais un appel recursif sur le noeud suivant
				this.filsDroit.ajouterNoeud(stagiaire); //je demande au fils gauche de s'en occuper
			}	
		}
	}
	
	public int taille() 
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
	}
	

}
