package model;

public class Stagiaire {
	// attributs
	
	//constantes pour le fichier binaire :
	public final static int TAILLE_MAX_NOM = 20 ; //20 char 
	public final static int TAILLE_MAX_PRENOM = 20 ; 
	public final static int TAILLE_MAX_DPT = 3 ;
	public final static int TAILLE_MAX_PROMO = 10 ;
		
	public final static int TAILLE_OCTET_NOM = 2 * TAILLE_MAX_NOM ; //20 char * 2 octets 
	public final static int TAILLE_OCTET_PRENOM = 2 * TAILLE_MAX_PRENOM ; 
	public final static int TAILLE_OCTET_DPT = 2 * TAILLE_MAX_DPT ;
	public final static int TAILLE_OCTET_PROMO = 2 * TAILLE_MAX_PROMO ;
	public final static int TAILLE_OCTET_ANNEE = 4 ;
	public final static int TAILLE_OCTET_STAGIAIRE = TAILLE_OCTET_NOM + TAILLE_OCTET_PRENOM + TAILLE_OCTET_DPT + TAILLE_OCTET_PROMO + TAILLE_OCTET_ANNEE;
	
	public String nom;
	public String prenom;
	public String departement;
	public String promotion;
	public int annee;
	
	//contructeur
	
	public Stagiaire(String nom, String prenom, String departement, String promotion, int annee) {
		
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.promotion = promotion;
		this.annee = annee;
	}
	
	//Getters & Setters
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getDepartement() {
		return departement;
	}
	public void setDepartement(String departement) {
		this.departement = departement;
	}
	public String getPromotion() {
		return promotion;
	}
	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	
	@Override
	public String toString() {
		return "Stagiaire [nom=" + nom + ", prenom=" + prenom + ", departement=" + departement + ", promotion="
				+ promotion + ", annee=" + annee + "]";
	}
	
	

}
