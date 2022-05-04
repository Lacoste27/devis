/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Tsiory
 */
public class Connect {

    public static Connection getConnection(String database) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        if (database.equals("mysql")) {
            String url = "jdbc:mysql://localhost:3306/devis";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("INFOS : Base de donnée mysql");
        } else if (database.equals("postgresql")) {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/devis", "postgres", "mdpprom13");
            System.out.println("INFOS : Base de donnée postgresql");
        }
        return connection;
    }
}
