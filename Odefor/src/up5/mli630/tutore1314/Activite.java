package up5.mli630.tutore1314;

import up5.mli630.tutore1314.cout.CalculCoutEnseignement;

public class Activite {

	private float nbHC,nbHTD,nbHTP;
	private CalculCoutEnseignement calculCoutEnseignement;

/**
 * @param nbHC le nombre d'heure de cours (exemple 12*1h30 = 18H)
 * @param nbHTD le nombre d'heure de tds
 * @param nbHTP le nombre d'heure de tps (exemple : 12*3h = 36h)
 * @param calculCoutEnseignement le mode de calcul du cout associé à cet enseignement
 */
	public Activite(float nbHC,float nbHTD, float nbHTP,CalculCoutEnseignement calculCoutEnseignement) {
		this.nbHC = nbHC;
		this.nbHTD = nbHTD;
		this.nbHTP = nbHTP;
		this.calculCoutEnseignement=calculCoutEnseignement;
	}

	public float getNbHC() {
		return nbHC;
	}

	public float getNbHTD() {
		return nbHTD;
	}

	public float getNbHTP() {
		return nbHTP;
	}
	
	public float getNbHeuresAPayer(float effectif){
		return calculCoutEnseignement.getNbHeuresAPayer(effectif, this);
	}
	
	public float getNbHeuresEtudiants(){
		return getNbHC()+getNbHTD()+getNbHTP();
	}
	

}
