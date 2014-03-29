package IGImage;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelEval extends JPanel {
    public PanelEval() {
        add(new JLabel("Pré-traitement"));
        ArrayList<JButton> listButton = new ArrayList<JButton>();
        for (int i = 1; i < 18; i++) {
            listButton.add(new JButton("" + i));
        }
        ActionListener listeneur = new AuditeurEval();
        JButton bCur;
        for (JButton aListButton : listButton) {
            bCur = aListButton;
            add(bCur);
            bCur.addActionListener(listeneur);
        }

    }
}
