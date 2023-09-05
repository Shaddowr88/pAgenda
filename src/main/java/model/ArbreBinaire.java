package model;

import java.util.ArrayList;
import java.util.List;

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
	

	 public List<Stagiaire> convertirEnArrayList() {
	        List<Stagiaire> arrayList = new ArrayList<>();
	        convertirEnArrayList(premierNoeud, arrayList);
	        return arrayList;
	    }
	
	private void convertirEnArrayList(Noeud courant, List<Stagiaire> arrayList) {
       if (courant == null) {
           return;
       }

       arrayList.add(courant.getStagiaire());
       convertirEnArrayList(courant.getFilsGauche(), arrayList);
       convertirEnArrayList(courant.getFilsDroit(), arrayList);
   }
	
}
	

