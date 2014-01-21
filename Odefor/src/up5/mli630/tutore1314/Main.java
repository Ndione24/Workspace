package up5.mli630.tutore1314;

import up5.mli630.tutore1314.cout.CalculCoutEnseignement;
import up5.mli630.tutore1314.cout.CoutEnseignementClassique;
import up5.mli630.tutore1314.cout.CoutProjetStageMemoire;
import up5.mli630.tutore1314.maquette.AnneeDeFormation;
import up5.mli630.tutore1314.maquette.ECUE;
import up5.mli630.tutore1314.maquette.Parcours;
import up5.mli630.tutore1314.maquette.Periode;
import up5.mli630.tutore1314.maquette.UE;

public class Main {

	public static void main(String[] args) {
		CalculCoutEnseignement cout = new CoutEnseignementClassique(400, 40, 35);
		CoutProjetStageMemoire cpms = new CoutProjetStageMemoire(2);
		Enseignement ens1 = new EnseignementNormal(
				"MLI530", "POO", 
				"Programmation Oriente Objet",
				18, 0, 36, 15, cout);
		Enseignement ens2 = new EnseignementNormal("MLI330", 
				"BD",
				"Base de données",
				18, 24, 0, 20, cout);
		Enseignement ens3 = new EnseignementNormal("MLI630", 
				"Projets",
				"Projets tutorés", 
				0, 84, 0, 30, cpms);

		AnneeDeFormation annee = new AnneeDeFormation(
				"Licence informatique 3éme année", 3);
		Parcours parcoursInfo = new Parcours("MLICPINF",
				"Parcours informatique troisiéme année",
				"Parcours informatique troisiéme année");
		Parcours parcoursMiage = new Parcours("MLICPMIA",
				"Parcours miage troisiéme année",
				"Parcours miage troisiéme année");

		annee.ajouterChoix(parcoursInfo, 0.5f);
		annee.ajouterChoix(parcoursMiage, 0.5f);

		Periode periode5 = new Periode("MLI5SINF", "S5 parcours informatique",
				"S5 parcours informatique", 5);
		parcoursInfo.addChild(periode5);
		Periode periode6 = new Periode("MLI6SINF", "S6 parcours informatique",
				"S6 parcours informatique", 6);
		parcoursInfo.addChild(periode6);

		UE ue1 = new UE("MLI530", "POO", "POO");
		periode5.addObligatoire(ue1);

		ECUE ecue1 = new ECUE(ens1, 5, 5);
		ue1.addObligatoire(ecue1);

		// System.out.println(annee.getNbHeuresEtudiants());

	}
}