package up5.mli630.tutore1314.maquette;


public class Periode extends Bloc<UE> {

	private int number;

	public Periode(String id,String sn,String ln,int number){
		super(id,sn,ln);
		this.number=number;
	}

	public int getNumber() {
		return number;
	}

	
	
}


