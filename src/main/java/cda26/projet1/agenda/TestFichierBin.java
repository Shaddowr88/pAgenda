package cda26.projet1.agenda;

import metier.EcritureFichierBin;
import metier.LectureFichier;
import model.Noeud;
import model.Stagiaire;

public class TestFichierBin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Noeud test = new Noeud(new Stagiaire("Gabriel", "sfjsdfv", "00", "CDA26", 2023), null , null);
		Noeud test2 = new Noeud(new Stagiaire("Loic", "sfjsdfv", "00", "CDA26", 2023), null , null);
		
		EcritureFichierBin ecritureFichierBin = new EcritureFichierBin();
		LectureFichier lectureFichier = new LectureFichier() ;
		
		//ecritureFichierBin.ecrireArbreDansFichierBin(0, test, test2); 
		
		//Attention, supprimer fichier bin avant de lancer ce main
		ecritureFichierBin.creerFichierBinaire(lectureFichier.lectureFichierDon().getPremierNoeud());
		
		//ecritureFichierBin.ajouterStagiaireDansFichierBin(0, lectureFichier.lectureFichierDon().getPremierNoeud(), test2); 
		
		ecritureFichierBin.creerArbreFichierBin(lectureFichier.lectureFichierDon(), lectureFichier.lectureFichierDon().getPremierNoeud());
	}
	
	
	
	

}
