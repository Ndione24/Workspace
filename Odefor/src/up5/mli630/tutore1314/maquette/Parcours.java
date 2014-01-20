package up5.mli630.tutore1314.maquette;

import java.util.ArrayList;
import java.util.List;

public class Parcours extends Oblig<Periode> {

	protected List<Periode> list = new ArrayList<Periode>();

	public Parcours(String id, String sn, String ln) {
		super(id, sn, ln);
	}

}
