package jeu.cartes.carte;

public enum Symbole {

	MAISON("home"),DOS("dos"), SOLEIL("sun"), CHAT("cat"), OURS("ours"), CHIEN("dog"),	
	ETOILE("star"), NEIGE("snow"), GREEN("gris"), LAC("lac"), ARBRE("tree"), POISSON("fish"), 
	POMME("verte"), VOITURE("car"),	AVION("plane"),	BATEAU("boat"), CHEVAL("horse"), STYLO("pen"), MER("sea"), VIDE("vide");


	private static final Symbole[] LES_SYMBOLES = Symbole.values();
	public static final int NBR_SYMBOLES = LES_SYMBOLES.length;

	private String texte;
	
	private Symbole(String string) {
		this.texte=string;
	}

	@Override
	public String toString() {
		return this.texte;
	}

	public static Symbole get(int indiceMotif) {
		return LES_SYMBOLES[indiceMotif-1];
	}

}