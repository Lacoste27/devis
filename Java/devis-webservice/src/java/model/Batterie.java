/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.time.Duration;
import java.util.List;
import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author Robsona
 */
public class Batterie implements Serializable {

    private int id;
    private String nom;
    private Integer voltage;
    private Integer amperage;
    private Integer prix;
    private Integer marge;
    private String datefabrication;

    public Batterie() {

    }

    public Batterie(int id, String nom, Integer voltage, Integer amperage, Integer prix, Integer marge, String datefabrication) {
        this.id = id;
        this.nom = nom;
        this.voltage = voltage;
        this.amperage = amperage;
        this.prix = prix;
        this.datefabrication = datefabrication;
        this.setMarge(marge);
    }

    public int getId() {
        return id;
    }   

    public String getNom() {
        return nom;
    }

    public Integer getVoltage() {
        return voltage;
    }

    public Integer getAmperage() {
        return amperage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setVoltage(Integer voltage) {
        this.voltage = voltage;
    }

    public void setAmperage(Integer amperage) {
        this.amperage = amperage;
    }

    public Integer getPuissance() {
        int _marge = this.getMargeBatterie();
        System.out.println("Marge Batterie : " + _marge);
        int puissance = (this.getAmperage() * this.getVoltage() * _marge) / 100;
        return puissance;
    }

    public Integer getMargeBatterie() {
        Integer retour = 100;
        return retour;
    }

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public void setMarge(Integer marge) {
        int _marge = this.getMargeBatterie();
        this.marge = _marge;
    }

    public Integer getMarge() {
        return this.marge;
    }

    public static void print(List<Batterie> list) {
        for (Batterie batterie : list) {
            System.out.println("Nom :" + batterie.getNom());
            System.out.println("Voltage :" + batterie.getVoltage());
            System.out.println("Amperage :" + batterie.getAmperage());
            System.out.println("Prix :" + batterie.getPrix());
            System.out.println("------------------------------------");
        }
    }

    public String getDatefabrication() {
        return datefabrication;
    }

    public void setDatefabrication(String date) {
        this.datefabrication = date;
    }

    public static int getNombreAnne(LocalDate date) {
        LocalDate now = LocalDate.now();
        Period difference = Period.between(date, now);
        int years = Math.abs(difference.getYears());
        return years;
    }

}
