package abalone.model;

public class Player {

	private String name;
	private boolean camps;
	private short score;
	private boolean human;

	public Player(String name, boolean camps, boolean human) {
		this.name = name;
		this.camps = camps;
		this.human = human;
	}

	/** Renvoie le nom du joueur */
	public String getName() {
		return name;
	}

	/** Renvoie le camps du joueur */
	public boolean getCamps() {
		return camps;
	}

	/** Renvoie le score du joueur */
	public short getScore() {
		return score;
	}

	/** Renvoie vrai si le joueur est un humain, faux si c'est un ordinateur */
	public boolean isHuman() {
		return human;
	}

	/** Change le nom du joueur */
	public void setName(String name) {
		this.name = name;
	}

	/** Change le camps du joueur */
	public void setCamps(boolean camps) {
		this.camps = camps;
	}

	/** Change le score du joueur */
	public void setScore(short score) {
		this.score = score;
	}

	/** Change le degré d'humanité du joueur */
	public void setHuman(boolean human) {
		this.human = human;
	}

}
