package up5.mli630.tutore1314.maquette;

import java.util.ArrayList;
import java.util.List;

import up5.mli630.tutore1314.Element;

public class Bloc<E extends Element> extends Element  {

    
	private Oblig<E> obligs;
    private List<Choix <E>> choix=new ArrayList<Choix <E>>();;
    private List <Choix <Bloc<E>>> blocs=new ArrayList<Choix<Bloc<E>>>();

    public Bloc(String id, String shortName, String longName) {
		super(id, shortName, longName);
		obligs=new Oblig<E>(null,shortName,longName);
	}
    
    public void addObligatoire(E e){
    	obligs.addChild(e);
    }
    public void addChoix(Choix <E> e){
    	choix.add(e);
    }
    public void addChoixBloc(Choix <Bloc<E>> e){
    	blocs.add(e);
    }
    
    @Override
	public float getCoeff( ) {
        throw new RuntimeException("Non impl\u00E9ment\u00E9");
	}
    
    @Override
	public float getEcts( ) {
        throw new RuntimeException("Non impl\u00E9ment\u00E9");
	}

     
	@Override
	public float getNbHeuresEtudiants() {
        throw new RuntimeException("Non impl\u00E9ment\u00E9");
	};
    
    

}
