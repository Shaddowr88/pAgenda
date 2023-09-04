package cda26.projet1.agenda;

import java.io.*;
import java.nio.file.Path;

public class LectureFichier {
	
	public static void main(String[] args) throws IOException {
		
		String nom = "";
		String prenom= "";
		String dpt = "";
		String promo = "";
		int annee = 0;
		int compteurLigne = 1;
		int compteurStagiaire = 0;
		
		
	
	BufferedReader reader;
	
	try {
	
	reader = new BufferedReader(new FileReader(".\\src\\Files\\STAGIAIRES.DON"));
		
		String line = reader.readLine();
		
		while (line != null) {
			
			//System.out.println(line);
			// si c'est la première ligne, alors remplir la variable nom;
			if (compteurLigne == 1) {
				nom = line;
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
				System.out.println(stagiaire);
				
				// 3)  Reset les variables et le compteur
				compteurLigne = 0;
				compteurStagiaire += 1;
				
			}
			
			
			
			// si c'est une étoile ou compteur = 6, alors :
			// 1)  Stocker les variables dans un objet Stagiaire (constructeur)
			// 2)  Méthode ajouterNoeud(Stagiaire)
			
			compteurLigne += 1;
			line = reader.readLine();
		}
		
		reader.close();
		
    } catch (IOException e) {
        e.printStackTrace();
		
		
}
	System.out.println(compteurStagiaire);

}
}
