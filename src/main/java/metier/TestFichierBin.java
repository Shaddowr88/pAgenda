package metier;

import java.io.IOException;
import java.util.ArrayList;

import model.ArbreBinaire;
import model.Stagiaire;

public class TestFichierBin {

public static void main(String[] args) throws IOException {
		
		//Attention, supprimer fichier bin des tests precedents avant de lancer ce main

		ArbreBinaire arbreAnnuaire = new ArbreBinaire() ;
		
		arbreAnnuaire.lectureFichierDon(arbreAnnuaire);
		
		
		arbreAnnuaire.testLecture();


//		System.out.println(recherche.search(arbreAnnuaire.getRaf(), stagiaireAChercher));
		Stagiaire stagiaireASupprimer = new Stagiaire("LACROIX", "Dominique", "75", "AI 61", 2003) ;
		
//		System.out.println(arbreAnnuaire.rechercheNoeudASupprimer(stagiaireASupprimer, 0)) ;
//		
//		System.out.println(arbreAnnuaire.rechercheIndexPereDuNoeudASupprimer(0, 13)) ;
		System.out.println("\n");
		//Test suppression racine
		//arbreAnnuaire.supprimerStagiaire(stagiaireASupprimer, arbreAnnuaire.getRaf());
		
		//arbreAnnuaire.testLecture();
		
		//System.out.println(arbreAnnuaire.getPremierNoeud().fichierBinVersArrayList(arbreAnnuaire.getRaf()));
		
	}
	
	
	
	

}