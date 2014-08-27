/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.tuincentrum;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author marjolein.vancelst
 */
public class Tuincentrum2_3_Connection {

    private static final String URL = "jdbc:mysql://localhost/tuincentrum";
    private static final String USER = "cursist";
    private static final String PASSWORD = "cursist";
    private static final String SELECT_SQL = "select naam from leveranciers where woonplaats = ? order by naam";

    public static void main(String[] args) {
        /* vanaf Java 7: object dat de interface AutoCloseable implementeert vermelden tussen ronde haakjes bij "try" 
         --> geen code voor sluiten, want compiler genereert de code.
         Onderstaande code is voor vóór java 7
        
         Connection connection = null;        
         try {
         connection = DriverManager.getConnection(URL, USER, PASSWORD);
         System.out.println("Connectie geopend");
         } catch (SQLException ex) {
         System.out.println(ex);
         } finally {
         if (connection != null) {
         try {
         connection.close();
         } catch (SQLException ex) {
         System.out.println(ex);
         }
         }
         }*/

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connectie geopend");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
