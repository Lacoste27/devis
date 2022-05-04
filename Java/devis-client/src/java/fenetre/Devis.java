/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenetre;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.swing.JFrame;
import javax.swing.JPanel;
import listener.DevisListener;
import machine.Appareil;
import maison.Maison;
import produit.Batterie;
import produit.Panneau;

/**
 *
 * @author Robsona
 */
public class Devis extends JFrame {
    
    private final Maison maison = new Maison();
    private final Configuration configuration = new Configuration();
    private final ProduitList produitList = new ProduitList();
    private final JPanel principale = new JPanel();
    private final JPanel maisonPanel = new JPanel();
    private final JPanel configurationPanel = new JPanel();
    private final GridLayout grid = new GridLayout(1, 2);
    private List<Batterie> batterieList;
    private List<Panneau> panneauList;
    
    public Devis() throws IOException {
        this.setFenetre();
    }

    private void setFenetre() {
        this.setResizable(false);
        this.setTitle("Devis");
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(this.principale);
        this.setResizable(true);
        this.maisonPanel.setBackground(Color.red);
        this.configurationPanel.setBackground(Color.GREEN);
        this.maison.addMouseListener(new DevisListener(this.maison));
        principale.setLayout(grid);
        principale.add(maison);
        principale.add(configurationPanel);
        this.setConfigurationPanel();
    }

    private void setConfigurationPanel() {
        GridLayout layout = new GridLayout(2, 0, 20, 20);
        this.configurationPanel.setLayout(layout);
        this.configurationPanel.add(produitList);
        this.configurationPanel.add(configuration);
    }

    public void addAppareil(Appareil appareil) {
        this.maison.addAppareil(appareil);
    }

    public Maison getMaison() {
        return this.maison;
    }
}
