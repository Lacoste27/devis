package model;

import java.time.LocalTime;

    public class HourConsommation {

    private LocalTime debut;
    private LocalTime fin;
    private Integer consommation;

    public HourConsommation(LocalTime debut, LocalTime fin, Integer consommation) {
        this.debut = debut;
        this.fin = fin;
        this.consommation = consommation;
    }

    /**
     * @return the debut
     */
    public LocalTime getDebut() {
        return debut;
    }

    /**
     * @param debut the debut to set
     */
    public void setDebut(LocalTime debut) {
        this.debut = debut;
    }

    /**
     * @return the fin
     */
    public LocalTime getFin() {
        return fin;
    }

    /**
     * @param fin the fin to set
     */
    public void setFin(LocalTime fin) {
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
