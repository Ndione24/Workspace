package chat.tools;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrameUtil {

	public static JFrame createFrame(String title, JPanel panel) {
		JFrame frame = new JFrame();
		frame.setTitle(title);
		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		return frame;
	}

}
