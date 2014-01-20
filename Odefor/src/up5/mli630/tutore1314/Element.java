package up5.mli630.tutore1314;

public abstract class Element {

	private String id, shortName, longName;

	public Element(String id, String shortName, String longName) {
		super();
		this.id = id;
		this.shortName = shortName;
		this.longName = longName;
	}

	public String getId() {
		return id;
	}

	public String getShortName() {
		return shortName;
	}

	public String getLongName() {
		return longName;
	}

	public abstract float getCoeff();

	public abstract float getEcts();

	public abstract float getNbHeuresEtudiants();

}
