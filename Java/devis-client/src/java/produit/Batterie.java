/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produit;

/**
 *
 * @author Robsona
 */
public class Batterie {
    private String id ;
    private String nom ;
    private Integer voltage ;
    private Integer amperage ;
    private Integer prix ;
    
    public Batterie(){
        
    }

    public Batterie(String id, String nom, Integer voltage, Integer amperage, Integer prix) {
        this.id = id;
        this.nom = nom;
        this.voltage = voltage;
        this.amperage = amperage;
        this.prix = prix ;
    }
    
    public String getId() {
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

    public void setId(String id) {
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
        return this.getAmperage() * this.getVoltage();
    }

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }
    
    
}
