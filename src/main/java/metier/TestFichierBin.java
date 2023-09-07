package metier;

import java.io.IOException;
import java.util.ArrayList;

import model.ArbreBinaire;
import model.Noeud;
import model.Stagiaire;

public class TestFichierBin {

public static void main(String[] args) throws IOException {
		
		
		
		//Attention, supprimer fichier bin des tests precedents avant de lancer ce main

		ArbreBinaire arbreAnnuaire = new ArbreBinaire() ;
		
		arbreAnnuaire.lectureFichierDon(arbreAnnuaire);
		
		
		arbreAnnuaire.testLecture();
		
		
		//System.out.println(arbreAnnuaire.getPremierNoeud().fichierBinVersArrayList(arbreAnnuaire.getRaf()));
	
		
		arbreAnnuaire.testLecture();
	
		ArrayList<String> stagiaireAChercher = new ArrayList() ; 
		//stagiaireAChercher.add("ROIGNANT") ;
		stagiaireAChercher.add("ROIGNANT") ;
		stagiaireAChercher.add("Pierre-Yves") ;
		stagiaireAChercher.add("77") ;
		stagiaireAChercher.add("AI 95") ;
		
		Recherche recherche = new Recherche() ;
		
		System.out.println(recherche.search(arbreAnnuaire.getRaf(), stagiaireAChercher));
		
		
		
		
	}
	
	
	
	

}