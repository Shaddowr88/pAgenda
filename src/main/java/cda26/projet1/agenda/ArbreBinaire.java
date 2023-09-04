package cda26.projet1.agenda;

public class ArbreBinaire {
	//attributs
	
	private Noeud premierNoeud;
	
	//contructeur

	public ArbreBinaire(Noeud premierNoeud) {
		
		this.premierNoeud = premierNoeud;
	}

	//Getters & Setters
	public Noeud getPremierNoeud() {
		return premierNoeud;
	}

	public void setPremierNoeud(Noeud premierNoeud) {
		this.premierNoeud = premierNoeud;
	}

}
