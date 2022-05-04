/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import base.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import models.Appareil;

/**
 *
 * @author Tsiory
 */
public class AppareilService {

    public void save(Appareil appareil) throws SQLException, ClassNotFoundException {
        Connection connexion = Connect.getConnection("postgresql");
        String sql = "insert into appareil values(NEXTVAL('idAppareil'),?,'" + appareil.getHeuredebut().toString() + "','" + appareil.getHeurefin().toString() + "',?,?,?)";
        PreparedStatement statement = connexion.prepareStatement(sql);
        statement.setString(1, appareil.getNom());
        statement.setInt(2, appareil.getPuissance());
        statement.setInt(3, appareil.getQuantite());
        statement.setInt(4, appareil.getClient_id());
        statement.executeUpdate();
        connexion.setAutoCommit(false);
        connexion.commit();
        connexion.close();
    }

    public List<Appareil> findAll() throws SQLException, ClassNotFoundException {
        List<Appareil> retour = new ArrayList<>();
        Connection connexion = Connect.getConnection("postgresql");
        String sql = "select * from appareil";
        PreparedStatement statement = connexion.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        Appareil appareil = new Appareil();
        while (result.next()) {
            appareil.setId(result.getInt("id"));
            appareil.setNom(result.getString("nom"));
            appareil.setHeuredebut(LocalTime.of(Integer.parseInt(result.getString("heuredebut").split(":")[0]), Integer.parseInt(result.getString("heuredebut").split(":")[1])));
            appareil.setHeurefin(LocalTime.of(Integer.parseInt(result.getString("heurefin").split(":")[0]), Integer.parseInt(result.getString("heurefin").split(":")[1])));
            appareil.setPuissance(result.getInt("puissance"));
            appareil.setQuantite(result.getInt("quantite"));
            appareil.setClient_id(result.getInt("client_id"));
            retour.add(appareil);
        }
        return retour ;
    }
    
     public List<Appareil> findByClient_id(int id) throws SQLException, ClassNotFoundException {
        List<Appareil> retour = new ArrayList<>();
        Connection connexion = Connect.getConnection("postgresql");
        String sql = "select * from appareil where client_id = ?";
        PreparedStatement statement = connexion.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        Appareil appareil = new Appareil();
        while (result.next()) {
            appareil.setId(result.getInt("id"));
            appareil.setNom(result.getString("nom"));
            appareil.setHeuredebut(LocalTime.of(Integer.parseInt(result.getString("heuredebut").split(":")[0]), Integer.parseInt(result.getString("heuredebut").split(":")[1])));
            appareil.setHeurefin(LocalTime.of(Integer.parseInt(result.getString("heurefin").split(":")[0]), Integer.parseInt(result.getString("heurefin").split(":")[1])));
            appareil.setPuissance(result.getInt("puissance"));
            appareil.setQuantite(result.getInt("quantite"));
            appareil.setClient_id(result.getInt("client_id"));
            retour.add(appareil);
        }
        return retour ;
    }
}
