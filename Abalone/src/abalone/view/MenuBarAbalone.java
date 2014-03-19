package abalone.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class MenuBarAbalone extends JMenuBar {

	private JMenu mFile = new JMenu("Fichier");
	private JMenu mGame = new JMenu("Partie");
	private JMenu mEdit = new JMenu("Modifier");
	private JMenu mDifficulty = new JMenu("Difficulté");
	private JMenu mHelp = new JMenu("Aide");
	
	private JMenuItem open = new JMenuItem("Ouvrir");
	private JMenuItem save = new JMenuItem("Enregistrer");
	private JMenuItem saveAs = new JMenuItem("Enregistrer sous");
	private JMenuItem exit = new JMenuItem("Quitter");

	private JMenuItem start = new JMenuItem("Lancer");
	private JMenuItem stop = new JMenuItem("Arrêter");
	
	private JMenuItem about = new JMenuItem("A propos");
	private JMenuItem rules = new JMenuItem("Regles du jeu");

	private JMenuItem preferences = new JMenuItem("Preferences");

	private JRadioButtonMenuItem easy = new JRadioButtonMenuItem("Facile");
	private JRadioButtonMenuItem medium = new JRadioButtonMenuItem("Moyen");
	private JRadioButtonMenuItem hard = new JRadioButtonMenuItem("Difficile");
	private JRadioButtonMenuItem expert = new JRadioButtonMenuItem("Expert");

	public MenuBarAbalone() {

		mFile.add(open);
		mFile.add(save);
		mFile.add(saveAs);
		mFile.add(exit);

		mGame.add(start);
		mGame.add(stop);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(easy);
		bg.add(medium);
		bg.add(hard);
		bg.add(expert);
		medium.setSelected(true);
		mDifficulty.add(easy);
		mDifficulty.add(medium);
		mDifficulty.add(hard);
		mDifficulty.add(expert);
		
		mEdit.add(mDifficulty);
		mEdit.add(preferences);
		
		mHelp.add(rules);
		mHelp.add(about);

		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		this.add(mFile);
		this.add(mGame);
		this.add(mEdit);
		this.add(mHelp);
	}

}
