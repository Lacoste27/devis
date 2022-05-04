/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machine;

import java.awt.geom.Ellipse2D;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import models.Batterie;
import models.Panneau;

/**
 *
 * @author Robsona
 */
public class Appareil {

    private String id;
    private String nom;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private Integer quantite;
    private Integer puissance;
    private Ellipse2D shape;
    private Integer x;
    private Integer y;

    public Appareil() {

    }

    public Appareil(String id, String nom, LocalTime heureDebut, LocalTime heureFin, Integer quantite, Integer puissance) {
        this.id = id;
        this.nom = nom;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.quantite = quantite;
        this.puissance = puissance;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public LocalTime getHeureDebut() {
        return heureDebut;
    }

    public LocalTime getHeureFin() {
        return heureFin;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public Integer getPuissance() {
        return puissance;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setHeureDebut(LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }

    public void setHeureFin(LocalTime heureFin) {
        this.heureFin = heureFin;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public void setPuissance(Integer puissance) {
        this.puissance = puissance;
    }

    public Ellipse2D getShape() {
        return shape;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public void setShape(Ellipse2D shape) {
        this.shape = shape;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public int[] getHeureUtilisation() {
        int heuredebut = this.getHeureDebut().getHour();
        int heurefin = this.getHeureFin().getHour();
        int[] result = new int[2];
        if (heurefin >= 18 || heurefin <= 06) {
            result[0] = 18 - heuredebut;
            result[1] = ((heurefin - 18) < 0) ? (heurefin - 18) * -1 : (heurefin - 18);
        } else {
            result[0] = heurefin - heuredebut;
            result[1] = 0;
        }
        return result;
    }

    public int[] getPuissanceTotal() {
        int[] result = new int[2];
        int[] heureUtilisation = this.getHeureUtilisation();
        for (int i = 0; i < heureUtilisation.length; i++) {
            result[0] = heureUtilisation[0] * this.getPuissance();
            result[1] = heureUtilisation[1] * this.getPuissance();
        }
        //result[0] = result[0] + result[1];
        return result;
    }
    
    public int getPuissanceBatterie(List<Appareil> appareil) {
        int somme = 0 ;
        for (Appareil _apparei1 : appareil) {
            int heureNuit = _apparei1.getPuissanceTotal()[1];
            somme += heureNuit ;
        }
        return somme ;
    }
    
    public int getPuissancePanneau(List<Appareil> appareil) {
        int somme = 0 ;
        for (Appareil _apparei1 : appareil) {
            int heureMatin = _apparei1.getPuissanceTotal()[0];
            somme += heureMatin ;
        }
        return somme ;
    }
    
    public HashMap<Integer, Panneau> panneauProposition(List<Panneau> list, int puissance) {
        HashMap<Integer, Panneau> hash = new HashMap<>();
        List<Panneau> _list = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Panneau panneau = (Panneau) list.get(i);
            if (panneau.getPuissance() < puissance) {
                int somme = 0;
                int prix = 0;
                int counter = 0;
                while (somme <= puissance) {
                    somme += panneau.getPuissance();
                    prix += panneau.getPrix();
                    counter += 1;
                }
                if (prix < list.get(i + 1).getPrix()) {
                    hash.put(counter, panneau);
                    return hash;
                } else if (prix > list.get(i + 1).getPrix()) {
                    hash.put(1, list.get(i + 1));
                    return hash;
                }
            }
        }
        return null;
    }
    
    public HashMap<Integer, Batterie> batterieProposition(List<Batterie> list, int puissance) {
        HashMap<Integer, Batterie> hash = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            Batterie batterie = (Batterie) list.get(i);
            int watt = batterie.getAmperage() * batterie.getVoltage();
            if (watt < puissance) {
                int somme = 0;
                int prix = 0;
                int counter = 0;
                while (somme <= puissance) {
                    somme += watt;
                    prix += batterie.getPrix();
                    counter += 1;
                }
                if (prix < list.get(i + 1).getPrix()) {
                    hash.put(counter, batterie);
                    return hash;
                } else if (prix > list.get(i + 1).getPrix()) {
                    hash.put(1, list.get(i + 1));
                    return hash;
                }
            }
        }
        return null;
    }
}
