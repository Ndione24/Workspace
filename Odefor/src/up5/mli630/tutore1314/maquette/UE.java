package up5.mli630.tutore1314.maquette;

public class UE extends Bloc<ECUE> {
	private float ects;
	private float coeff;

	public UE(String id, String shortName, String longName, float ects,
			float coeff) {
		super(id, shortName, longName);
		this.ects = ects;
		this.coeff = coeff;
	}

	public UE(String id, String shortName, String longName, float coeff) {
		this(id, shortName, longName, -1, coeff);
	}

	public UE(String id, String shortName, String longName) {
		this(id, shortName, longName, -1, -1);
	}

	public float getEcts() {
		throw new RuntimeException("Non implémenté");
	}

	public float getCoeff() {
		throw new RuntimeException("Non implémenté");
	}

}
