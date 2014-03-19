package abalone.view;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import abalone.tools.FrameUtil;
import chat.model.Chat;
import chat.view.PanelChat;

public class PanelAbalone extends JPanel {
	
	public PanelAbalone() throws IOException {
		setLayout(new BorderLayout());
		PanelGameboard pGameboard = new PanelGameboard();
		PanelChat pChat = new PanelChat(new Chat());
		add(pGameboard, BorderLayout.CENTER);
		add(pChat, BorderLayout.SOUTH);
	}
	
	public static void main(String[] args) throws IOException {
		PanelAbalone pAbalone =  new PanelAbalone();
		JFrame frame = FrameUtil.createFrame("Abalone", pAbalone);
		frame.setJMenuBar(new MenuBarAbalone());
		frame.pack();
		frame.setVisible(true);
	}
}
