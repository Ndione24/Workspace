package IGImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class PanelSelectEval extends JPanel {
    public PanelSelectEval() {
        JPanel gridPanel = new JPanel(new GridLayout(4,2));
        add(new JLabel("Pré-traitement"));
        ArrayList<JButton> listButton = new ArrayList<JButton>();
        for (int i = 1; i < 26; i++) {
            listButton.add(new JButton("" + i));
        }
        ActionListener listeneur = new AuditeurEval();
        for (JButton bCur : listButton) {
            gridPanel.add(bCur);
            bCur.addActionListener(listeneur);
        }
        this.add(gridPanel);
    }
}
