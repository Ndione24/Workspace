package up5.mi.viethi.tp9.RepertoireMVC;

public interface RepertoireListener {
	/** Un nouveau contact a été ajouté dans le carnet */
	void entreeAjoute(Repertoire repertoire, String nom, String tel);
}
