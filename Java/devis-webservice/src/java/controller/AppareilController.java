/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import model.Appareil;
import model.Batterie;
import model.Consommation;
import model.ConsommationHeure;
import model.Etat;
import model.HourConsommation;
import model.Panneau;
import services.AppareilService;
import services.BatterieService;
import services.PanneauService;

/**
 *
 * @author Tsiory
 */
@Stateless
@Path("appareil")
public class AppareilController {

    private final AppareilService service = new AppareilService();
    private final PanneauService servicepanneau = new PanneauService();
    private final Etat etat = new Etat();

    @GET
    @Path("all")
    @Produces({"application/json"})
    public List<Appareil> findAll() throws SQLException {
        try {
            return this.service.findAll();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AppareilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @GET
    @Path("id/{client_id}")
    @Produces({"application/json"})
    public List<Appareil> findByClient_id(@PathParam(value = "client_id") int client_id) throws SQLException {
        try {
            return this.service.findByClient_id(client_id);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AppareilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @GET
    @Path("chart/consommationheure/{client_id}")
    @Produces({"application/json"})
    public String findChartClient(@PathParam(value = "client_id") int client_id) throws SQLException {
        try {
            List<Appareil> appareillist = AppareilService.findByClient_id(client_id);
            List<ConsommationHeure> consommation = Consommation.nightConsumptionPerHours(appareillist);
            String retour = new Gson().toJson(consommation);
            return retour;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AppareilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @GET
    @Path("chart/consommationbattery/{client_id}")
    @Produces({"application/json"})
    public String findChartBatterieClient(@PathParam(value = "client_id") int client_id) throws SQLException {
        try {
            List<Batterie> listbatterie = new AppareilService().getBatterieSuggestion(client_id);
            double sommepuissance = new Appareil().sommePuissanceBatterie(listbatterie);
            List<Appareil> appareillist = AppareilService.findByClient_id(client_id);
            List<ConsommationHeure> consommation = Consommation.nightBatteryConsumptionPerHours((int)sommepuissance,appareillist);
            String retour = new Gson().toJson(consommation);
            return retour;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AppareilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    @GET
    @Path("chart/batterie/{client_id}")
    @Produces({"application/json"})
    public String SuggestionBatterie(@PathParam(value = "client_id") int client_id) throws SQLException {
        try {
            List<Batterie> listbatterie = new AppareilService().getBatterieSuggestion(client_id);
            String retour = new Gson().toJson(listbatterie);
            return retour;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AppareilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @GET
    @Path("devis/panneau/{client_id}")
    @Produces({"application/json"})
    public String findClientPanneau(@PathParam(value = "client_id") int client_id) throws SQLException {
        try {
            List<List<Panneau>> retours = this.service.getListePanneauSuggestion(client_id);
            AppareilService.triePanneau(retours);
            String retour = new Gson().toJson(AppareilService.triePanneau(retours));
            return retour;
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage() + " à " + ex.getLocalizedMessage());
        }
        return null;
    }

    @GET
    @Path("devis/batterie/{client_id}")
    @Produces({"application/json"})
    public String findClientBatterie(@PathParam(value = "client_id") int client_id) throws SQLException {
        try {
            List<List<Batterie>> retours = this.service.getListeBatterieSuggestion(client_id);
            String retour = new Gson().toJson(AppareilService.trieBatterie(retours));
            return retour;
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage() + " à " + ex.getLocalizedMessage());
        }
        return null;
    }

    @GET
    @Path("traitement/base/{intervale}")
    @Produces({"application/json"})
    public String findByIntervaleBase(@PathParam(value = "intervale") int intervale) throws SQLException {
        List<Etat> retours = this.servicepanneau.getEtat(intervale);
        System.out.println("Etat" + retours);
        String retour = new Gson().toJson(retours);
        return retour;
    }

    @GET
    @Path("traitementsoustotal/base/{intervale}/{soustotal}")
    @Produces({"application/json"})
    public String findByIntervaleSousTotal(@PathParam(value = "intervale") int intervale, @PathParam(value = "soustotal") int soustotal) throws SQLException {
        List<Etat> retours = Etat.getEtatFinal(intervale, soustotal);
        String retour = new Gson().toJson(retours);
        return retour;
    }

    @GET
    @Path("traitement/java/{intervale}")
    @Produces({"application/json"})
    public String findByIntervaleJava(@PathParam(value = "intervale") int intervale) throws SQLException {
        List<Etat> retours = this.etat.getListEtatMetier(intervale);
        System.out.println("Etat" + retours + " Java traitement");
        String retour = new Gson().toJson(retours);
        return retour;
    }

    @GET
    @Path("prix/panneau/{client_id}")
    @Produces({"application/json"})
    public int getPrixPanneau(@PathParam(value = "client_id") int client_id) throws SQLException {
        return 1;
    }

    @GET
    @Path("client/consomation/{client_id}")
    @Produces({"application/json"})
    public String getConsommationClient(@PathParam(value = "client_id") int client_id) throws SQLException {
        try {
            return String.valueOf(this.service.getPuissanceTotal(client_id));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AppareilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

}
