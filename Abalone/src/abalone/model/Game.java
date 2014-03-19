package abalone.model;

/**
 * Game est une classe qui regroupe toutes les information du modele sur la partie en cours.
 * 
 * Une partie est caracterisee par les informations suivantes :
 * Deux joueurs, dont un peut etre l'ordinateur lui-meme.
 * Un plateau, representant le plateau de jeu, et ses cases.
 * 
 * Des coups, qui representent la sequence de coup joues depuis le debut de la partie.
 * 
 * @author melkir
 *
 */
public class Game {
	/** Le plateau */
	private Gameboard gameboard;
	/** Les joueurs de la partie */
	private Player j1, j2;
	/** Le dernier joueur a avoir joue */
	private Player jCur;
	
	public Game() {
		// TODO Auto-generated constructor stub
	}

}
