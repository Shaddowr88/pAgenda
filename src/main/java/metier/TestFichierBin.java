package metier;

import java.io.IOException;

import model.ArbreBinaire;
import model.Noeud;
import model.Stagiaire;

public class TestFichierBin {

	public static void main(String[] args) throws IOException {
		
		
		EcritureFichierBin ecritureFichierBin = new EcritureFichierBin();
		LectureFichier lectureFichier = new LectureFichier() ;
		
		//ecritureFichierBin.ecrireArbreDansFichierBin(0, test, test2); 
		
		//Attention, supprimer fichier bin des tests precedents avant de lancer ce main
		//ecritureFichierBin.creerFichierBinaire(lectureFichier.lectureFichierDon().getPremierNoeud());
		
		//ecritureFichierBin.ajouterStagiaireDansFichierBin(0, lectureFichier.lectureFichierDon().getPremierNoeud(), test2); 
		
		ArbreBinaire arbreTest = lectureFichier.lectureFichierDon() ;
		
		System.out.println(arbreTest); //test affichage arbre lu en GND
		System.out.println("Taille de l'arbre : " + arbreTest.tailleArbre());
		System.out.println("Hauteur de l'arbre : " + arbreTest.hauteurArbre());
		
		ecritureFichierBin.creerArbreFichierBin(arbreTest, arbreTest.getPremierNoeud());
	}
	
	
	
	

}
