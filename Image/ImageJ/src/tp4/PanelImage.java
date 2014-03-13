package tp4;

import ij.ImagePlus;
import ij.gui.ImageCanvas;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelImage extends JPanel {
	String title;
	ImageCanvas image1, image2, histogramme1, histogramme2;
	
	public PanelImage(ImagePlus imp, ImagePlus imp2) {
		BorderLayout mainLayout = new BorderLayout();
		mainLayout.setHgap(2);
		setLayout(mainLayout);
		setBorder(BorderFactory.createEmptyBorder(0,4,4,4));
		
		ImagePlus histo = Image.getHistogramWindow(imp);
		ImagePlus histo2 = Image.getHistogramWindow(imp2);
		
		title = imp.getTitle();
		image1 = new ImageCanvas(imp);
		image2 = new ImageCanvas(imp2);
		histogramme1 = new ImageCanvas(histo);
		histogramme2 = new ImageCanvas(histo2);
		
		add(new JLabel("Nom de l'image : " + title), BorderLayout.NORTH);
		add(new ImageCanvas(imp), BorderLayout.WEST);
		add(new ImageCanvas(histo), BorderLayout.EAST);

		JPanel pane2 = new JPanel(new BorderLayout());
		pane2.add(new JLabel(title + " après la transformation"), BorderLayout.NORTH);
		pane2.add(new ImageCanvas(imp2), BorderLayout.WEST);
		pane2.add(new ImageCanvas(histo2), BorderLayout.EAST);

		add(pane2, BorderLayout.SOUTH);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
	}
	
	@Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		super.update(g);
	}
	
}
