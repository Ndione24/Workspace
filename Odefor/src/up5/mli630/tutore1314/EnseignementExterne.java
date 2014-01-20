package up5.mli630.tutore1314;

public class EnseignementExterne extends Enseignement {
	private float forfaitNbHParEtudiant;
	private float nbHC, nbHTD, nbHTP;

	public EnseignementExterne(String id, String shortName, String longName,
			float ects, float nbHC, float nbHTD, float nbHTP,
			float forfaitNbHParEtudiant) {
		super(id, shortName, longName, ects);
		this.forfaitNbHParEtudiant = forfaitNbHParEtudiant;
		this.nbHC = nbHC;
		this.nbHTD = nbHTD;
		this.nbHTP = nbHTP;
	}

	public float getForfaitNbHParEtudiant() {
		return forfaitNbHParEtudiant;
	}

	public float getNbHC() {
		return nbHC;
	}

	public float getNbHTD() {
		return nbHTD;
	}

	public float getNbHTP() {
		return nbHTP;
	}

	public float getNbHeuresAPayer(float effectif) {
		throw new RuntimeException("Non impl\u00E9ment\u00E9");
	}

	public float getNbHeuresEtudiants() {
		throw new RuntimeException("Non impl\u00E9ment\u00E9");
	}

}
