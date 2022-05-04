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
public class Panneau implements Serializable {
    private int id ;
    private String nom ;
    private Integer puissance ;
    private Integer prix;
    
    public Panneau() {
        
    }

    public Panneau(int id, String nom, Integer puissance, Integer prix) {
        this.id = id;
        this.nom = nom;
        this.puissance = puissance;
        this.prix = prix;
    }
    
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Integer getPuissance() {
        return puissance;
    }

    public Integer getPrix() {
        return prix;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPuissance(Integer puissance) {
        this.puissance = puissance;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }
}
