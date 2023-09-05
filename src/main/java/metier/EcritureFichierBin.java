package metier;

import java.io.IOException;
import java.io.RandomAccessFile;

import model.ArbreBinaire;
import model.Noeud;
import model.Stagiaire;

public class EcritureFichierBin 
{
	static int compteur = 1 ;
	
	
	
	public String getNomLong(Noeud noeud)
	{
		String nomLong = "" ; 
		
		String nomStagiaire = noeud.getStagiaire().getNom() ;
		
		if (nomStagiaire.length() <= Stagiaire.TAILLE_MAX_NOM)
		{
			nomLong = nomStagiaire ; 
			
			for ( int i = nomStagiaire.length() ; i < Stagiaire.TAILLE_MAX_NOM ; i++ )
			{
				//On ajoute des etoiles au nom si le nom est en dessous de la taille max
				nomLong += "*" ;
			}
		}
		else 
		{
			nomLong  = nomStagiaire.substring(0, Stagiaire.TAILLE_MAX_NOM) ;
		}
		
		
		return nomLong ;
	}
	
	
	public String getPrenomLong(Noeud noeud)
	{
		String prenomLong = "" ; 
		
		String prenomStagiaire = noeud.getStagiaire().getPrenom() ;
		
		if (prenomStagiaire.length() <= Stagiaire.TAILLE_MAX_PRENOM)
		{
			prenomLong = prenomStagiaire ; 
			
			for ( int i = prenomStagiaire.length() ; i < Stagiaire.TAILLE_MAX_PRENOM ; i++ )
			{
				//On ajoute des etoiles au nom si le nom est en dessous de la taille max
				prenomLong += "*" ;
			}
		}
		else 
		{
			prenomLong  = prenomStagiaire.substring(0, Stagiaire.TAILLE_MAX_PRENOM) ;
		}
		
		
		return prenomLong ;
	}
	
	
	public String getDepartementLong(Noeud noeud)
	{
		String dptLong = "" ; 
		
		String dptStagiaire = noeud.getStagiaire().getDepartement() ;
		
		if (dptStagiaire.length() <= Stagiaire.TAILLE_MAX_DPT)
		{
			dptLong = dptStagiaire ; 
			
			for ( int i = dptStagiaire.length() ; i < Stagiaire.TAILLE_MAX_DPT ; i++ )
			{
				//On ajoute des etoiles au nom si le nom est en dessous de la taille max
				dptLong += "*" ;
			}
		}
		else 
		{
			dptLong  = dptStagiaire.substring(0, Stagiaire.TAILLE_MAX_DPT) ;
		}
		
		return dptLong ;
	}
	
	
	
