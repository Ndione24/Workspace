package up5.mi.viethi.tp9.RepertoireMVC;

import javax.swing.JPanel;

public class ControleurBasique {
	
	private final Repertoire repertoire = new Repertoire();
	
	public ControleurBasique() {
		FrameUtil.createFrame("PanelVisu", createPanelRepertoire());
	}
	
	protected Repertoire getRepertoire() { return this.repertoire; }
	
	private JPanel createPanelRepertoire() {
		final PanelRepertoire panel = new PanelRepertoire(repertoire);
		repertoire.addRepertoireListener(new RepertoireListener() {
			public void entreeAjoute(Repertoire repertoire, String nom, String tel) {
				panel.entreeAjoute();
			}
		});
		// abonnement du controleur aux événements de l'interface graphique
		panel.addPanelRepertoireListener(new PanelRepertoireListener() {
			public void contactAEnvoyer(String nom, String tel) {
				// dans un cas réel, on ferait appel ici au serveur de chat ...
				// on se contente de modifier le modèle
				repertoire.ajouterEntree(nom, tel);
			}
		});
		return panel;
	}
	
	public static void main(String[] args) {
		new ControleurBasique();
	}
}
