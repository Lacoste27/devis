/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import services.PanneauService;

/**
 *
 * @author Tsiory
 */
public class Etat {

    private String intervale;
    private int quantite;
    private double prixunitairemoyenne;
    private float prixwattmoyenne;

    public Etat(String intervale, int quantite, double prixunitairemoyenne, float prixwattmoyenne) {
        this.intervale = intervale;
        this.quantite = quantite;
        this.prixunitairemoyenne = prixunitairemoyenne;
        this.prixwattmoyenne = prixwattmoyenne;
    }

    public Etat() {

    }

    /**
     * @return the intervale
     */
    public String getIntervale() {
        return intervale;
    }

    /**
     * @param intervale the intervale to set
     */
    public void setIntervale(String intervale) {
        this.intervale = intervale;
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
     * @return the prixunitairemoyenne
     */
    public double getPrixunitairemoyenne() {
        return prixunitairemoyenne;
    }

    /**
     * @param prixunitairemoyenne the prixunitairemoyenne to set
     */
    public void setPrixunitairemoyenne(double prixunitairemoyenne) {
        this.prixunitairemoyenne = prixunitairemoyenne;
    }

    /**
     * @return the prixwattmoyenne
     */
    public float getPrixwattmoyenne() {
        return prixwattmoyenne;
    }

    /**
     * @param prixwattmoyenne the prixwattmoyenne to set
     */
    public void setPrixwattmoyenne(float prixwattmoyenne) {
        this.prixwattmoyenne = prixwattmoyenne;
    }

    public List<Etat> getListEtat(int intervale) {
        List<Etat> response = new ArrayList<>();
        double puissancemax = Double.parseDouble(PanneauService.getMax());
        int min = 0;
        int max = intervale;
        while (min < puissancemax) {
            List<Panneau> panneaulist = PanneauService.findByIntervalle(min, max);
            double _prixunitairemoyenne = this.getPrixUnitaireMoyenne(panneaulist);
            float _prixwattmoyenne = this.getPrixWattMoyenne(panneaulist);
            if (panneaulist.size() == 0) {
                response.add(new Etat((min + "-" + max), 0, 0, 0));
            } else {
                response.add(new Etat((min + 1) + "-" + max, panneaulist.size(), _prixunitairemoyenne, _prixwattmoyenne));
            }
            min += intervale;
            max += intervale;
        }
        return response;
    }

    public static List<Panneau> findPanneauByInterval(int min, int max) {
        List<Panneau> panneaulist = new PanneauService().findAll();
        List<Panneau> retour = new ArrayList<>();
        for (int i = 0; i < panneaulist.size(); i++) {
            if (panneaulist.get(i).getPuissance() > min && panneaulist.get(i).getPuissance() <= max) {
                retour.add(panneaulist.get(i));
            }
        }
        return retour;
    }

    public static List<Etat> getPanneauIntervale(int intervale, int start, int last) {
        List<Etat> panneaulist = new PanneauService().getEtat(intervale);
        List<Etat> retour = new ArrayList<>();
        for (int i = 0; i < panneaulist.size(); i++) {
            if (Integer.parseInt(panneaulist.get(i).getIntervale().split("-")[1]) > start && Integer.parseInt(panneaulist.get(i).getIntervale().split("-")[1]) <= last) {
                retour.add(panneaulist.get(i));
                Etat.printEtat(panneaulist.get(i));
            }
        }
        return retour;
    }

    public static double[] getMoyenne(int intervale, int start, int last) {
        List<Etat> list = Etat.getPanneauIntervale(intervale, start, last);
        double moyenneprixwatt = 0;
        double moyenneprixunitaire = 0;
        double sommequantite = 0;
        double sommemoyennewatt = 0;
        double sommemoyenneprixunitaire = 0;
        for (Etat etat : list) {
            sommemoyennewatt += etat.getPrixunitairemoyenne();
            sommemoyenneprixunitaire += etat.getPrixunitairemoyenne();
            sommequantite += etat.getQuantite();
        }
        moyenneprixwatt = (sommemoyennewatt / list.size());
        moyenneprixunitaire = (sommemoyenneprixunitaire / list.size());
        double[] retour = {sommequantite, moyenneprixunitaire, moyenneprixwatt};
        return retour;
    }

    public static List<Etat> getEtatFinal(int intervale, int soustotal) {
        List<Etat> retour = new ArrayList<>();
        int start = 0;
        int debutintervale = soustotal;
        int max = Integer.parseInt(PanneauService.getMax());
        int last = soustotal;
        while (start < max) {
            System.out.println(last + " last");
            List<Etat> panneausoustotal = Etat.getPanneauIntervale(intervale, start, last);
            double[] moyenne = Etat.getMoyenne(intervale, start, last);
            for (Etat etat : panneausoustotal) {
                retour.add(etat);
            }
            retour.add(new Etat("Total", (int) moyenne[0], moyenne[1], (float) moyenne[2]));
            System.out.println(retour);
            start += soustotal;
            last += soustotal;
        }
        return retour;
    }

    public double getPrixUnitaireMoyenne(List<Panneau> panneaulist) {
        int size = panneaulist.size();
        double prixmoyenne = 0.0;
        double prixtotal = 0.0;
        for (int i = 0; i < panneaulist.size(); i++) {
            prixtotal += panneaulist.get(i).getPrix();
        }
        prixmoyenne = prixtotal / size;
        return prixmoyenne;
    }

    public static void print(List<Etat> list) {
        for (Etat etat : list) {
            System.out.println("----------------------------------");
            System.out.println("Prix Moyenne :" + etat.getPrixunitairemoyenne());
            System.out.println("Quantite :" + etat.getQuantite());
            System.out.println("Intervalle :" + etat.getIntervale());
            System.out.println("----------------------------------");

        }
    }

    public static void printEtat(Etat etat) {
        System.out.println("----------------------------------");
        System.out.println("Prix Moyenne :" + etat.getPrixunitairemoyenne());
        System.out.println("Quantite :" + etat.getQuantite());
        System.out.println("Intervalle :" + etat.getIntervale());
        System.out.println("----------------------------------");

    }

    public List<Etat> getEtatSousTotal(int intervale, int soustotal) {
        List<Etat> list = new PanneauService().getEtat(intervale);
        int debutintervale = soustotal;
        int puissancemax = Integer.parseInt(PanneauService.getMax());
        while (debutintervale < puissancemax) {

            debutintervale += intervale;
        }
        return null;
    }

    public float getPrixWattMoyenne(List<Panneau> panneaulist) {
        int size = panneaulist.size();
        float _prixwattmoyenne = 0;
        float prixmoyenne = 0;
        for (int i = 0; i < panneaulist.size(); i++) {
            float wattmoyenne = this.getWattMoyenne(panneaulist.get(i));
            _prixwattmoyenne += wattmoyenne;
        }
        prixmoyenne = _prixwattmoyenne / size;
        return prixmoyenne;
    }

    public float getWattMoyenne(Panneau panneau) {
        float retour = (float) panneau.getPrix() / (float) panneau.getPuissance();
        return retour;
    }

    public List<Etat> getListEtatMetier(int interval) {
        List<Etat> response = new ArrayList<>();
        double puissancemax = Double.parseDouble(PanneauService.getMax());
        int min = 0;
        int max = interval;
        while (min < puissancemax) {
            List<Panneau> panneaulist = Etat.findPanneauByInterval(min, max);
            System.out.println(panneaulist);
            double _prixunitairemoyenne = this.getPrixUnitaireMoyenne(panneaulist);
            float _prixwattmoyenne = this.getPrixWattMoyenne(panneaulist);
            if (panneaulist.isEmpty()) {
                response.add(new Etat((min + "-" + max), 0, 0, 0));
            } else {
                response.add(new Etat((min + 1) + "-" + max, panneaulist.size(), _prixunitairemoyenne, _prixwattmoyenne));
            }
            min += interval;
            max += interval;
            System.out.println(min + "" + max);
        }
        return response;
    }
}
