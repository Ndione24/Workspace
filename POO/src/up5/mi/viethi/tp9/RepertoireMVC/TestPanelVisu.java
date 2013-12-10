package up5.mi.viethi.tp9.RepertoireMVC;

public class TestPanelVisu {
	// création d'un chat
	Repertoire repertoire = new Repertoire();
	// une fenetre de visualisation du chat
	final PanelVisuRepertoire panel = new PanelVisuRepertoire(repertoire);
	
	RepertoireListener repertoireListener = new RepertoireListener() {
		public void entreeAjoute(Repertoire repertoire, String nom, String tel) {
			panel.entreeAjoute(nom, tel);
		}
	};
	
	/* Erreur : repertoire.addRepertoireListener(repertoireListener); */ 
	
}
