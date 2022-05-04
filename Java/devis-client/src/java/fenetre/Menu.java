/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenetre;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import listener.MenuListener;

/**
 *
 * @author Robsona
 */
public class Menu extends JFrame {

    private final JPanel panel = new JPanel();
    private final JButton boutton = new JButton("Faire un devis");

    public Menu() {
        this.setFenetre();
    }

    private void setFenetre() {
        this.panel.add(this.boutton);
        this.boutton.addMouseListener(new MenuListener());
        this.getContentPane().add(panel, "Center");
        this.setResizable(false);
        this.setTitle("Menu");
        this.setSize(100, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(500, 100);
        this.getContentPane().setLayout(new FlowLayout());
        this.getContentPane().add(panel, "Center");
        this.setVisible(true);
    }
}
