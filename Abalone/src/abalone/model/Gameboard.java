package abalone.model;

import abalone.controller.GameController;

public class Gameboard {

	/** Un tableau de jeu est une matrice de bille */
	private Ball[][] gameboard;
	
	/** Initialise le plateau de jeu */
	public Gameboard() {
		this.gameboard = new Ball[9][9];
	}
	
	/** Renvoie la bille placee sur le plateau a la position x,y */
	public Ball getBall(int line, int column) {
		if (!GameController.isOut(line, column)) 
			return this.gameboard[line][column];
		else return null;
	}
	
	/** Modifie la position d'une bille sur le plateau */
	public void setBall(Ball ball, byte line, byte column) {
		this.gameboard[line][column] = ball;
		if (ball != null) {
			ball.setLine(line);
			ball.setColumn(column);
		}
	}
	
	/** Teste si une case est vide. */
	public boolean isEmpty(int ligne, int colonne) {
		try {
			return this.gameboard[ligne][colonne] == null;
		} catch(ArrayIndexOutOfBoundsException e) {
			System.err.println("Erreur : coordonnees hors plateau");
			return false;
		}
	}
	
	/** Renvoie le plateau de jeu */
	public Ball[][] getGameboard() {
		return this.gameboard;
	}

	/** Définie le plateau de jeu */
	public void setGameboard(Ball[][] gameboard) {
		this.gameboard = gameboard;
	}
	
	/** Affiche l'etat du plateau */
	public String toString() {
		String str = "";
		for(int i=0; i < 9; i++) {
			for(int j=0; j < 9; j++) {
				if (isEmpty(i,j)) str+="o";
				else str+= getBall(i,j).toString();
			}
			str+="\n";
		}
		return str;
	}
}
