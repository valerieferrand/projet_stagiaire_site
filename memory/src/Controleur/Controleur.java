package Controleur;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ihm.FenetreHote;
import ihm.Touches;
import jeu.cartes.Joueur;
import jeu.cartes.carte.PaquetCarte;



public class Controleur<ihm> implements KeyListener {

	private static final int ERREUR_FRAPPE = KeyEvent.VK_A;
	private static final String Touches = null;
	private static final int TOUCHE1_J1 = Touches.TOUCHE1_J1;
	private static final int TOUCHE2_J1 = Touches.TOUCHE2_J1;
	private static final int TOUCHE3_J1 = Touches.TOUCHE3_J1;
	private static final int TOUCHE1_J2 = Touches.TOUCHE1_J2;
	private static final int TOUCHE2_J2 = Touches.TOUCHE2_J2;
	private static final int TOUCHE3_J2 = Touches.TOUCHE3_J2;

	private static final int POS_C1_J1 = 3;
	private static final int POS_C2_J1 = 4;
	private static final int POS_C3_J1 = 5;
	private static final int POS_C1_J2 = 6;
	private static final int POS_C2_J2 = 7;
	private static final int POS_C3_J2 = 8;

	private static Joueur joueur1;
	private static Joueur joueur2;
	private static Carte sommet1;
	private static Carte sommet2;

	private static boolean stop = false;

	private IOKeyBoardConsole ihm ;

	/**
	 * Le constructeur du contr�leur initialise la partie (deux joueurs = deux paquets) et deux sommets de tas.
	 * Puis, il cr�e une fen�tre h�te et lui transmet l'information � afficher.
	 */
	public Controleur() {		
		super();
		PaquetCarte pc = new PaquetCarte();
		Joueur Joueur = new Joueur("joueur 1",pc);
		PaquetCarte pc2 =new PaquetCarte(pc, pc.size()/2);
		joueur2 = new Joueur("joueur 2", pc2);

		sommet1=joueur1.remove(0);
		sommet2=joueur2.remove(0);

		ihm = new FenetreHote();
		this.afficherCartes();

		ihm.setNoms(joueur1.getNom(), joueur2.getNom());

		/* Activer le contr�leur de touche frapp�e */
		ihm.addKeyListener(this);
	}

	private void afficherCartes() {
		ihm.setSommets(sommet1.getMotif().toString(), Controleur.getCouleur(sommet1), sommet1.getValeur(), sommet2.getMotif().toString(), getCouleur(sommet2), sommet2.getValeur());
		Carte carte1=joueur1.get(0);
		Carte carte2=joueur1.get(1);
		Carte carte3=joueur1.get(2);
		ihm.setCartesJoueur1(carte1.getMotif().toString(), getCouleur(carte1), carte1.getValeur(), carte2.getMotif().toString(), getCouleur(carte2), carte2.getValeur(), carte3.getMotif().toString(), getCouleur(carte3), carte3.getValeur());
		carte1=joueur2.get(0);
		carte2=joueur2.get(1);
		carte3=joueur2.get(2);
		ihm.setCartesJoueur2(carte1.getMotif().toString(), getCouleur(carte1), carte1.getValeur(), carte2.getMotif().toString(), getCouleur(carte2), carte2.getValeur(), carte3.getMotif().toString(), getCouleur(carte3), carte3.getValeur());		
	}

	private static Color getCouleur(Carte carte) {
		return carte.getCouleur();
	}

