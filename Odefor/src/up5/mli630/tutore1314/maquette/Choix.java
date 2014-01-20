package up5.mli630.tutore1314.maquette;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import up5.mli630.tutore1314.Element;

public class Choix <E extends Element> extends Element{

private List<E> elements = new ArrayList<E>();
private Map<E,Float> importances=new HashMap<E,Float>();

private int nbChoix;

public Choix(String id,String sn,String ln,int nbChoix){
	super(id,sn,ln);
	this.nbChoix=nbChoix;
}

/**
 * 
 * @param element le choix à ajouter
 * @param importance un nombre indiquant l'importance relative du choix en terme d'effectif étudiant concerné
 */
public void ajouterChoix(E element,float importance){
	elements.add(element);
	importances.put(element,new Float(importance));
}

private float getImportanceRelative(E element){
	return importances.get(element);
}

public float getSommeImportances(){
	float res=0;
	for (E element: elements) 
		res+=getImportanceRelative(element);
	return res;
}


/**
 * rend le nombre d'heures que les étudiants doivent suivre pour cette formation 
 */
public float getNbHeuresEtudiants() {
	float res=0;
	for (E element: elements)  res+=element.getNbHeuresEtudiants()*getImportanceRelative(element);
	return res*nbChoix/getSommeImportances();	
}

@Override
public float getCoeff() {
    throw new RuntimeException("Non impl\u00E9ment\u00E9");

}

@Override
public float getEcts() {
    throw new RuntimeException("Non impl\u00E9ment\u00E9");
}


}
