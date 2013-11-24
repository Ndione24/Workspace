package up5.mi.viethi.tp5.IGSecret;

public class Jeu {
	/** le nombre minimum a trouver */
	private int minInitial;
	/** le nombre maximum a trouver */
	private int maxInitial;
	/** au cours d'un jeu, le minimum deduit à partir des reponses precedentes */
	private int min;
	/** au cours d'un jeu, le maximum deduit à partir des reponses precedentes */
	private int max;

	private int nbPropositions;

	private boolean gagne = false;

	public Jeu(int minInitial, int maxInitial) {

		this.minInitial = minInitial;
		this.maxInitial = maxInitial;
		reset();
	}

	/** le jeu est redemarre */
	public void reset() {

		this.min = minInitial;
		this.max = maxInitial;
		this.gagne = false;
		this.nbPropositions = 0;
	}

	/** @return true si le jeu est sans solution */
	public boolean isJeuSansSolution() {

		return this.min > this.max;
	}

	/** @return true si le joueur a gagne */
	public boolean isGagne() {

		return this.gagne;
	}

	/* retourne la proposition de l'ordinateur */
	public int getProposition() {

		return (this.min + this.max) / 2;

	}

	public int getNbPropositions() {
		return this.nbPropositions;
	}

	/* rajoute 1 au minimum */
	public void noterPropositionTropPetite() {

		this.nbPropositions++;
		this.min = this.getProposition() + 1;

	}

	/* enleve 1 au maximum */
	public void noterPropositionTropGrande() {

		this.nbPropositions++;
		this.max = this.getProposition() - 1;
	}

	/*
	 * le maximum et le minimum prend la valeur du chiffre recherche qui vient
	 * d'etre trouve
	 */
	public void noterPropositionOK() {

		this.nbPropositions++;
		this.min = this.getProposition();
		this.max = this.min;
		this.gagne = true;
	}
}