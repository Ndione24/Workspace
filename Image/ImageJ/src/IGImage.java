import javax.swing.JFrame;
import javax.swing.JPanel;

public class IGImage extends JFrame {
	
	public IGImage(String title, JPanel panel) {
		super(title);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		add(panel);
		pack();
		setVisible(true);
	}

}