package up5.mli630.tutore1314.maquette;

import java.util.ArrayList;
import java.util.List;

import up5.mli630.tutore1314.Element;



public  class Oblig<E extends Element> extends Element   {
	
	protected List<E> list = new ArrayList<E>();



	public Oblig(String id,String sn,String ln){
		super(id,sn,ln);
	}



	/**
	 * rend le nombre d'heures que les Žtudiants doivent suivre pour cette formation 
	 */
	public float getNbHeuresEtudiants() {
		float res=0;
		for (E periode: list) res+=periode.getNbHeuresEtudiants();
		return res;	
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for (E element: list) sb.append(element.toString()+"\n");
		return sb.toString();
	}

	public void addChild(E element){
		list.add(element);
	}
	
	public E getChild(String text) {
		E res=null;
		for (int i=0;i<list.size()&&res==null;i++)
			if (list.get(i).getId().equals(text)) 
				res=list.get(i);
		return res;
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


