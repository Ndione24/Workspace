package tp4;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class IGImage extends JFrame {
	JButton bChange;
	
	public IGImage(String title, JPanel panel) {
		super(title);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setResizable(false);
		setLayout(new BorderLayout());
		
		JPanel pControl = new JPanel();
		bChange = new JButton("Changer d'image");
		pControl.add(bChange);
		
		bChange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		add(panel, BorderLayout.CENTER);
		add(pControl, BorderLayout.SOUTH);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
}
