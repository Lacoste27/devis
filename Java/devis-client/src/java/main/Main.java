/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import fenetre.Menu;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import machine.Appareil;
import models.Panneau;
import remote.BatterieRemote;

/**
 *
 * @author Robsona
 */
public class Main {

    @EJB
    private static BatterieRemote batterieImpl;

    public static void main(String[] args) throws MalformedURLException, IOException {
        //Menu frame = new Menu();
        
        batterieImpl.findAll();

        List<Panneau> list = new ArrayList<>();
        Appareil appareil = new Appareil("1", "Nom", LocalTime.of(15, 00), LocalTime.of(05, 00), 2, 20);
        list.add(new Panneau(1, "nom", 200, 750000));
        list.add(new Panneau(1, "nom", 700, 2450000));
        list.add(new Panneau(1, "nom", 800, 3400000));
        list.add(new Panneau(2, "nom", 1000, 4000000));

        System.out.println(Arrays.toString(appareil.getHeureUtilisation()));
        System.out.println(Arrays.toString(appareil.getPuissanceTotal()));

        System.out.println(new Appareil().panneauProposition(list, 600));
    }
}
