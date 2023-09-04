package cda26.projet1.agenda;

public class ArbreBinaire {
	//attributs
	
	private Noeud premierNoeud;
	
	//contructeur

	public ArbreBinaire(Noeud premierNoeud) {
		
		this.premierNoeud = premierNoeud;
	}

	public ArbreBinaire() { //constructeur vide pour appel dans classe LectureFichier
		
	}

	//Getters & Setters
	public Noeud getPremierNoeud() {
		return premierNoeud;
	}

	public void setPremierNoeud(Noeud premierNoeud) {
		this.premierNoeud = premierNoeud;
	}
	
	
	@Override
	public String toString() 
	{
		if (this.premierNoeud == null) 
		{//cas où l'arbre est vide
			return "L'arbre est vide" ;
		}
		else
		{
			return this.premierNoeud.toString() ;
		}
	}
	
	public void ajouterNoeudDansArbre(Stagiaire stagiaire)
	{
		if (this.premierNoeud == null) 
		{//cas où l'arbre est vide : on cree le premier Noeud avec le stagiaire donné
			setPremierNoeud(new Noeud(stagiaire)) ;
		}
		else 
		{ //sinon on appelle juste la fonction ajouterNoeud de la classe Noeud
			this.premierNoeud.ajouterNoeud(stagiaire) ;
		}
	}
	
	
	public int tailleArbre() {
		if (this.premierNoeud == null) {//cas où l'arbre est vide
			return 0;
		}else {//cas où l'arbre n'est pas vide
			return this.premierNoeud.taille();
		}
	}
	
	
	public int hauteurArbre()
	{
		if (this.premierNoeud == null) 
		{//cas où l'arbre est vide
			return 0;
		}
		else 
		{
			return this.premierNoeud.hauteur();
		}
	}
	
}
	