	/**
	 * Cette proc�dure indique la marche � suivre quand une touche est enfonc�e
	 */
	public void keyPressed (final KeyEvent e)
	{
		// La gestion du bool�en stop permet d'�viter de garder une touche enfonc�e		
		if (!stop) {
			stop = true;
			int toucheFrappee=e.getKeyCode();
			System.out.println("sommet 1 ="+sommet1);
			System.out.println("sommet 2 ="+sommet2);
			System.out.println("joueur 1 ="+joueur1);
			System.out.println("joueur 2 ="+joueur2);
			/* La fonction getPositionCarte permet de transformer une touche frapp�e,
			un caract�re, en une des positions figur�es sur la table de jeu.
			 */

			int position = Controleur.getPositionCarte(toucheFrappee);

			/*
			position indique quelle carte a �t� choisie par le clavier.
			Les cartes du joueur 1 sont en position 3-5,
			celles du joueur 2 en position 6-8

			Avant de prendre en compte le choix d'une carte, on v�rifie qu'elle
			est compatible avec la carte du sommet d'un des tas. Si c'est le cas,
			on la supprime de la main du joueur et on la place sur le sommet du tas.

			Il est possible que les deux tas puissent accueillir la carte jou�e.
			Le programme privil�gie le tas 1 pour le joueur 1 et le tas 2 pour le joueur 2 
			 */
			switch (position) {
			case POS_C1_J1 :
				// nouveauSommet va prendre la valeur de la 1�re carte du joueur 1 si celle-ci peut �tre pos�e sur le sommet 1 courant.
				// nouveauSommet reste null sinon.
				Carte nouveauSommet = joueur1.testerCarteSommet(0,sommet1,joueur2); 
				if (nouveauSommet==null) {
					// nouveauSommet va prendre la valeur de la 1�re carte du joueur 1 si celle-ci peut �tre pos�e sur le sommet 2 courant.
					// nouveauSommet reste null sinon.
					nouveauSommet = joueur1.testerCarteSommet(0,sommet2,joueur2);
					if (nouveauSommet==null) {
						// le joueur a fait une erreur, il va �tre p�nalis� et le joueur 2 va perdre toute �ventuelle p�nalit�
						joueur1.gererErreur(joueur2);
					}
					else {
						// la carte va sur le sommet 2 et a �t� �t�e du jeu du joueur 1
						sommet2=nouveauSommet;
					}
				}
				else {
					// la carte va sur le sommet 1 et a �t� �t�e du jeu du joueur 1
					sommet1=nouveauSommet;
				}
				break;

			case POS_C2_J1 :			
				nouveauSommet = joueur1.testerCarteSommet(1,sommet1,joueur2); 
				if (nouveauSommet==null) {
					nouveauSommet = joueur1.testerCarteSommet(1,sommet2,joueur2);
					if (nouveauSommet==null) {
						joueur1.gererErreur(joueur2);
					}
					else {
						sommet2=nouveauSommet;
					}
				}
				else {
					sommet1=nouveauSommet;
				}

				break;
			case POS_C3_J1 :	
				nouveauSommet = joueur1.testerCarteSommet(2,sommet1,joueur2); 
				if (nouveauSommet==null) {
					nouveauSommet = joueur1.testerCarteSommet(2,sommet2,joueur2);
					if (nouveauSommet==null) {
						joueur1.gererErreur(joueur2);
					}
					else {
						sommet2=nouveauSommet;
					}
				}
				else {
					sommet1=nouveauSommet;
				}


				break;
			case POS_C1_J2 :
				nouveauSommet = joueur2.testerCarteSommet(0,sommet1,joueur1); 
				if (nouveauSommet==null) {
					nouveauSommet = joueur2.testerCarteSommet(0,sommet2,joueur1);
					if (nouveauSommet==null) {
						joueur2.gererErreur(joueur1);
					}
					else {
						sommet2=nouveauSommet;
					}
				}
				else {
					sommet1=nouveauSommet;
				}

				break;
			case POS_C2_J2:
				nouveauSommet = joueur2.testerCarteSommet(1,sommet1,joueur1); 
				if (nouveauSommet==null) {
					nouveauSommet = joueur2.testerCarteSommet(1,sommet2,joueur1);
					if (nouveauSommet==null) {
						joueur2.gererErreur(joueur1);
					}
					else {
						sommet2=nouveauSommet;
					}
				}
				else {
					sommet1=nouveauSommet;
				}
				break;
			case POS_C3_J2 :	
				nouveauSommet = joueur2.testerCarteSommet(2,sommet1,joueur1); 
				if (nouveauSommet==null) {
					nouveauSommet = joueur2.testerCarteSommet(2,sommet2,joueur1);
					if (nouveauSommet==null) {
						joueur2.gererErreur(joueur1);
					}
					else {
						sommet2=nouveauSommet;
					}
				}
				else {
					sommet1=nouveauSommet;
				}
				break;
			default: // C'�tait une mauvaise touche ou la touche d'un joueur bloqu�
				break;
			}
			// les mains sont �ventuellement compl�t�es par des cartes vides pour toujours pouvoir afficher 3 cartes, ce qui simplifie la lecture du code et permet de varier les constructeurs sur ce TP
			if (joueur1.size()==2) {				joueur1.add(new Carte());			}
			if (joueur2.size()==2) {				joueur2.add(new Carte());			}
			if (joueur1.gagne()) {ihm.setGagnant(joueur1.getNom());}
			if (joueur2.gagne()) {ihm.setGagnant(joueur2.getNom());}

			this.afficherCartes();

			// Cet appel permet de r�afficher l'�tat actuel du jeu.
			ihm.repaint();
		}
	}

	/**
	 * Cette proc�dure indique la marche � suivre lorsqu'une touche est rel�ch�e.
	 */
	public void keyReleased (final KeyEvent e)
	{

		//code pour un key released  

		// Si personne n'a gagn�, on permet de retaper une touche au clavier -stop passe � faux-
		// sinon, la partie est termin�e.
		if (!joueur1.gagne() && !joueur2.gagne()) {stop=false;}
	}

	public void keyTyped (final KeyEvent e)
	{
		//code pour un key typed  
	} 



	/**
	 * La touche est prise en compte si le joueur n'a pas de penalite. 
	 * @param toucheFrappee
	 * @return
	 */
	private static int getPositionCarte(final int toucheFrappee) {
		int rep = ERREUR_FRAPPE;
		if (joueur1.sansPenalite()) {
			switch (toucheFrappee) {
			case TOUCHE1_J1 : rep = POS_C1_J1;			break;
			case TOUCHE2_J1 : rep = POS_C2_J1;			break;
			case TOUCHE3_J1 : rep = POS_C3_J1;			break;
			default: break;
			}
		}
		if (joueur2.sansPenalite()) {
			switch (toucheFrappee) {
			case TOUCHE1_J2 : rep = POS_C1_J2;			break;
			case TOUCHE2_J2 : rep = POS_C2_J2;			break;
			case TOUCHE3_J2 : rep = POS_C3_J2;			break;
			default: break;
			}
		}

		return rep;
	}


}
