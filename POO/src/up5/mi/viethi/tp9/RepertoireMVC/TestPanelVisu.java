package up5.mi.viethi.tp9.RepertoireMVC;

public class TestPanelVisu {

	public static void main(String[] args) {
		// création d'un répertoire
		Repertoire repertoire = new Repertoire();
		// une fenetre de visualisation du répertoire
		final PanelVisuRepertoire panel = new PanelVisuRepertoire(repertoire);
		repertoire.addRepertoireListener(new RepertoireListener() {
			public void entreeAjoute(Repertoire repertoire, String nom, String tel) {
				panel.entreeAjoute();
			}
		});
		FrameUtil.createFrame("PanelVisu", panel);
		repertoire.ajouterEntree("thibault", "0600000000");
		repertoire.ajouterEntree("maeva", "0600000666");
	}
}
