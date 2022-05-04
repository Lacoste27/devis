/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenetre;

import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author Robsona
 */
public class ProduitList extends JPanel{
    public ProduitList() {
        this.setConfiguration();
    }
    
    public void setConfiguration() {
        this.setSize(200, 600);
        this.setBackground(Color.BLUE);
    }
}
