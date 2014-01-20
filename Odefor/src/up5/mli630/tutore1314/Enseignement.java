package up5.mli630.tutore1314;

public abstract class Enseignement extends Element {

	private float ects;

	/**
	 * @param id
	 *            l'identifiant de l'enseignement (exemple : MLI530)
	 * @param shortName
	 *            le nom court de l'enseignement (exemple : POO)
	 * @param longName
	 *            le nom long de l'enseignement (exemple : programmation
	 *            orient�e objet avanc�e)
	 */
	public Enseignement(String id, String shortName, String longName, float ects) {
		super(id, shortName, longName);
		this.ects = ects;
	}

	public float getEcts() {
		return this.ects;
	}

	public float getCoeff() {
		throw new RuntimeException("Non impl\u00E9ment\u00E9");
	}

}
