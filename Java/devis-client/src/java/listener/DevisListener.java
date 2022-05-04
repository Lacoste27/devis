/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import maison.Maison;

/**
 *
 * @author Robsona
 */
public class DevisListener implements MouseListener {
    private final Maison maison;
    
    public DevisListener(Maison maison) {
        this.maison = maison ;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
