package up5.mli630.tutore1314.cout;

import up5.mli630.tutore1314.Activite;

public interface CalculCoutEnseignement {
	
	/**
	 * rend le nombre de groupes de cours n�cessaires compte tenu de l'effectif
	 * @param effectif le nombre d'�tudiants concern�s
	 * @return le nombre de groupes de cours n�cessaires
	 */
    public abstract int getNbGroupesCours(float effectif);
    
	/**
	 * rend le nombre de groupes de td n�cessaires compte tenu de l'effectif
	 * @param effectif le nombre d'�tudiants concern�s
	 * @return le nombre de groupes de td n�cessaires
	 */
    public abstract int getNbGroupesTD(float effectif);
    
	/**
	 * rend le nombre de groupes de tp n�cessaires compte tenu de l'effectif
	 * @param effectif le nombre d'�tudiants concern�s
	 * @return le nombre de groupes de tp n�cessaires
	 */
    public abstract int getNbGroupesTP(float effectif);
    
    /**
     * 
     * @param f le nombre d'�tudiants concern�s
     * @param enseignement l'enseignement concern�
     * @return le nombre total d'heures � payer aux enseignants 
     */
    public abstract float getNbHeuresAPayer(float f,Activite activite);
     
}
