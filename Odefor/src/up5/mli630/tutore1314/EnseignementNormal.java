package up5.mli630.tutore1314;

import java.util.ArrayList;
import java.util.List;

import up5.mli630.tutore1314.cout.CalculCoutEnseignement;

public class EnseignementNormal extends Enseignement {

	private List<Activite> activites = new ArrayList<Activite>();

	public void ajoutActivite(Activite activite) {
		this.activites.add(activite);
	}

	public EnseignementNormal(String id, String shortName, String longName,
			float ects) {
		super(id, shortName, longName, ects);
	}

	public EnseignementNormal(String id, String shortName, String longName,
			float ects, float nbHC, float nbHTD, float nbHTP,
			CalculCoutEnseignement calculCoutEnseignement) {
		this(id, shortName, longName, ects);
		this.ajoutActivite(new Activite(nbHC, nbHTD, nbHTP,
				calculCoutEnseignement));
	}

	public float getNbHeuresAPayer(float effectif) {
		throw new RuntimeException("Non impl\u00E9ment\u00E9");
	}

	public float getNbHeuresEtudiants() {
		throw new RuntimeException("Non impl\u00E9ment\u00E9");
	}

}
