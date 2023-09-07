package metier;

import java.io.*;
import java.nio.file.Path;

import model.ArbreBinaire;
import model.Stagiaire;

public class LectureFichier {

		String nom = "";
		String prenom= "";
		String dpt = "";
		String promo = "";
		int annee = 0;
		int compteurLigne = 1;
		int compteurStagiaire = 0;
		public static boolean fichierBinExiste = false;

		//On cree un arbre binaire vide qui sera notre annuaire
		//ArbreBinaire arbreAnnuaire = new ArbreBinaire() ; 
		
		//La méthode permets de d'abord vérifier si le fichier Bin existe, puis de le créer à partir du fichier DON, si ce n'est pas le cas.
		public boolean verifierSiFichierBinExiste()
		{
			//Je créé une variable qui stock le path du fichier Bin
			File annuaireBin = new File("src/Files/annuaire.bin");
		//Je regarde si le chemin existe et si l'élement est bien un fichier
		if(annuaireBin.exists() && annuaireBin.isFile())	
		{ //Si la condition est remplie je passe le boléen à true
		fichierBinExiste = true;
		}
		else 
		{
			
			//Création du lecteur de fichier
			LectureFichier lectureFichier = new LectureFichier() ;
			
			//Création de l'arbreBinaire qui va passer les données vers le fichier binaire
			ArbreBinaire arbreAnnuaire = new ArbreBinaire() ;
			
			//Lecture du fichier DON en appelant la méthode de lecture
			lectureFichier.lectureFichierDon(arbreAnnuaire);
			
			verifierSiFichierBinExiste();
		}
		return fichierBinExiste;
		}
		
		
		
		BufferedReader reader;
		

		
		public void lectureFichierDon(ArbreBinaire arbreAnnuaire)
		{

		try {

			reader = new BufferedReader(new FileReader(".\\src\\Files\\STAGIAIRESTEST.DON"));

			String line = reader.readLine();

			while (line != null) {

				//System.out.println(line);
				// si c'est la première ligne, alors remplir la variable nom;
				if (compteurLigne == 1) {
					nom = line.toUpperCase(); //on stocke le nom en le mettant en majuscules pour eviter les cas particuliers (noms à particules par ex.)
				}
				// si c'est la deuxième ligne, alors remplir la variable prenom;
				if (compteurLigne == 2) {
					prenom = line;
				}
				// si c'est la troisième ligne, alors remplir la variable dpt;
				if (compteurLigne ==3 ) {

					dpt = line;

				}
				// si c'est la quatrième ligne, alors remplir la variable promo;
				if (compteurLigne == 4) {
					promo = line;
				}
				// si c'est la cinquième ligne, alors remplir la variable annee;
				if (compteurLigne == 5) {
					annee = Integer.valueOf(line);
				}
				if (compteurLigne == 6 && line.equals("*")) {

					Stagiaire stagiaire = new Stagiaire(nom, prenom, dpt, promo, annee);

					// 3)  Reset les variables et le compteur
					compteurLigne = 0;
					compteurStagiaire += 1;

					//Ajouter le stagiaire en tant que noeud dans notre arbre annuaire 
					//et ecrire le fichier bin
					arbreAnnuaire.ajouterStagiaireDeFichierDonAFichierBin(stagiaire);

				}

				compteurLigne += 1;
				line = reader.readLine();
			}

			
			reader.close();
			

		} catch (IOException e) {
			e.printStackTrace();


		}
		
	}
}