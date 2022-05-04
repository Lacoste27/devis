/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import model.Appareil;
import model.Consommation;
import model.HourConsommation;
import services.AppareilService;
/**
 *
 * @author Tsiory
 */
public class Main {

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        List<Appareil> appareillist = AppareilService.findByClient_id(18);
        HourConsommation[] consommation = Consommation.nightConsumptionPerHour(appareillist);
        
    }
    
}
