package up5.mi.viethi.tp6.IGSwingEditeur;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PanelSwingEditeur extends JPanel implements ActionListener {
	
	JButton bOpen, bSave, bClear;
	JTextArea zoneTexte;
	JFileChooser fc;
	
	public PanelSwingEditeur() {
		super(new BorderLayout());
		
		zoneTexte = new JTextArea(20,5);
		add(zoneTexte, BorderLayout.NORTH);
		
		bClear = new JButton("Effacer");
		bSave = new JButton("Sauvegarder");
		bOpen = new JButton("Ouvrir");
		
		JPanel pButton = new JPanel();
		pButton.add(bClear);
		pButton.add(bSave);
		pButton.add(bOpen);
		add(pButton, BorderLayout.SOUTH);
		
		fc = new JFileChooser();
		
		bClear.addActionListener(this);
		bSave.addActionListener(this);
		bOpen.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bClear) { //Handle clear button action.
			zoneTexte.setText(null);
		} else if (e.getSource() == bOpen) { //Handle open button action.
	        int returnVal = fc.showOpenDialog(PanelSwingEditeur.this);
	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file = fc.getSelectedFile();
	            //This is where a real application would open the file.
	            System.out.println("Opening: " + file.getName());
				try {
					open(file);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        } else {
	            System.out.println("Open command cancelled by user.");
	        }
		} else if (e.getSource() == bSave ){ //Handle save button action.
	        int returnVal = fc.showSaveDialog(PanelSwingEditeur.this);
	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file = fc.getSelectedFile();
	            //This is where a real application would save the file.
	            System.out.println("Saving: " + file.getName() + ".");
	            try {
					save(file);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        } else {
	            System.out.println("Save command cancelled by user.");
	        }
		}
	}
	
	// fonction qui étant donné un fichier rend une String répresentant le
	// contenu du fichier
	public static String FileToString(File fic) throws IOException {
		String ligne;
		String res = "";
		BufferedReader br = new BufferedReader(new FileReader(fic));
		while ( (ligne = br.readLine() ) != null) {
			res += ligne + '\n';
		}
		br.close();
		return res;
	}
		
	public static void StringToFile(String str, File fic) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(fic));
		bw.write(str);
		bw.close();
	}
	
	private void save(File file) throws IOException {
		StringToFile(zoneTexte.getText(), file);
	}
	
	private void open(File file) throws IOException {
        String str = null;
		str = FileToString(file);
        zoneTexte.setText(str);
	}
}
