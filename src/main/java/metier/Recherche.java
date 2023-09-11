package metier;
import java.util.ArrayList;
import java.util.List;
import model.Stagiaire;
import model.NoeudBinaire;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Cette classe sert a effectuer des recherches dans une liste d'objets de type Stagiaire
 *
 * @author Loic S.
 * @author Floriane D. 
 */
public class Recherche {
	
	/**
	 * Methode permettant de retourner une liste contenant les stagiaires correspondant aux termes recherches par un utilisateur
	 * 
	 * @param le fichier de type RandomAccessFile dans lequel sont stockees les informations des stagiaires
	 * 
	 * @param la liste des termes recherches par l'utilisateur 
	 * 
	 * @throws IOException si une exception de type Input ou Output arrive
	 * 
	 * @return la liste contenant le/les stagiaires correspondant aux termes recherches par l'utilisateur
	 * 
	 */
	public List<Stagiaire> search(RandomAccessFile raf, ArrayList<String> termsToFind) throws IOException {
        List<Stagiaire> resultList = new ArrayList<>();
        
        NoeudBinaire noeudCourant = new NoeudBinaire() ;
        
        //Je me place a la position 0 pour lire la racine dans le fichier binaire
        raf.seek(0);
        noeudCourant = noeudCourant.lireNoeudFichierBinVersObjetNoeudBinaire(raf) ;
        
        //searchRecursive(raf, noeudCourant, termsToFind, resultList);
        superSearchRecursive(raf, noeudCourant, termsToFind, resultList);
        return resultList;
    }
	
	
	/**
	 * Methode permettant de creer une liste contenant les stagiaires correspondant a l'un des termes recherches par un utilisateur
	 * 
	 * @param le fichier de type RandomAccessFile dans lequel sont stockees les informations des stagiaires
	 * 
	 * @param le noeud de l'arbre binaire de recherche dans lequel on est positionnes (est egal a la racine a l'appel de la methode)
	 * 
	 * @param la liste des termes recherches par l'utilisateur 
	 * 
	 * @param la liste des resultats de la recherche, contenant les stagiaires correspondant a la recherche
	 * 
	 * @throws IOException si une exception de type Input ou Output arrive
	 * 
	 * @return la liste contenant le/les stagiaires correspondant aux termes recherches par l'utilisateur
	 * 
	 */
	//Si on veut faire une recherche de n'importe quel stagiaire contenant n'importe quel element
	//entré en recherche
    private static void searchRecursive(
    		RandomAccessFile raf,
    		NoeudBinaire noeudCourant, 
    		ArrayList<String>  termsToFind, 
    		List<Stagiaire> resultList) throws IOException 
    {
    	//Si fichier vide, ne retourne rien
        if (noeudCourant == null) {
            return;
        }
    
        for (String term : termsToFind) {
            if (
            		noeudCourant.getStagiaire().getNom().contains(term) || 
            		noeudCourant.getStagiaire().getPrenom().contains(term) || 
            		Integer.toString(noeudCourant.getStagiaire().getAnnee()).contains(term)||
            		noeudCourant.getStagiaire().getDepartement().contains(term)||
            		noeudCourant.getStagiaire().getPromotion().contains(term)) 
        	{
               resultList.add(noeudCourant.getStagiaire());
               break; // On ajoute le nœud une seule fois s'il correspond à au moins un terme.
            }
        }
        //si il y a un fils gauche, càd que filsGauche != -1, 
        //j'appelle la fonction de facon recursive sur son fils gauche
        //en positionnant mon curseur à l'index correspondant au filsGauche
        if (noeudCourant.getFilsGauche() != -1)
        {
        	raf.seek(noeudCourant.getFilsGauche() * NoeudBinaire.TAILLE_MAX_NOEUD);
			//je lis le noeud et je le stocke dans un objet : 
			//creation d'un noeud vide
			NoeudBinaire noeudGauche = new NoeudBinaire() ;
			//lecture du noeud du fils gauche dans le noeud vide
			noeudGauche = noeudGauche.lireNoeudFichierBinVersObjetNoeudBinaire(raf) ;
			//Appel recursif sur le fils gauche : 
			searchRecursive(raf, noeudGauche, termsToFind, resultList);
        }
        
        //Meme chose sur le fils droit
        if (noeudCourant.getFilsDroit() != -1)
        {
        	raf.seek(noeudCourant.getFilsDroit() * NoeudBinaire.TAILLE_MAX_NOEUD);
			//je lis le noeud et je le stocke dans un objet : 
			//creation d'un noeud vide
			NoeudBinaire noeudDroit = new NoeudBinaire() ;
			//lecture du noeud du fils droit dans le noeud vide
			noeudDroit = noeudDroit.lireNoeudFichierBinVersObjetNoeudBinaire(raf) ;
			//Appel recursif sur le fils droit : 
			searchRecursive(raf, noeudDroit, termsToFind, resultList);
        }
	       
    }
    
