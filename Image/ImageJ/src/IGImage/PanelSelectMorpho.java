package IGImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by melkir on 03/04/14.
 */
class PanelSelectMorpho extends JPanel {

    public PanelSelectMorpho() {
        setLayout(new FlowLayout());
        JButton bDilation, bErosion, bOuverture, bFermeture;
        add(new JLabel("Morpho"));
        bDilation = new JButton("Dilatation");
        bErosion = new JButton("Erosion");
        bOuverture = new JButton("Ouverture");
        bFermeture = new JButton("Fermeture");

        add(bDilation);
        add(bErosion);
        add(bOuverture);
        add(bFermeture);

        ActionListener listener = new AuditeurMorpho();
        bDilation.addActionListener(listener);
        bErosion.addActionListener(listener);
        bOuverture.addActionListener(listener);
        bFermeture.addActionListener(listener);
    }

}
