/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devisclient;

import javax.ejb.EJB;
import remote.BatterieRemote;
import remote.PanneauRemote;

/**
 *
 * @author Tsiory
 */
public class Main {

    @EJB
    private static PanneauRemote panneauImpl;

    @EJB
    private static BatterieRemote batterieImpl;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            System.out.println(panneauImpl.findAll());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
