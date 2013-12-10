package up5.mi.viethi.tp9.RepertoireMVC;

public class TestConsole {

	public static void main(String[] args) {
		// création du répertoire
		Repertoire repertoire = new Repertoire();

		// création d'un écouteur d'événements en provenance de ce répertoire
		RepertoireListener repertoireListener = new RepertoireListener() {
			@Override
			public void entreeAjoute(Repertoire repertoire, String nom,
					String tel) {
				System.out.println("Une nouvelle entrée pour le repertoire ! "
						+ nom + " : " + tel);
			}
		};
		// ajout de cet écouteur
		repertoire.addRepertoireListener(repertoireListener);

		// programme utilisant le répertoire (très rudimentaire pour cet
		// exemple)
		repertoire.ajouterEntree("Thibault", "0600000000");
	}

}
