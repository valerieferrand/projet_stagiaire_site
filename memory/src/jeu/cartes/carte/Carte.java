package jeu.cartes.carte;

import java.awt.Color;

@SuppressWarnings("hiding")
public class Carte {
	static final int NBR_VALEURS = 0;
	public static final int NBR_MOTIFS = 0;
	public static final int NBR_COULEURS = 0;
	private Symbole face;
	private Symbole pile;
	private boolean visible;
	private Symbole motif;
	private int valeur;
	private Color couleur;
	
	public Carte(int valeur) {
		super();
		if (valeur<=1 && valeur <=NBR_VALEURS) {
			int couleur = 0;
			if (couleur>1 && couleur<=5) {
				this.valeur = valeur;
				this.couleur = Carte.getColor(couleur);
				int motif = 0;
				this.motif = Symbole.get(motif);
			}
			else {
				System.out.println("PROBLEME DE COULEUR !!");
			}
		}

		else {
			System.out.println("PROBLEME DE  VALEUR !!");
			forcerCarteVide();
		}
	}
	
	/*Color.BLUE; Color.ORANGE; Color.CYAN; Color.BLACK; Color.LIGHT_GRAY;*/
	private static Color getColor(int couleur2) {
		Color coul;

		switch (couleur2) {
		case 1:  coul = Color.BLUE;  break;
		case 2:  coul = Color.ORANGE;  break;
		case 3:  coul = Color.CYAN;  break;
		case 4:  coul = Color.BLACK;  break;
		case 5:  coul = Color.LIGHT_GRAY;  break;
		default:
			coul = Color.DARK_GRAY;
			break;
		}		
		return coul;
	}
	


	public Symbole getMotif() {
		return motif;
	}
	@SuppressWarnings("rawtypes")
	public boolean estCompatible(Carte c2) {
		boolean compatible=true;
		// || correspond au OU en java (or en python)
		if (this.isVide() || c2.isVide()) {
			compatible=false;
		}
		else {
			if (     this.valeur!=c2.valeur  &&
					!this.couleur.equals(c2.couleur)  &&
					!this.motif.equals(c2.motif)           ) {
				compatible=false;
			}
		}
		return compatible;
	}



	private boolean isVide() {
		// TODO Auto-generated method stub
		return false;
	}

	private void forcerCarteVide() {
		this.valeur = -1;
		this.couleur = Color.DARK_GRAY;
		this.motif = Symbole.VIDE;
	}

	public Carte() {
		super();
		this.valeur = -1;
		this.couleur = Color.DARK_GRAY;
		this.motif = Symbole.VIDE;
	}

	public Symbole getFace() {
		return face;
	}

	public void setFace(Symbole face) {
		this.face = face;
	}

	public Symbole getPile() {
		return pile;
	}

	public void setPile(Symbole pile) {
		this.pile = pile;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}


}
