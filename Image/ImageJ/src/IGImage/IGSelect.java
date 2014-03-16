package IGImage;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

public class IGSelect extends JFrame {
	public IGSelect() {
		super("Projet Image");
		add(new PanelSelectTrans(), BorderLayout.NORTH);
		add(new PanelSelectFiltre(), BorderLayout.CENTER);
		add(new PanelEval(), BorderLayout.SOUTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0, false);
		Action escapeAction = new AbstractAction() {
			// close the frame when the user presses escape
			public void actionPerformed(ActionEvent e) { System.exit(0); }
		};
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
		getRootPane().getActionMap().put("ESCAPE", escapeAction);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
