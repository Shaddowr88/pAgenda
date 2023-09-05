package metier;

import java.util.ArrayList;
import java.util.List;

import model.ArbreBinaire;
import model.Noeud;
import model.Stagiaire;

public class Recherche {
	

	public List<Stagiaire> search(ArbreBinaire stagiaires, ArrayList<String> termsToFind) {
        List<Stagiaire> resultList = new ArrayList<>();
        superSearchRecursive(stagiaires.getPremierNoeud(), termsToFind, resultList);
        return resultList;
    }

    private static void searchRecursive(
    		Noeud root, 
    		String[] termsToFind, 
    		List<Stagiaire> resultList) 
    {
        if (root == null) {
            return;
        }
    
        for (String term : termsToFind) {
            if (
            		root.getStagiaire().nom.contains(term) || 
            		root.getStagiaire().prenom.contains(term) || 
            		Integer.toString(root.getStagiaire().annee).contains(term)||
            		root.getStagiaire().departement.contains(term)||
            		root.getStagiaire().promotion.contains(term)) 
        	{
               resultList.add(root.getStagiaire());
               break; // On ajoute le nœud une seule fois s'il correspond à au moins un terme.
            }
        }
        searchRecursive(root.getFilsDroit(), termsToFind, resultList);
        searchRecursive(root.getFilsGauche(), termsToFind, resultList);
    }

    
    private static void superSearchRecursive(
    		Noeud root, 
    		ArrayList<String> termsToFind, 
    		List<Stagiaire> resultList
    		){
        if (root == null) {
            return;
        }
        
        boolean allTerms = true;

        for (String term : termsToFind) {
           if (
        		   !(
        				   root.getStagiaire().nom.contains(term) || 
        				   root.getStagiaire().prenom.contains(term) || 
        				   Integer.toString(root.getStagiaire().annee).contains(term)||
        				   root.getStagiaire().departement.contains(term)||
        				   root.getStagiaire().promotion.contains(term))) 
        	{
                //resultList.add(root);
            	allTerms = false;
            	
                break; // On ajoute le nœud une seule fois s'il correspond à au moins un terme.
            }
        }
        
        if (allTerms) {
        	resultList.add(root.getStagiaire());	
        }

       superSearchRecursive(root.getFilsDroit(), termsToFind, resultList);
       superSearchRecursive(root.getFilsGauche(), termsToFind, resultList);
    }

}
