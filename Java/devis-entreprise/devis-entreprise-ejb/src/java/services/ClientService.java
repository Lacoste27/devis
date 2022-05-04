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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import models.Client;

/**
 *
 * @author Tsiory
 */
public class ClientService {

    public void save(Client client) throws SQLException, ClassNotFoundException {
        System.out.println("save");
        Connection connexion = Connect.getConnection("mysql");
        String sql = "insert into client values(null,?,?,?,?,'" + client.getDatedemande().toString() + "')";
        PreparedStatement statement = connexion.prepareStatement(sql);
        statement.setString(1, client.getRef());
        statement.setString(2, client.getNom());
        statement.setString(3, client.getPrenom());
        statement.setString(4, client.getAdresse());
        System.out.println(sql);
        statement.executeUpdate();
        connexion.close();
    }
    
    public List<Client> findAll() throws SQLException, ClassNotFoundException {
        List<Client> retour = new ArrayList<>();
        Connection connexion = Connect.getConnection("mysql");
        String sql = "select * from client";
        PreparedStatement statement = connexion.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        Client client = new Client();
        while(result.next()) {
            client.setId(result.getInt("id"));
            client.setRef(result.getString("ref"));
            client.setNom(result.getString("nom"));
            client.setPrenom(result.getString("prenom"));
            client.setAdresse(result.getString("adresse"));
            client.setDatedemande(LocalDate.parse(result.getString("datedemande")));
            retour.add(client);
        }
        return retour ;
    }
    
     public int getLastId() throws SQLException, ClassNotFoundException {
        Connection connexion = Connect.getConnection("mysql");
        String sql = "select MAX(id) as maxid from client";
        PreparedStatement statement = connexion.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        result.next();
        return result.getInt("maxid") ;
    }
}
