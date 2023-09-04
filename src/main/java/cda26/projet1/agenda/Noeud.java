package cda26.projet1.agenda;

public class Noeud {
	
	//attributs
	private Stagiaire stagiaire;
	private Noeud filsGauche;
	private Noeud filsDroit;
	
	
	//Contructeur
	public Noeud(Stagiaire stagiaire, Noeud filsGauche, Noeud filsDroit) {
		
		this.stagiaire = stagiaire;
		this.filsGauche = filsGauche;
		this.filsDroit = filsDroit;
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
	
	
	

}
