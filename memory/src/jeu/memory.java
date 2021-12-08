package jeu;

import jeu.cartes.carte.Carte;
import jeu.cartes.carte.PaquetCarte;
import jeu.cartes.carte.Symbole;

public class memory {
//procedure
	public static void main(String[] args) {
		System.out.println(Symbole.ARBRE);
		//new Controleur();
		Carte cVide = new Carte();
		Carte cMaisonBleue = new Carte(1,1,1);

		System.out.println(cVide);
		System.out.println(cMaisonBleue);

		PaquetCarte pc = new PaquetCarte();
		System.out.println(pc);
	} 

}