    /**
	 * Methode permettant de creer une liste contenant les stagiaires correspondant a tous les termes recherches par un utilisateur
	 * 
	 * @param le fichier de type RandomAccessFile dans lequel sont stockees les informations des stagiaires
	 * 
	 * @param le noeud de l'arbre binaire de recherche dans lequel on est positionnes (est egal a la racine a l'appel de la methode)
	 * 
	 * @param la liste des termes recherches par l'utilisateur 
	 * 
	 * @param la liste des resultats de la recherche, contenant les stagiaires correspondant a la recherche
	 * 
	 * @throws IOException si une exception de type Input ou Output arrive
	 * 
	 * @return la liste contenant le/les stagiaires correspondant aux termes recherches par l'utilisateur
	 * 
	 */
    //Si on veut faire une recherche pour le ou les elements contenant
    //TOUS les elements entrés en recherche
    private static void superSearchRecursive(
    		RandomAccessFile raf,
    		NoeudBinaire noeudCourant, 
    		ArrayList<String> termsToFind, 
    		List<Stagiaire> resultList
    		) throws IOException{
        if (noeudCourant == null) {
            return;
        }
        
        boolean allTerms = true;
        
        for (String term : termsToFind) {
           if (
        		   !(
        				   noeudCourant.getStagiaire().nom.contains(term) || 
        				   noeudCourant.getStagiaire().prenom.contains(term) || 
        				   Integer.toString(noeudCourant.getStagiaire().annee).contains(term)||
        				   noeudCourant.getStagiaire().departement.contains(term)||
        				   noeudCourant.getStagiaire().promotion.contains(term))) 
        	{
                //resultList.add(root);
            	allTerms = false;
            	
                break; // On ajoute le nœud une seule fois s'il correspond à au moins un terme.
            }
        }
        
        if (allTerms) {
        	resultList.add(noeudCourant.getStagiaire());	
        }
       
      //si il y a un fils gauche, càd que filsGauche != -1, 
        //j'appelle la fonction de facon recursive sur son fils gauche
        //en positionnant mon curseur à l'index correspondant au filsGauche
        if (noeudCourant.getFilsGauche() != -1)
        {
        	raf.seek(noeudCourant.getFilsGauche() * NoeudBinaire.TAILLE_MAX_NOEUD);
			//je lis le noeud et je le stocke dans un objet : 
			//creation d'un noeud vide
			NoeudBinaire noeudGauche = new NoeudBinaire() ;
			//lecture du noeud du fils gauche dans le noeud vide
			noeudGauche = noeudGauche.lireNoeudFichierBinVersObjetNoeudBinaire(raf) ;
			//Appel recursif sur le fils gauche : 
			superSearchRecursive(raf, noeudGauche, termsToFind, resultList);
        }
        
        //Meme chose sur le fils droit
        if (noeudCourant.getFilsDroit() != -1)
        {
        	raf.seek(noeudCourant.getFilsDroit() * NoeudBinaire.TAILLE_MAX_NOEUD);
			//je lis le noeud et je le stocke dans un objet : 
			//creation d'un noeud vide
			NoeudBinaire noeudDroit = new NoeudBinaire() ;
			//lecture du noeud du fils droit dans le noeud vide
			noeudDroit = noeudDroit.lireNoeudFichierBinVersObjetNoeudBinaire(raf) ;
			//Appel recursif sur le fils droit : 
			superSearchRecursive(raf, noeudDroit, termsToFind, resultList);
        }
       
    }
}