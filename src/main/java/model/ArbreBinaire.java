package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class ArbreBinaire {
	// attributs
		String nom = "";
		String prenom= "";
		String dpt = "";
		String promo = "";
		int annee = 0;
		int compteurLigne = 1;
		int compteurStagiaire = 0;
		private NoeudBinaire premierNoeud ;
		private RandomAccessFile raf = null ;
		static boolean premierNoeudCreeOuNon = false ;
		private final String BIN_PATH = "src/Files/annuaire.bin";

		// contructeur
		public ArbreBinaire(NoeudBinaire premierNoeud) {

			this.premierNoeud = premierNoeud;
		}
		
		// constructeur vide 
		public ArbreBinaire() {
			try {
				raf = new RandomAccessFile(BIN_PATH, "rw");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Getters & Setters
		public NoeudBinaire getPremierNoeud() {
			return premierNoeud;
		}

		public void setPremierNoeud(NoeudBinaire premierNoeud) {
			this.premierNoeud = premierNoeud;
		}

		public RandomAccessFile getRaf() {
			return raf;
		}

		public void setRaf(RandomAccessFile raf) {
			this.raf = raf;
		}

		@Override
		public String toString() {
			if (this.premierNoeud == null) {// cas où l'arbre est vide
				return "L'arbre est vide";
			} else {
				return this.premierNoeud.toString();
			}
		}

		// Methode appelee dans LectureFichier pour ajouter chaque stagiaire lu dans le
		// fichier DON au fichier binaire
		public void ajouterStagiaireDeFichierDonAFichierBin(Stagiaire stagiaire) throws IOException 
		{

			// si l'arbre est vide = > si le fichier est vide
			if (premierNoeudCreeOuNon == false) 
			{
				// dans ce cas j'écris le stagiaire au début du fichier
				raf.seek(0);
				// creer le premier noeud dans l'arbre
				this.setPremierNoeud(new NoeudBinaire(stagiaire));
				// ecrire ce noeud dans le fichier binaire
				this.getPremierNoeud().ecrireNoeudDansFichierBin(raf, stagiaire);

				premierNoeudCreeOuNon = true;
			} 
			else 
			{
				// je me met au début du fichier
				raf.seek(0);
				// je lis le premier noeud et je le stocke dans une variable racine
				NoeudBinaire racine = new NoeudBinaire();
				racine = racine.lireNoeudFichierBinVersObjetNoeudBinaire(raf);
				//j'appelle la methode de la classe NoeudBinaire qui va ecrire les noeuds dans le fichier bin
				racine.ajouterNoeudDansFichierBin(stagiaire, raf);
			}

		}

		
BufferedReader reader;
		
		
		public void lectureFichierDon(ArbreBinaire arbreAnnuaire)
		{

		try {
			
			if (raf.length() != 0)
			{
				System.out.println("Le fichier est déjà créé");
			}else
			{
			System.out.println("Je créé le fichier");
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
			
			}
		} catch (IOException e) {
			e.printStackTrace();


		}
		
	}

		//methode test d'affichage console  
		public void testLecture() throws IOException {
			int nbNoeud = (int)raf.length()/NoeudBinaire.TAILLE_MAX_NOEUD;
			NoeudBinaire noeud = new NoeudBinaire();
			System.out.println("il y a " + nbNoeud + " noeuds") ;
			raf.seek(0);
			for(int i =0; i < nbNoeud; i++) {
				NoeudBinaire courant = noeud.lireNoeudFichierBinVersObjetNoeudBinaire(raf);
				System.out.println(courant);
			}
		
		}
		
		}



	

