package model;

import java.time.LocalTime;

public class ConsommationHeure {

    private String debut;
    private String fin;
    private Integer consommation;

    public ConsommationHeure(String debut, String fin, Integer consommation) {
        this.debut = debut;
        this.fin = fin;
        this.consommation = consommation;
    }

    /**
     * @return the debut
     */
    public String getDebut() {
        return debut;
    }

    /**
     * @param debut the debut to set
     */
    public void setDebut(String debut) {
        this.debut = debut;
    }

    /**
     * @return the fin
     */
    public String getFin() {
        return fin;
    }

    /**
     * @param fin the fin to set
     */
    public void setFin(String fin) {
        this.fin = fin;
    }

    /**
     * @return the consommation
     */
    public Integer getConsommation() {
        return consommation;
    }

    /**
     * @param consommation the consommation to set
     */
    public void setConsommation(Integer consommation) {
        this.consommation = consommation;
    }

}
