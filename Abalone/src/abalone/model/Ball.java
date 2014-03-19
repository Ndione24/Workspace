package abalone.model;

public class Ball {

	/** Ligne et colonne du plateau sur laquelle se trouve la bille */
	private byte line;
	private byte column;
	/** Joueur auquel appartient la bille */
	Player player;

	/**
	 * Constructeur d'une bille
	 * @param line		Ligne de la bille sur le plateau
	 * @param column	Colonne de la bille sur le plateau
	 * @param player	Joueur auquel appartient la bille
	 */
	public Ball(byte line, byte column, Player player) {
		this.line = line;
		this.column = column;
		this.player = player;
	}

	/** Renvoie la colonne de la bille sur le plateau */
	public byte getColumn() {
		return column;
	}

	/** Renvoie la ligne de la bille sur le plateau */
	public byte getLine() {
		return line;
	}

	/** Renvoie le joueur auquel appartient la bille */
	public Player getPlayer() {
		return player;
	}

	/** Modifie le joueur auquel appartient la bille */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/** Modifie la ligne de la bille sur le plateau */
	public void setLine(byte line) {
		this.line = line;
	}

	/** Modifie la colonne de la bille sur le plateau */
	public void setColumn(byte column) {
		this.column = column;
	}
	
	/** Renvoie les informations détaillées de la bille */
	private String getData() {
		return "(" + this.getLine() + "," + this.getColumn() + ") "
				+ (player.getCamps() ? "J2" : "J1");
	}
	
	public String toString() {
		String str = "o";
		str = (player.getCamps() ? "+" : "-");
		return str;
	}

}
