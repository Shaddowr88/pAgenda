package metier;

import java.util.ArrayList;
import java.util.List;

import model.ArbreBinaire;
import model.Noeud;
import model.NoeudBinaire;
import model.Stagiaire;

public class Recherche {

	 public List<Stagiaire> search(ArbreBinaire stagiaires, ArrayList<String> termsToFind) {
	        List<Stagiaire> resultList = new ArrayList<>();
	        superSearchRecursive(stagiaires, stagiaires.getPremierNoeud(), termsToFind, resultList);
	        return resultList;
	    }

   

    private static void superSearchRecursive(
            ArbreBinaire arbre,
            NoeudBinaire noeudBinaire,
            ArrayList<String> termsToFind,
            List<Stagiaire> resultList) {
        if (noeudBinaire == null) {
            return;
        }

        boolean allTerms = true;

        for (String term : termsToFind) {
            if (
                    !(
                            noeudBinaire.getStagiaire().getNom().contains(term) ||
                            noeudBinaire.getStagiaire().getPrenom().contains(term) ||
                            Integer.toString(noeudBinaire.getStagiaire().getAnnee()).contains(term) ||
                            noeudBinaire.getStagiaire().getDepartement().contains(term) ||
                            noeudBinaire.getStagiaire().getPromotion().contains(term)))
            {
                allTerms = false;
                break; // On ajoute le stagiaire une seule fois s'il correspond à au moins un terme.
            }
        }

        if (allTerms) {
            resultList.add(noeudBinaire.getStagiaire());
        }

        // Parcours récursif des fils
        if (noeudBinaire.getFilsGauche() != -1) {
            superSearchRecursive(arbre, arbre.getNoeudBinaire(noeudBinaire.getFilsGauche()), termsToFind, resultList);
        }
        if (noeudBinaire.getFilsDroit() != -1) {
            superSearchRecursive(arbre, arbre.getNoeudBinaire(noeudBinaire.getFilsDroit()), termsToFind, resultList);
        }
    }
}
