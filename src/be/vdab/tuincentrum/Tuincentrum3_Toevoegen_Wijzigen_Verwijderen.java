/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.tuincentrum;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author marjolein.vancelst
 */
public class Tuincentrum3_Toevoegen_Wijzigen_Verwijderen {

    private static final String URL = "jdbc:mysql://localhost/tuincentrum";
    private static final String USER = "cursist";
    private static final String PASSWORD = "cursist";
    private static final String UPDATE_SQL
            = "update planten set verkoopprijs=verkoopprijs*1.1";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement statement = connection.createStatement()) {
            System.out.println(statement.executeUpdate(UPDATE_SQL));
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
