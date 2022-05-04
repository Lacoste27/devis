/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Robsona
 */
public class Client implements Serializable {
   private int id ;
   private String ref;
   private String nom ;
   private String prenom ;
   private String adresse ;
   private LocalDate datedemande;

    public Client(int id, String ref, String nom, String prenom, String adresse, LocalDate datedemande) {
        this.id = id;
        this.ref = ref;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.datedemande = datedemande;
    }

    public Client() {
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
     * @return the ref
     */
    public String getRef() {
        return ref;
    }

    /**
     * @param ref the ref to set
     */
    public void setRef(String ref) {
        this.ref = ref;
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
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @return the adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * @param adresse the adresse to set
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * @return the datedemande
     */
    public LocalDate getDatedemande() {
        return datedemande;
    }

    /**
     * @param datedemande the datedemande to set
     */
    public void setDatedemande(LocalDate datedemande) {
        this.datedemande = datedemande;
    }
}
