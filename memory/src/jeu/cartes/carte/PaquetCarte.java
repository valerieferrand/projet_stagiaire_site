package jeu.cartes.carte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PaquetCarte {

	private static final int NBR_CARTES=20;
	@SuppressWarnings("rawtypes")
	private List<Carte> cartes = new ArrayList<Carte>();

	public PaquetCarte() {
		super();
		Carte<?> c = null;
		for (int indiceValeur = 1; indiceValeur <= Carte.NBR_VALEURS; indiceValeur++){
			for (int indiceCouleur = 1; indiceCouleur <= Carte.NBR_COULEURS; indiceCouleur++) {
				for (int indiceMotif = 1; indiceMotif <= Carte.NBR_MOTIFS; indiceMotif++) {

					c = new Carte();
					System.out.println("la carte créée est"+c);
					cartes.add(c);
					System.out.println("la liste de cartes en cours de création:"+cartes);
				}
			}
		}
		Collections.shuffle(cartes);
		int nbBoucles = cartes.size()-NBR_CARTES;

		for (int i =0; i < nbBoucles; i++) {
			cartes.remove(0);
		}
	}
	public PaquetCarte(PaquetCarte p1, int n) {
		super();
		if (n<p1.size()) {
			for(int i = 0; i < n; i++) {
				this.add(p1.remove(0));
			}
		}
		else {
			System.out.println("Problème de taille du paquet!! ");
		}
	}
	private int size() {

		return cartes.size();
	}
	private boolean add(@SuppressWarnings("rawtypes") Carte carte)	{
		return cartes.add(carte);
	}

	@SuppressWarnings("rawtypes")
	public Carte get(int i) {
		return cartes.get(i);
	}
	@SuppressWarnings("rawtypes")
	private Carte remove(int i) {
		return cartes.remove(i);
	}


	@Override
	public String toString() {
		return cartes.size()+" / Paquet [cartes=" + cartes + "]";
	}
}

