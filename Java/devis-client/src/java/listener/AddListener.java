/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import fenetre.Devis;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalTime;
import machine.Appareil;

/**
 *
 * @author Robsona
 */
public class AddListener implements MouseListener {

    private final Devis devis;

    public AddListener(Devis devis) {
        this.devis = devis;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Appareil appareil = new Appareil("1", "Frigo", LocalTime.NOON, LocalTime.now(), 2, 300);
        this.devis.addAppareil(appareil);
        System.out.println("Ajouter Appareil :");
        System.out.println(this.devis.getMaison().getList());
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
