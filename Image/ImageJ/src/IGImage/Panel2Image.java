package IGImage;

import ij.ImagePlus;
import ij.gui.ImageCanvas;

import javax.swing.*;
import java.awt.*;

/**
 * Created by melkir on 03/04/14.
 */
class Panel2Image extends JPanel {

    public Panel2Image(ImagePlus imp, ImagePlus imp2) {
        BorderLayout mainLayout = new BorderLayout();
        mainLayout.setHgap(2);
        setLayout(mainLayout);
        setBorder(BorderFactory.createEmptyBorder(0, 4, 4, 4));
        String title = imp.getTitle();
        add(new JLabel("Nom de l'image : " + title), BorderLayout.NORTH);
        add(new ImageCanvas(imp), BorderLayout.WEST);
        add(new ImageCanvas(imp2), BorderLayout.EAST);
    }
}
