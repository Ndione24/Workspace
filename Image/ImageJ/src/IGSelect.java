import javax.swing.JFrame;


public class IGSelect extends JFrame {
	public IGSelect() {
		super("Projet Image");
		add(new PanelSelect());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
}
