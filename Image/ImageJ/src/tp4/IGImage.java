package tp4;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class IGImage extends JFrame {

	public IGImage(String title, JPanel panel) {
		super(title);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setResizable(false);
		add(panel);

		KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0, false);
		Action escapeAction = new AbstractAction() {
			// close the frame when the user presses escape
			public void actionPerformed(ActionEvent e) { dispose(); }
		};
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
		getRootPane().getActionMap().put("ESCAPE", escapeAction);

		pack();
//		setLocationRelativeTo(null);
		setVisible(true);
	}

}
