/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import connects.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Appareil;
import model.Batterie;
import model.Consommation;
import model.HourConsommation;
import model.Panneau;

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
        System.out.println(sql);
        PreparedStatement statement = connexion.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            Appareil appareil = new Appareil();
            appareil.setId(result.getInt("id"));
            appareil.setNom(result.getString("nom"));
            appareil.setHeuredebut(LocalTime.of(Integer.parseInt(result.getString("heuredebut").split(":")[0]), Integer.parseInt(result.getString("heuredebut").split(":")[1])));
            appareil.setHeurefin(LocalTime.of(Integer.parseInt(result.getString("heurefin").split(":")[0]), Integer.parseInt(result.getString("heurefin").split(":")[1])));
            appareil.setPuissance(result.getInt("puissance"));
            appareil.setQuantite(result.getInt("quantite"));
            appareil.setClient_id(result.getInt("client_id"));
            retour.add(appareil);
        }
        return retour;
    }

    public static List<Appareil> findByClient_id(int id) throws SQLException, ClassNotFoundException {
        List<Appareil> retour = new ArrayList<>();
        Connection connexion = Connect.getConnection("postgresql");
        String sql = "select * from appareil where client_id = ?";
        System.out.println(sql);
        PreparedStatement statement = connexion.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            Appareil appareil = new Appareil();
            appareil.setId(result.getInt("id"));
            appareil.setNom(result.getString("nom"));
            appareil.setHeuredebut(LocalTime.of(Integer.parseInt(result.getString("heuredebut").split(":")[0]), Integer.parseInt(result.getString("heuredebut").split(":")[1])));
            appareil.setHeurefin(LocalTime.of(Integer.parseInt(result.getString("heurefin").split(":")[0]), Integer.parseInt(result.getString("heurefin").split(":")[1])));
            appareil.setPuissance(result.getInt("puissance"));
            appareil.setQuantite(result.getInt("quantite"));
            appareil.setClient_id(result.getInt("client_id"));
            retour.add(appareil);
        }
        return retour;
    }

    public List<List<Panneau>> getListePanneauSuggestion(int client_id) throws SQLException, ClassNotFoundException {
        List<List<Panneau>> list = new ArrayList<>();
        List<Appareil> clientappareil = this.findByClient_id(client_id);
        List<Panneau> servicePanneauFirst = new PanneauService().findAll();
        List<Panneau> servicePanneauSecond = new PanneauService().findAllSecond();
        list.add(new Appareil().getMeilleureCombinaisonPanneau(servicePanneauFirst.size(), new Appareil().getPuissanceMatin(clientappareil), servicePanneauFirst));
        list.add(new Appareil().getMeilleureCombinaisonPanneau(servicePanneauSecond.size(), new Appareil().getPuissanceMatin(clientappareil), servicePanneauSecond));
        return list;
    }

    public List<List<Batterie>> getListeBatterieSuggestion(int client_id) throws SQLException, ClassNotFoundException {
        List<List<Batterie>> list = new ArrayList<>();
        List<Appareil> clientappareil = this.findByClient_id(client_id);
        List<Batterie> serviceBatterieFirst = new BatterieService().findAll();
        List<Batterie> serviceBatterieSecond = new BatterieService().findAllSecond();
        list.add(new Appareil().getMeilleureCombinaisonBatterie(serviceBatterieFirst.size(), new Appareil().getPuissanceSoir(clientappareil), serviceBatterieFirst));
        list.add(new Appareil().getMeilleureCombinaisonBatterie(serviceBatterieSecond.size(), new Appareil().getPuissanceSoir(clientappareil), serviceBatterieSecond));
        return list;
    }

    public List<Batterie> getBatterieSuggestion(int client_id) throws SQLException, ClassNotFoundException {
        List<Appareil> clientappareil = AppareilService.findByClient_id(client_id);
        List<Batterie> serviceBatterieFirst = new BatterieService().findAll();
        return new Appareil().getMeilleureCombinaisonBatterie(serviceBatterieFirst.size(), new Appareil().getPuissanceSoir(clientappareil), serviceBatterieFirst);
    }

    public static List<List<Batterie>> trieBatterie(List<List<Batterie>> list) {
        List<List<Batterie>> retour = new ArrayList<>();
        int i = 0;
        for (i = 0; i < list.size() - 1; i++) {
            double firstprix = Appareil.sommePrixBatterie(list.get(i));
            double secondprix = Appareil.sommePrixBatterie(list.get(i + 1));
            if (firstprix < secondprix) {
                retour.add(list.get(i));
                retour.add(list.get(i + 1));
            } else {
                retour.add(list.get(i + 1));
                retour.add(list.get(i));
            }
        }
        return retour;
    }

    public static List<List<Panneau>> triePanneau(List<List<Panneau>> list) {
        List<List<Panneau>> retour = new ArrayList<>();
        int i = 0;
        for (i = 0; i < list.size() - 1; i++) {
            double firstprix = Appareil.sommePrixPanneau(list.get(i));
            double secondprix = Appareil.sommePrixPanneau(list.get(i + 1));
            if (firstprix < secondprix) {
                retour.add(list.get(i));
                retour.add(list.get(i + 1));
            } else {
                retour.add(list.get(i + 1));
                retour.add(list.get(i));
            }
        }
        return retour;
    }

    public static double sommePuissanceBatterie(List<Batterie> liste) {
        double retour = 0.0;
        for (int i = 0; i < liste.size(); i++) {
            Batterie m = (Batterie) liste.get(i);
            retour += m.getPuissance();
        }
        return retour;
    }

    public static double sommePuissancePanneau(List<Panneau> liste) {
        double retour = 0.0;
        for (int i = 0; i < liste.size(); i++) {
            Panneau m = (Panneau) liste.get(i);
            retour += m.getPuissance();
        }
        return retour;
    }

    public int getPuissanceTotal(int client_id) throws SQLException, ClassNotFoundException {
        List<Appareil> clientappareil = this.findByClient_id(client_id);
        return new Appareil().getPuissanceSoir(clientappareil) + new Appareil().getPuissanceMatin(clientappareil);
    }

    public int getPrixPanneau(List<Panneau> list) {
        return new Appareil().getPrixPanneau(list);
    }

}
