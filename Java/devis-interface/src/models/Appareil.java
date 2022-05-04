/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        int heuredebut = this.getHeuredebut().getHour();
        int heurefin = this.getHeurefin().getHour();
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
        int somme = 0;
        for (Appareil _apparei1 : appareil) {
            int heureNuit = _apparei1.getPuissanceTotal()[1];
            somme += heureNuit;
        }
        return somme;
    }

    public int getPuissancePanneau(List<Appareil> appareil) {
        int somme = 0;
        for (Appareil _apparei1 : appareil) {
            int heureMatin = _apparei1.getPuissanceTotal()[0];
            somme += heureMatin;
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

   /* public static double sommePuissance(Vector<Materiel> liste) {
        double retour = 0.0;
        for (int i = 0; i < liste.size(); i++) {
            Materiel m = (Materiel) liste.get(i);
            retour += m.getPuissance();
        }
        return retour;
    }

    public static double sommePuissance(List<Materiel> liste) {
        double retour = 0.0;
        for (int i = 0; i < liste.size(); i++) {
            Materiel m = (Materiel) liste.get(i);
            retour += m.getPuissance();
        }
        return retour;
    }

    public static double sommePrix(Vector<Materiel> liste) {
        double retour = 0.0;
        for (int i = 0; i < liste.size(); i++) {
            Materiel m = (Materiel) liste.get(i);
            retour += m.getPrix();
        }
        return retour;
    }

    public static void trouverCombinaison(int n, int left, double k, List<Materiel> all, Vector<Vector<Materiel>> retour, Vector<Materiel> temp) {
        System.out.println(sommePuissance(temp));
        if (sommePuissance(temp) >= k) {
            Vector<Materiel> nouv = new Vector();

            for (int i = 0; i < temp.size(); i++) {
                Materiel m = (Materiel) temp.get(i);
                nouv.add(new Materiel(m.getId(), m.getNom(), m.getIdTypeMateriel(), m.getIntensite(), m.getTension(), m.getPuissance(), m.getPrix()));
                System.out.print(((Materiel) temp.get(i)).getNom() + " ");
            }
            retour.add(nouv);
            System.out.println();
            return;
        }
        for (int i = left; i < n; ++i) {
            temp.add(all.get(i));
            trouverCombinaison(n, i + 1, k, all, retour, temp);
            temp.remove(temp.size() - 1);
        }
    }

    public static Vector<Vector<Materiel>> getListeCombinaisonPossible(int n, double puissance, int temps) {
        List<Materiel> listeMateriels = Materiel.getAll();
        Vector<Vector<Materiel>> retour = new Vector<Vector<Materiel>>();
        Vector<Materiel> temp = new Vector<Materiel>();
        trouverCombinaison(n, 0, puissance, listeMateriels, retour, temp);
        return retour;
    }

    public static Vector<Vector<Materiel>> getListeCombinaisonPossible(int n, double puissance, List<Materiel> listeMateriels) {
        Vector<Vector<Materiel>> retour = new Vector<Vector<Materiel>>();
        Vector<Materiel> temp = new Vector<Materiel>();
        trouverCombinaison(n, 0, puissance, listeMateriels, retour, temp);
        return retour;
    }

    public static Vector<Materiel> getMeilleureCombinaison(int n, double puissance, int temps) {
        Vector<Vector<Materiel>> liste = getListeCombinaisonPossible(n, puissance, temps);
        double min = sommePrix(((Vector<Materiel>) liste.get(0)));
        int index = 0;
        for (int i = 1; i < liste.size(); i++) {
            Vector<Materiel> l = (Vector<Materiel>) liste.get(i);
            double prix = sommePrix(l);
            if (prix < min) {
                min = prix;
                index = i;
            }
        }
        return (Vector<Materiel>) liste.get(index);
    }

    public static Vector<Materiel> getMeilleureCombinaison(int n, double puissance, List<Materiel> listeMateriels) {
        Vector<Vector<Materiel>> liste = getListeCombinaisonPossible(n, puissance, listeMateriels);
        double min = sommePrix(((Vector<Materiel>) liste.get(0)));
        System.out.println("min=" + min);
        int index = 0;
        for (int i = 1; i < liste.size(); i++) {
            Vector<Materiel> l = (Vector<Materiel>) liste.get(i);
            double prix = sommePrix(l);
            if (prix < min) {
                System.out.println("min=" + prix);
                min = prix;
                index = i;
            }
        }
        return (Vector<Materiel>) liste.get(index);
    }*/
}
