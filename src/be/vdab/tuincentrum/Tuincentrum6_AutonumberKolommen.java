/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.tuincentrum;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author marjolein.vancelst
 */
public class Tuincentrum6_AutonumberKolommen {

    private static final String URL = "jdbc:mysql://localhost/tuincentrum";
    private static final String USER = "cursist";
    private static final String PASSWORD = "cursist";
    private static final String INSERT_SQL
            = "insert into soorten(naam) values (?)";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.printf("Naam: ");
            String naam = scanner.nextLine();
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    PreparedStatement statement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, naam);
                statement.execute();
                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    resultSet.next();
                    System.out.println("De waarde van de gegenereerde sleutel: " + resultSet.getLong(1));
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }
}
