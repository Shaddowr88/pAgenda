package model;

/**
 * Stagiaire est la classe qui définit l'objet Stagiaire utilisé par les classes NoeudBinaire et ArbreBinaire dans le cas d'ajout d'objets "Stagiaire"
 * au sein d'un fichier binaire stockant un arbre binaire. 
 * Un Stagiaire possède ainsi : un nom, un prenom, un département, une promotion, une année et un ensemble de constantes déterminant la taille maximale que doit occuper chaque attribut en terme d'octet.
 * Cette classe est caractérisée par les informations suivantes :
 * <ul>
 * <li>Les attributs de le classe :</li>
 * <li>L'attribut nom indique le nom associé à l'objet Stagiaire</li>
 * <li>L'attribut prenom indique le prenom associé à l'objet Stagiaire</li>
 * <li>L'attribut departement indique le département associé à l'objet Stagiaire</li>
 * <li>L'attribut promotion indique la promotion associée à l'objet Stagiaire</li>
 * <li>L'attribut annee indique l'année associée à l'objet Stagiaire</li>
 * 
 * <li>Les constantes de classe :</li>
 * <li>Les constantes TAILLE_MAX_NOM/PRENOM/DPT/PROMO fixent la taille des attributs respectivement associés en termes de nombre de caractères.</li>
 * <li>Les constantes TAILLE_OCTET_NOM/PRENOM/DPT/PROMO/ANNEE fixent la taille des attributs respectivement associés en termes d'octets. </li>
 * <li>La constante TAILLE_OCTET_STAGIAIRE fixe la taille totale et maximale de l'objet Stagiaire en ajoutant la somme des valeurs des constantes TAILLE_OCTET_NOM/PRENOM/DPT/PROMO/ANNEE. </li>
 * </ul>
 * Description des principales fonctionnalités de la classe :
 *  - Un constructeur surchargé
 *  - Des getters et setters
 *  - Une toString permettant d'afficher les attributs de l'objet
 * </p>
 * Les attributs statiques :
 * None
 * </p>
 * @author Deltombe Floriane
 * @author Difilippo Gabriel
 * @author Bitjoka Vincent
 * @version 1
 */
public class Stagiaire {
	
	
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
	
	// attributs
	 /** 
     * L'attribut nom possède une visibilité "public" et un type String. Il indique le nom associé à l'objet Stagiaire.
     */
	public String nom;
	 /** 
     * L'attribut prenom possède une visibilité "public" et un type String. Il indique le prenom associé à l'objet Stagiaire.
     */
	public String prenom;
	 /** 
     * L'attribut departement possède une visibilité "public" et un type String. Il indique le département associé à l'objet Stagiaire.
     */
	public String departement;
	 /** 
     * L'attribut promotion possède une visibilité "public" et un type String. Il indique la promotion associée à l'objet Stagiaire.
     */
	public String promotion;
	/** 
     * L'attribut annee possède une visibilité "public" et un type int. Il indique l'année associée à l'objet Stagiaire.
     */
	public int annee;
	
	//contructeur
	/** 
     * <b>Constructeur surchargé de Stagiaire</b> 
     *  
     * Construction d'un objet Stagiaire à partir de ses attributs passés en paramètres.
     * 
     * @param nom
     * @param prenom    
     * @param departement
     * @param promotion   
     * @param annee
     * 	
     */ 
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
	
	/**
	 * La toString permets d'afficher dans une String tous les attributs de l'objet Stagiaire
	 */
	@Override
	public String toString() {
		return "Stagiaire [nom=" + nom + ", prenom=" + prenom + ", departement=" + departement + ", promotion="
				+ promotion + ", annee=" + annee + "]";
	}
	
	

}
