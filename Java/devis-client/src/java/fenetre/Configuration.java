/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenetre;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.dnd.DropTarget;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;
import listener.DragMouse;

/**
 *
 * @author Robsona
 */
public class Configuration extends JPanel {

    private final GridLayout layout = new GridLayout(6, 2);

    public Configuration() {
        this.setConfiguration();
    }

    public void setConfiguration() {
        DragMouse draglistener = new DragMouse();
        this.setLayout(layout);
        this.setSize(200, 600);
        this.setBackground(Color.GREEN);
        JButton boutton = new JButton("Boutton");
        boutton.setFocusable(false);
        boutton.addMouseListener(draglistener);
        boutton.setTransferHandler(new TransferHandler("Boutton"));
        this.add(boutton);

    }

    private void setLabel() {
        JLabel nom = new JLabel("Nom :");
        JLabel heuredebut = new JLabel("Heure debut :");
        JLabel heureFin = new JLabel("Heure fin :");
        JLabel puissance = new JLabel("Puissance :");
        JLabel quantite = new JLabel("Quantite :");
    }
}
