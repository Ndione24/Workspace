package abalone.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelGameboardListener extends MouseAdapter {

	PanelGameboard pGameboard;
	
	public PanelGameboardListener(PanelGameboard pGameboard) {
		this.pGameboard = pGameboard;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		super.mousePressed(e);
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		super.mouseMoved(e);
	}
	
}
