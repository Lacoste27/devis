/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import services.BatterieService;
import services.PanneauService;

/**
 *
 * @author Tsiory
 */
public class Appareil implements Serializable {

    private int id;
    private String nom;
    private LocalTime heuredebut;
    private LocalTime heurefin;
    private int puissance;
    private int quantite;
    private int client_id;

    private final PanneauService servicePanneau = new PanneauService();
    private final BatterieService serviceBatterie = new BatterieService();

    public Appareil() {

    }

    public Appareil(int id, String nom, LocalTime heuredebut, LocalTime heurefin, int puissance, int quantite, int client_id) {
        this.id = id;
        this.nom = nom;
        this.heuredebut = heuredebut;
        this.heurefin = heurefin;
        this.puissance = puissance;
        this.quantite = quantite;
        this.client_id = client_id;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the heuredebut
     */
    public LocalTime getHeuredebut() {
        return heuredebut;
    }

    /**
     * @param heuredebut the heuredebut to set
     */
    public void setHeuredebut(LocalTime heuredebut) {
        this.heuredebut = heuredebut;
    }

    /**
     * @return the heurefin
     */
    public LocalTime getHeurefin() {
        return heurefin;
    }

    /**
     * @param heurefin the heurefin to set
     */
    public void setHeurefin(LocalTime heurefin) {
        this.heurefin = heurefin;
    }

    /**
     * @return the puissance
     */
    public int getPuissance() {
        return puissance;
    }

    /**
     * @param puissance the puissance to set
     */
    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }

    /**
     * @return the quantite
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     * @param quantite the quantite to set
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    /**
     * @return the client_id
     */
    public int getClient_id() {
        return client_id;
    }

    /**
     * @param client_id the client_id to set
     */
    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int[] getHeureUtilisation() {
        int _heuredebut = this.getHeuredebut().getHour();
        int _heurefin = this.getHeurefin().getHour();
        int[] result = new int[2];
        if (_heurefin >= 18 || _heuredebut <= 06) {
            result[0] = 18 - _heuredebut;
            result[1] = ((_heurefin - 18) < 0) ? (_heurefin - 18) * -1 : (_heurefin - 18);
        } else if (checkNuit(_heuredebut) && checkNuit(_heurefin)) {
            System.out.println("Ato ve");
            result[0] = 12;
            result[1] = (06 - _heuredebut) + (_heurefin - 18);
        } else if (_heurefin > 00 && _heurefin < 06) {
            result[0] = 18 - _heuredebut;
            result[1] = 6 + (6 - _heurefin);
        } else {
            result[0] = _heurefin - _heuredebut;
            result[1] = 0;
        }
        return result;
    }

    public int getPrixPanneau(List<Panneau> list) {
        int somme = 0;
        for (Panneau panneau : list) {
            somme += panneau.getPrix();
        }
        return 0;
    }

    private boolean checkNuit(int heure) {
        System.out.println(heure);
        return (heure > 18 || heure < 06);
    }

    public int[] getPuissanceTotal() {
        int[] result = new int[2];
        int[] heureUtilisation = this.getHeureUtilisation();
        for (int i = 0; i < heureUtilisation.length; i++) {
            result[0] = heureUtilisation[0] * this.getPuissance() * this.getQuantite();
            result[1] = heureUtilisation[1] * this.getPuissance() * this.getQuantite();
        }
        //result[0] = result[0] + result[1];
        return result;
    }

    public int getPuissanceSoir(List<Appareil> appareil) {
        int somme = 0;
        for (Appareil appareil1 : appareil) {
            int[] result = appareil1.getHeureJourNuit(appareil1.getHeuredebut(), appareil1.getHeurefin());
            somme += appareil1.getPuissance() * result[1];
        }
        return somme;
    }

