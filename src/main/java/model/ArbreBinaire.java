package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class ArbreBinaire {
	// attributs

		private NoeudBinaire premierNoeud ;
		private RandomAccessFile raf = null ;
		static boolean premierNoeudCreeOuNon = false ;

		// contructeur
		public ArbreBinaire(NoeudBinaire premierNoeud) {

			this.premierNoeud = premierNoeud;
		}
		
		// constructeur vide 
		public ArbreBinaire() {
			try {
				raf = new RandomAccessFile("src/Files/annuaire.bin", "rw");
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
	

