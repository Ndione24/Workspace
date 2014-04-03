package IGImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

class IGSelect extends JFrame {
    public IGSelect() {
        super("Projet Image");
        setLayout(new GridLayout(4, 1));
        add(new PanelSelectTrans());
        add(new PanelSelectFiltre());
        add(new PanelSelectEval());
        add(new PanelSelectMorpho());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
        Action escapeAction = new AbstractAction() {
            // close the frame when the user presses escape
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
        getRootPane().getActionMap().put("ESCAPE", escapeAction);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
