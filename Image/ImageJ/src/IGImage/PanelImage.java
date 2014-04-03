package IGImage;

import ij.ImagePlus;
import ij.gui.ImageCanvas;
import tp4.Image;

import javax.swing.*;
import java.awt.*;

class PanelImage extends JPanel {

    public PanelImage(ImagePlus imp, ImagePlus imp2) {
        BorderLayout mainLayout = new BorderLayout();
        mainLayout.setHgap(2);
        setLayout(mainLayout);
        setBorder(BorderFactory.createEmptyBorder(0, 4, 4, 4));
        String title = imp.getTitle();

        ImagePlus histo = Image.getHistogramWindow(imp);
        ImagePlus histo2 = Image.getHistogramWindow(imp2);

        add(new JLabel("Nom de l'image : " + title), BorderLayout.NORTH);
        add(new ImageCanvas(imp), BorderLayout.WEST);
        add(new ImageCanvas(histo), BorderLayout.EAST);

        JPanel pane2 = new JPanel(new BorderLayout());
        pane2.add(new JLabel(title + " après la transformation"), BorderLayout.NORTH);
        pane2.add(new ImageCanvas(imp2), BorderLayout.WEST);
        pane2.add(new ImageCanvas(histo2), BorderLayout.EAST);

        add(pane2, BorderLayout.SOUTH);
    }

}
