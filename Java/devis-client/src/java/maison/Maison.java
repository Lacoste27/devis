/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maison;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.TransferHandler;
import listener.DragMouse;
import machine.Appareil;

/**
 *
 * @author Robsona
 */
public class Maison extends JPanel {

    private List<Appareil> list;
    private final DragMouse draglistener = new DragMouse();

    public Maison() {
        this.list = new ArrayList<>();
        this.setBackground(Color.YELLOW);
        this.setTransferHandler(new TransferHandler("Boutton"));
    }

    public void addAppareil(Appareil appareil) {
        this.getList().add(appareil);
    }

    public List<Appareil> getList() {
        return list;
    }

    public void setList(List<Appareil> list) {
        this.list = list;
    }
    
    
}
