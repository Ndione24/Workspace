package up5.mli630.tutore1314.cout;

import up5.mli630.tutore1314.Activite;

public interface CalculCoutEnseignement {

	/**
	 * rend le nombre de groupes de cours nécessaires compte tenu de l'effectif
	 * @param effectif le nombre d'étudiants concernés
	 * @return le nombre de groupes de cours nécessaires
	 */
    public abstract int getNbGroupesCours(float effectif);

	/**
	 * rend le nombre de groupes de td nécessaires compte tenu de l'effectif
	 * @param effectif le nombre d'étudiants concernés
	 * @return le nombre de groupes de td nécessaires
	 */
    public abstract int getNbGroupesTD(float effectif);

	/**
	 * rend le nombre de groupes de tp nécessaires compte tenu de l'effectif
	 * @param effectif le nombre d'étudiants concernés
	 * @return le nombre de groupes de tp nécessaires
	 */
    public abstract int getNbGroupesTP(float effectif);

    /**
     *
     * @param f le nombre d'étudiants concernés
     * @param activite l'enseignement concerné
     * @return le nombre total d'heures à payer aux enseignants
     */
    public abstract float getNbHeuresAPayer(float f,Activite activite);

}
