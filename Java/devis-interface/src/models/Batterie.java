/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;

/**
 *
 * @author Robsona
 */
public class Batterie implements Serializable {
    private int id ;
    private String nom ;
    private Integer voltage ;
    private Integer amperage ;
    private Integer prix ;
    private Integer marge;
    
    public Batterie(){
        
    }

    public Batterie(int id, String nom, Integer voltage, Integer amperage, Integer prix, Integer marge) {
        this.id = id;
        this.nom = nom;
        this.voltage = voltage;
        this.amperage = amperage;
        this.prix = prix ;
        this.marge = marge;
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
        int puissance = (this.getAmperage() * this.getVoltage() * this.getMarge() ) / 100 ;
        return puissance;
    }

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }
    
    public void setMarge(Integer marge) {
        this.marge = marge ;
    }
    
    public Integer getMarge() {
        return this.marge;
    }
    
    
}