	public String getPromoLong(Noeud noeud)
	{
		String promoLong = "" ; 
		String promoStagiaire = noeud.getStagiaire().getPromotion() ;
		
		if (promoStagiaire.length() <= Stagiaire.TAILLE_MAX_PROMO)
		{
			promoLong = promoStagiaire ; 
			
			for ( int i = promoStagiaire.length() ; i < Stagiaire.TAILLE_MAX_PROMO ; i++ )
			{
				//On ajoute des etoiles au nom si le nom est en dessous de la taille max
				promoLong += "*" ;
			}
		}
		else 
		{
			promoLong  = promoStagiaire.substring(0, Stagiaire.TAILLE_MAX_PROMO) ;
		}
		
		return promoLong ;
	}
	
	
	public void creerFichierBinaire(Noeud premierNoeud)
	{
		try 
		{
			//creer le fichier bin vide 
			RandomAccessFile raf = new RandomAccessFile(".\\src\\Files\\annuaire.bin", "rw");
			
			//ecrire racine de l'arbre (premierNoeud) dans le fichier bin à l'index 0 
			raf.seek(0);
			
			raf.writeChars(getNomLong(premierNoeud));
			raf.writeChars(getPrenomLong(premierNoeud));
			raf.writeChars(getDepartementLong(premierNoeud));
			raf.writeChars(getPromoLong(premierNoeud));
			raf.writeInt(premierNoeud.getStagiaire().getAnnee());
			raf.writeInt(-1);
			raf.writeInt(-1);
			
			raf.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
//	
//	public void ajouterStagiaireDansFichierBin(int index, Noeud premierNoeud, Noeud noeudAEcrire) 	
//	{
//		try 
//		{
//		
//		RandomAccessFile raf = new RandomAccessFile(".\\src\\Files\\annuaire.bin", "rw");
//		
//		if (getNomLong(noeudAEcrire).compareTo(getNomLong(premierNoeud)) < 0) //compare les 2 string dans l'ordre alphabetique 
//			//et s'arrete des qu'il y a une difference
//		{
//			//je pars a gauche
//			raf.seek((index * 118) + 110) ; //TO DO mettre en constantes
//			//je regarde si j'ai la place d'inserer a gauche
//			int indexFilsGauche = raf.readInt() ;
//			
//			System.out.println("Index fils gauche : " + indexFilsGauche);
//			
//			if (indexFilsGauche == -1)
//			{
//				//il y a la place : j'ajoute le nouveau Noeud
//				//je change l'index de filsGauche avec le nouveau fils
//				raf.writeInt(compteur);
//				//je me place à la fin du fichier bin
//				raf.seek(raf.length());
//				//j'ajoute le fils à la fin du fichier 
//				raf.writeChars(getNomLong(noeudAEcrire));
//				raf.writeChars(getPrenomLong(noeudAEcrire));
//				raf.writeChars(getDepartementLong(noeudAEcrire));
//				raf.writeChars(getPromoLong(noeudAEcrire));
//				raf.writeInt(noeudAEcrire.getStagiaire().getAnnee());
//				raf.writeInt(-1);
//				raf.writeInt(-1);
//				compteur++ ;
//				System.out.println("Compteur dans if fils gauche: " + compteur);
//				
//				
//			}
//			else //il y a deja un filsGauche
//			{
//				//pas de place : je fais un appel recursif sur le noeud suivant
//				//this.filsGauche.ajouterNoeud(noeud.getStagiaire()); //je demande au fils gauche de s'en occuper
//				ajouterStagiaireDansFichierBin(indexFilsGauche, premierNoeud, noeudAEcrire);
//			}
//		}
//		else
//		{
//			//je pars a droite
//			raf.seek((index * 118) + 114) ; //TO DO mettre en constantes
//			//je regarde si j'ai la place d'inserer a droite
//			int indexFilsDroit = raf.readInt() ;
//			
//			System.out.println("Index fils droit : " + indexFilsDroit);
//			
//			if (indexFilsDroit == -1)
//			{
//				//il y a la place : j'ajoute le nouveau Noeud
//				//je change l'index de filsDroit avec le nouveau fils
//				raf.writeInt(compteur);
//				//je me place à la fin du fichier bin
//				raf.seek(raf.length());
//				//j'ajoute le fils à la fin du fichier 
//				raf.writeChars(getNomLong(noeudAEcrire));
//				raf.writeChars(getPrenomLong(noeudAEcrire));
//				raf.writeChars(getDepartementLong(noeudAEcrire));
//				raf.writeChars(getPromoLong(noeudAEcrire));
//				raf.writeInt(noeudAEcrire.getStagiaire().getAnnee());
//				raf.writeInt(-1);
//				raf.writeInt(-1);
//				compteur++ ;
//				System.out.println("Compteur dans if fils droit : " + compteur);
//			}
//			else
//			{
//				//pas de place : je fais un appel recursif sur le noeud suivant
//				//je demande au fils droit de s'en occuper
//				ajouterStagiaireDansFichierBin(indexFilsDroit, premierNoeud, noeudAEcrire);
//			}	
//		}
//		
//		
//		
//		raf.close();
//		
//		} 
//		catch (IOException e) 
//		{
//			e.printStackTrace();
//		}
//	
//	}
	
	
	public void ajouterStagiaireFilsGaucheDansFichierBin(int index, Noeud noeudAEcrire) 	
	{
		try 
		{
		
			RandomAccessFile raf = new RandomAccessFile(".\\src\\Files\\annuaire.bin", "rw");
			//je pars a gauche
			int indexTest = 0 ; 
			
			raf.seek((indexTest * 118) + 110) ; //TO DO mettre en constantes
			//je regarde si j'ai la place d'inserer a gauche
			int indexFilsGauche = raf.readInt() ;

			System.out.println("Index fils gauche : " + indexFilsGauche);

			if (indexFilsGauche == -1)
			{
				//il y a la place : j'ajoute le nouveau Noeud
				//je change l'index de filsGauche avec le nouveau fils
				raf.writeInt(compteur);
				//je me place à la fin du fichier bin
				raf.seek(raf.length());
				//j'ajoute le fils à la fin du fichier 
				raf.writeChars(getNomLong(noeudAEcrire));
				raf.writeChars(getPrenomLong(noeudAEcrire));
				raf.writeChars(getDepartementLong(noeudAEcrire));
				raf.writeChars(getPromoLong(noeudAEcrire));
				raf.writeInt(noeudAEcrire.getStagiaire().getAnnee());
				raf.writeInt(-1);
				raf.writeInt(-1);
				compteur = compteur + 1 ;
				System.out.println("Compteur dans if fils gauche: " + compteur);

				
			}
			
			else //il y a deja un filsGauche
			{
				//pas de place : je fais un appel recursif sur le noeud suivant
				//this.filsGauche.ajouterNoeud(noeud.getStagiaire()); //je demande au fils gauche de s'en occuper
				ajouterStagiaireFilsGaucheDansFichierBin(indexFilsGauche, noeudAEcrire);
			}
			
			raf.close();
		
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	
	}
	
	public void ajouterStagiaireFilsDroiteDansFichierBin(int index, Noeud noeudAEcrire) 	
	{
		try 
		{
		
			RandomAccessFile raf = new RandomAccessFile(".\\src\\Files\\annuaire.bin", "rw");
			//je pars a droite
			int indexTest = 0 ; 
			
			raf.seek((indexTest * 118) + 114) ; //TO DO mettre en constantes
			//je regarde si j'ai la place d'inserer a droite
			int indexFilsDroit = raf.readInt() ;

			System.out.println("Index fils droit : " + indexFilsDroit);

			if (indexFilsDroit == -1)
			{
				//il y a la place : j'ajoute le nouveau Noeud
				
				//je change l'index de filsDroit avec le nouveau fils
				raf.writeInt(compteur);
				//je me place à la fin du fichier bin
				raf.seek(raf.length());
				//j'ajoute le fils à la fin du fichier 
				raf.writeChars(getNomLong(noeudAEcrire));
				raf.writeChars(getPrenomLong(noeudAEcrire));
				raf.writeChars(getDepartementLong(noeudAEcrire));
				raf.writeChars(getPromoLong(noeudAEcrire));
				raf.writeInt(noeudAEcrire.getStagiaire().getAnnee());
				raf.writeInt(-1);
				raf.writeInt(-1);
				compteur = compteur + 1 ;
				System.out.println("Compteur dans if fils droit : " + compteur);
			}
			else
			{
				//pas de place : je fais un appel recursif sur le noeud suivant
				//je demande au fils droit de s'en occuper
				ajouterStagiaireFilsDroiteDansFichierBin(indexFilsDroit, noeudAEcrire);
			}	
			raf.close();

	} 
	catch (IOException e) 
		{
			e.printStackTrace();
		}
	
	}
	
	
	public void creerArbreFichierBin(ArbreBinaire arbre, Noeud noeud)
	{
		
		System.out.println("Compteur methode creer arbre: " + compteur);
		
		if ((noeud.getFilsGauche() == null) && (noeud.getFilsDroit() == null)) // si il n'a pas de fils gauche ou droit
		{
			System.out.println("Feuille - pas d'enfants");
		}
			
		
		else 
		{
			//on regarde dans l'arbre binaire qu'on a deja créé s'il a un fils gauche
			if (noeud.getFilsGauche() != null)
			{
				System.out.println("Fils gauche");
				//s'il a un fils gauche, alors on appelle la methode pour l'ajouter dans le fichier binaire
				ajouterStagiaireFilsGaucheDansFichierBin(compteur, noeud.getFilsGauche());
				
				//on fait un appel recursif avec le fils gauche
				creerArbreFichierBin(arbre, noeud.getFilsGauche());
			}
			if (noeud.getFilsDroit() != null)
			{
				System.out.println("Fils droit");
				//s'il a un fils droit, alors on appelle la methode pour l'ajouter dans le fichier binaire
				ajouterStagiaireFilsDroiteDansFichierBin(compteur, noeud.getFilsDroit());
				creerArbreFichierBin(arbre, noeud.getFilsDroit());
			}
		
		}
		
		
	}
	
	//TO DO creer methode qui regroupe les 3 methodes precedentes

	
}
