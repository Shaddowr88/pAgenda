package metier;

import java.io.IOException;

import model.ArbreBinaire;
import model.Noeud;
import model.Stagiaire;

public class TestFichierBin {

public static void main(String[] args) throws IOException {
		
		LectureFichier lectureFichier = new LectureFichier() ;
		
		//Attention, supprimer fichier bin des tests precedents avant de lancer ce main

		ArbreBinaire arbreAnnuaire = new ArbreBinaire() ;
		
		lectureFichier.lectureFichierDon(arbreAnnuaire);
		
		arbreAnnuaire.testLecture();
		
		
	}
	
	
	
	

}