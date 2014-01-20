package up5.mli630.tutore1314.cout;

import up5.mli630.tutore1314.Activite;

/**
 * 
 * le co�t des enseignements classiques :
 * des effectifs maximums sont pr�cis�s pour les cours , les tds et les tps
 * Exemple : 400 pour les cours, 40 pour les tds, 35 pour les tps
 * Le cout se d�duit du nombre de groupes de cours td et tp sachant qu'une heure de cours est compt�e pour 1,5h
 *
 */
public class CoutEnseignementClassique implements CalculCoutEnseignement {
// Les effectifs maximum des groupes de cours td et tp
private int effC,effTD,effTP;

public CoutEnseignementClassique(int effC, int effTD, int effTP) {
	super();
	this.effC = effC;
	this.effTD = effTD;
	this.effTP = effTP;
}

@Override
	public int getNbGroupesCours(float effectif) {
		return (int)(effectif-1)/this.effC+1;
	}

	@Override
	public int getNbGroupesTD(float effectif) {
		return (int) (effectif-1)/this.effTD+1;
	}

	@Override
	public int getNbGroupesTP(float effectif) {
		return  (int)(effectif-1)/this.effTP+1;
	}


	@Override
	public float getNbHeuresAPayer(float effectif,Activite activite) {
		return activite.getNbHC()*this.getNbGroupesCours(effectif)*1.5f  // 1 heure de cours est pay�e 1,5heures
				+ activite.getNbHTD()*this.getNbGroupesTD(effectif)
				+ activite.getNbHTP()*this.getNbGroupesTP(effectif);
	}

}
