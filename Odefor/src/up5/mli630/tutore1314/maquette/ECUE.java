package up5.mli630.tutore1314.maquette;

import up5.mli630.tutore1314.Element;
import up5.mli630.tutore1314.Enseignement;

public class ECUE extends Element {

	private Enseignement enseignement;

	private float ects;
	private float coeff;

	public ECUE(Enseignement enseignement, float ects, float coeff) {
		super(enseignement.getId(), enseignement.getShortName(), enseignement
				.getLongName());
		this.enseignement = enseignement;
		this.ects = ects;
		this.coeff = coeff;
	}

	public float getNbHeuresAPayer(float effectif) {
		throw new RuntimeException("Non impl�ment�");
	}

	@Override
	public float getNbHeuresEtudiants() {
		return enseignement.getNbHeuresEtudiants();
	}

	public Enseignement getEnseignement() {
		return enseignement;
	}

	public float getEcts() {
		return ects;
	}

	public float getCoeff() {
		return coeff;
	}

}
