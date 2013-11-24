package up5.mi.viethi.tp4.IGLien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JLabelLien extends JLabel {
	
	public JLabelLien() {
		super("Un Lien");
		final Color defaultColor = getForeground();
		
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setForeground(Color.BLUE);
            }
            public void mouseExited(MouseEvent e) {
            	setForeground(defaultColor);
            }
        });
		
	}
	
	public static void main(String[] args) {

	}

}
