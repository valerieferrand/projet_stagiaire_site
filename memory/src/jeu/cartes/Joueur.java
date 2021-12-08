package jeu.cartes;
import jeu.cartes.carte.Carte;
import jeu.cartes.carte.PaquetCarte;
public class Joueur {
	public static final int PENALITE=3;

	private final String nom;
	private PaquetCarte pc;
	private int penalite=0;

	//constructeur
	public Joueur(String nom, PaquetCarte pc) {
		super();
		this.penalite=0;
		this.nom = nom;
		this.pc = pc;
	}

	//methodes
	public Carte<?> get(int i) {
		return pc.get(i);
	}
	public String getNom() {
		return nom;

	}
	public void annulerPenalite() {
		this.penalite=0;
	}

	public void ajoutPenalite() {
		this.penalite=PENALITE;
	}

	public void oterEventuellementUnePenalite() {
		if (this.penalite>0) {
			this.penalite--;
		}
	}	
}
