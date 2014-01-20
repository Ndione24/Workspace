package up5.mli630.tutore1314.maquette;

/* structuration de haut niveau : 
 * les parcours (eventuellement unique)
 * les semestres (eventuellement un seul si annualis�  ...)
 * � l'int�rieur de chaque semestre, le bloc d'UE principal (un bloc d'UE est d�fini par : des UE facultatives, des UE obligatoires, des UE � choisir, des BLOC d'UE � choisir)
 * � l'int�rieur des UE, le bloc d'ECUE principal (un bloc d'ECUE est d�fini par : des ECUE obligatoires, des ECUE � choisir, des BLOC d'ECUE � choisir)
 * pour chaque ECUE le ou les enseignements correspondants
 */
public class AnneeDeFormation extends Choix<Parcours> {

	private int number;

	public AnneeDeFormation(String name, int number) {
		super(name, name, name, 1);
		this.number = number;
	}

	public int getNumeroAnnee() {
		return number;
	}

}
