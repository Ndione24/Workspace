package up5.mli630.tutore1314.cout;

import up5.mli630.tutore1314.Activite;

/** le cout des enseignements dont la rémunération dépend directement du nombre d'étudiants
 * C'est souvent le cas des UE stage, mémoire, projets
 * Il n'y a alors pas de groupes de cours tds ou tp
 * */
public class CoutProjetStageMemoire implements CalculCoutEnseignement {

private float forfaitNbHParEtudiant;

public CoutProjetStageMemoire(float forfaitNbHParEtudiant) {
	super();
	this.forfaitNbHParEtudiant = forfaitNbHParEtudiant;
}

@Override
	public int getNbGroupesCours(float effectif) {
		return 0;
	}

	@Override
	public int getNbGroupesTD(float effectif) {
		return 0;
	}

	@Override
	public int getNbGroupesTP(float effectif) {
		return 0;
	}

	@Override
	public float getNbHeuresAPayer(float effectif,Activite activite) {
		return effectif*this.forfaitNbHParEtudiant;
	}

}