    public int getPuissanceMatin(List<Appareil> appareil) {
        int somme = 0;
        for (int i = 0; i < appareil.size() - 1; i++) {
            int[] result = appareil.get(i).getHeureJourNuit(appareil.get(i).getHeuredebut(), appareil.get(i).getHeurefin());
            somme += appareil.get(i).getPuissance() * result[0];
        }
        return somme;
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

    public double sommePuissanceBatterie(List<Batterie> liste) {
        double retour = 0.0;
        for (int i = 0; i < liste.size(); i++) {
            Batterie m = (Batterie) liste.get(i);
            retour += m.getPuissance();
        }
        return retour;
    }

    public double sommePuissancePanneau(List<Panneau> liste) {
        double retour = 0.0;
        for (int i = 0; i < liste.size(); i++) {
            Panneau m = (Panneau) liste.get(i);
            retour += m.getPuissance();
        }
        return retour;
    }

    public static double sommePrixPanneau(List<Panneau> liste) {
        double retour = 0.0;
        for (int i = 0; i < liste.size(); i++) {
            Panneau m = (Panneau) liste.get(i);
            retour += m.getPrix();
        }
        return retour;
    }

    public static double sommePrixBatterie(List<Batterie> liste) {
        double retour = 0.0;
        for (int i = 0; i < liste.size(); i++) {
            Batterie m = (Batterie) liste.get(i);
            retour += m.getPrix();
        }
        return retour;
    }

    /*
    ** n=taille
        left=0
        k=puissance
    all =disponible
    retour = 
    temp = List<Panneau>
     */
    public void trouverCombinaisonPanneau(int n, int left, double k, List<Panneau> all, List<List<Panneau>> retour, List<Panneau> temp) {
        if (sommePuissancePanneau(temp) >= k) {
            List<Panneau> nouv = new ArrayList();
            for (int i = 0; i < temp.size(); i++) {
                Panneau m = (Panneau) temp.get(i);
                nouv.add(new Panneau(m.getId(), m.getNom(), m.getPuissance(), m.getPrix()));
                System.out.print(((Panneau) temp.get(i)).getNom() + " ");
            }
            retour.add(nouv);
            System.out.println();
            return;
        }
        for (int i = left; i < n; ++i) {
            temp.add(all.get(i));
            trouverCombinaisonPanneau(n, i + 1, k, all, retour, temp);
            temp.remove(temp.size() - 1);
        }
    }

    public List<List<Panneau>> getListeCombinaisonPossible(int n, double puissance, List<Panneau> listeMateriels) {
        List<List<Panneau>> retour = new ArrayList<>();
        List<Panneau> _listeMateriels = this.servicePanneau.findAll();
        List<Panneau> temp = new ArrayList<>();
        trouverCombinaisonPanneau(n, 0, puissance, _listeMateriels, retour, temp);
        return retour;
    }

    public List<Panneau> getMeilleureCombinaisonPanneau(int n, double puissance, List<Panneau> listeMateriels) {
        System.out.println("Puissance " + puissance);
        List<List<Panneau>> liste = getListeCombinaisonPossible(n, puissance, listeMateriels);
        double min = sommePrixPanneau(((List<Panneau>) liste.get(0)));
        int index = 0;
        for (int i = 1; i < liste.size(); i++) {
            List<Panneau> l = (List<Panneau>) liste.get(i);
            double prix = sommePrixPanneau(l);
            if (prix < min) {
                min = prix;
                index = i;
            }
        }
        System.out.println("Meilleur prix : " + liste.get(index).get(0).getPrix());
        return (List<Panneau>) liste.get(index);
    }

    public void trouverCombinaisonBatterie(int n, int left, double k, List<Batterie> all, List<List<Batterie>> retour, List<Batterie> temp) {
        if (sommePuissanceBatterie(temp) >= k) {
            List<Batterie> nouv = new ArrayList();
            for (int i = 0; i < temp.size(); i++) {
                Batterie m = (Batterie) temp.get(i);
                nouv.add(new Batterie(m.getId(), m.getNom(), m.getVoltage(), m.getAmperage(), m.getPrix(), m.getMarge(), m.getDatefabrication()));
            }
            retour.add(nouv);
            return;
        }
        for (int i = left; i < n; ++i) {
            temp.add(all.get(i));
            trouverCombinaisonBatterie(n, i + 1, k, all, retour, temp);
            temp.remove(temp.size() - 1);
        }
    }

    public List<List<Batterie>> getListeCombinaisonBatteriePossible(int n, double puissance, int temps) {
        List<Batterie> listeMateriels = this.serviceBatterie.findAll();
        System.out.println(listeMateriels);
        List<List<Batterie>> retour = new ArrayList<>();
        List<Batterie> temp = new ArrayList<>();
        this.trouverCombinaisonBatterie(listeMateriels.size(), 0, puissance, listeMateriels, retour, temp);
        return retour;
    }

    public List<List<Batterie>> getListeCombinaisonBatteriePossible(int n, double puissance, List<Batterie> listeMateriels) {
        List<List<Batterie>> retour = new ArrayList<>();
        List<Batterie> temp = new ArrayList<>();
        trouverCombinaisonBatterie(listeMateriels.size(), 0, puissance, listeMateriels, retour, temp);
        return retour;
    }

    public List<Batterie> getMeilleureCombinaisonBatterie(int n, double puissance, int temps) {
        List<List<Batterie>> liste = getListeCombinaisonBatteriePossible(n, puissance, temps);
        double min = sommePrixBatterie(((List<Batterie>) liste.get(0)));
        int index = 0;
        for (int i = 1; i < liste.size(); i++) {
            List<Batterie> l = (List<Batterie>) liste.get(i);
            double prix = sommePrixBatterie(l);
            if (prix < min) {
                min = prix;
                index = i;
            }
        }
        return (List<Batterie>) liste.get(index);
    }

    public List<Batterie> getMeilleureCombinaisonBatterie(int n, double puissance, List<Batterie> listeMateriels) {
        List<List<Batterie>> liste = getListeCombinaisonBatteriePossible(n, puissance, listeMateriels);
        double min = sommePrixBatterie(((List<Batterie>) liste.get(0)));
        int index = 0;
        for (int i = 1; i < liste.size(); i++) {
            List<Batterie> l = (List<Batterie>) liste.get(i);
            double prix = sommePrixBatterie(l);
            if (prix < min) {
                min = prix;
                index = i;
            }
        }
        return (List<Batterie>) liste.get(index);
    }

    public int[] getHeureJourNuit(LocalTime debutheure, LocalTime finheure) {
        int jour = (int) getHeureJour(debutheure, finheure);
        int nuit = (int) getHeureNuit(debutheure, finheure);
        int[] retour = {jour, nuit};
        return retour;
    }

    public double getHeureJour(LocalTime debutheure, LocalTime finheure) {
        int heureDebut = debutheure.getHour();
        int minuteDebut = debutheure.getMinute();
        int heureFin = finheure.getHour();
        int minuteFin = finheure.getMinute();
        int heureDebutJour = 6;
        int minuteDebutJour = 0;
        int heureFinJour = 18;
        int minuteFinJour = 0;

        if ((heureDebut >= 0 && heureDebut <= heureDebutJour) || (heureDebut >= heureFinJour && heureDebut <= 23)) {
            if ((heureFin >= 0 && heureFin <= heureDebutJour) || (heureFin >= heureFinJour && heureFin <= 23)) {
                return 0.0;
            }

        }
        // soirée miotra début
        if ((heureDebut == heureFinJour && minuteDebut > minuteFinJour) || heureDebut > heureFinJour) {
            heureDebut = heureDebutJour;
            minuteDebut = minuteDebutJour;
        }

        // soirée miotra fin
        if ((heureFin == heureFinJour && minuteFin > minuteFinJour) || heureFin > heureFinJour) {
            heureFin = heureFinJour;
            minuteFin = minuteFinJour;
        }

        // matinale aloha fin
        if ((heureFin == heureDebutJour && minuteFin < minuteDebutJour) || heureFin < heureDebutJour) {
            heureFin = heureDebutJour;
            minuteFin = minuteDebutJour;
        }

        // matinale aloha début
        if ((heureDebut == heureDebutJour && minuteDebut < minuteDebutJour) || heureDebut < heureDebutJour) {
            heureDebut = heureDebutJour;
            minuteDebut = minuteDebutJour;
        }

        Date heure1 = new GregorianCalendar(0, 1, 1, heureDebut, minuteDebut).getTime();
        Date heure2 = new GregorianCalendar(0, 1, 1, heureFin, minuteFin).getTime();
        long diff = heure2.getTime() - heure1.getTime();
        double nbHeureJour = diff / (1000.0 * 60.0 * 60.0);

        // alina | aloha début - miotra fin
        if ((heureDebut < heureFinJour || (heureDebut == heureFinJour && minuteDebut < minuteFinJour)) && heureDebut > heureDebutJour && (heureFin > heureDebutJour || (heureFin == heureDebutJour && minuteFin > minuteFinJour)) && heureFin < heureFinJour && heureDebut > heureFin) {
            // (heureDebut < heureFinJour || (heureDebut == heureFinJour && minuteDebut < minuteFinJour)) && heureDebut > heureDebutJour
            // &&
            // (heureFin > heureDebutJour || (heureFin == heureDebutJour && minuteFin > minuteFinJour)) && heureFin < heureFinJour
            // && heureDebut > heureFin
            heure1 = new GregorianCalendar(0, 1, 1, heureDebut, minuteDebut).getTime();
            heure2 = new GregorianCalendar(0, 1, 1, heureFinJour, minuteFinJour).getTime();
            diff = heure2.getTime() - heure1.getTime();
            nbHeureJour = diff / (1000.0 * 60.0 * 60.0);

            Date heure10 = new GregorianCalendar(0, 1, 1, heureDebutJour, minuteDebutJour).getTime();
            Date heure20 = new GregorianCalendar(0, 1, 1, heureFin, minuteFin).getTime();
            diff = heure20.getTime() - heure10.getTime();
            nbHeureJour += diff / (1000.0 * 60.0 * 60.0);
        }

        return nbHeureJour;
    }

    public double getHeureNuit(LocalTime debutheure, LocalTime finheure) {
        double nbHeureTotal = getNbHeure(debutheure, finheure);
        System.out.println("HEure total " + nbHeureTotal);

        double nbHeureJour = getHeureJour(debutheure, finheure);
        System.out.println("Heure Jour:" + nbHeureJour);
        return nbHeureTotal - nbHeureJour;
    }

    public double getNbHeure(LocalTime debutheure, LocalTime finheure) {
        int heureDebut = debutheure.getHour();
        int minuteDebut = debutheure.getMinute();
        int heureFin = finheure.getHour();
        int minuteFin = finheure.getMinute();
        Date heure1 = new GregorianCalendar(0, 1, 1, heureDebut, minuteDebut).getTime();
        Date heure2 = new GregorianCalendar(0, 1, 1, heureFin, minuteFin).getTime();
        long diff = heure2.getTime() - heure1.getTime();
        double nbHeureJour = diff / (1000.0 * 60.0 * 60.0);
        if (nbHeureJour < 0) {
            heure1 = new GregorianCalendar(0, 1, 1, heureDebut, minuteDebut).getTime();
            heure2 = new GregorianCalendar(0, 1, 2, heureFin, minuteFin).getTime();
            diff = heure2.getTime() - heure1.getTime();
            nbHeureJour = diff / (1000.0 * 60.0 * 60.0);
        }
        return nbHeureJour;
    }
    

}
