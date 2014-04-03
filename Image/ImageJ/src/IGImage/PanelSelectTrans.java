package IGImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

class PanelSelectTrans extends JPanel {

    public PanelSelectTrans() {
        setLayout(new FlowLayout());
        JButton bGris, bEgalisation, bNormalisation, bBinarisation, bOTSU;
        bGris = new JButton("Gris");
        bNormalisation = new JButton("Normalisation");
        bEgalisation = new JButton("Egalisation");
        bBinarisation = new JButton("Binarisation");
        bOTSU = new JButton("Otsu");
        add(new JLabel("Transformation"));
        add(bGris);
        add(bNormalisation);
        add(bEgalisation);
        add(bBinarisation);
        add(bOTSU);

        ActionListener auditeur = new AuditeurTrans();
        bGris.addActionListener(auditeur);
        bNormalisation.addActionListener(auditeur);
        bEgalisation.addActionListener(auditeur);
        bBinarisation.addActionListener(auditeur);
        bOTSU.addActionListener(auditeur);
    }
}
