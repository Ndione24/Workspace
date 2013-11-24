package up5.mi.viethi.tp6.IGSwingEditeur;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class IGSwingEditeur extends JFrame {

	public IGSwingEditeur() {
		super("SwingEditeur");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(new PanelSwingEditeur());
		pack();
		setVisible(true);
	}

	public static void main(String[] args) throws IOException {
		new IGSwingEditeur();
	}
}
