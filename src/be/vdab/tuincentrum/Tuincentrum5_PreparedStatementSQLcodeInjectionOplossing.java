/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.tuincentrum;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author marjolein.vancelst
 */
public class Tuincentrum5_PreparedStatementSQLcodeInjectionOplossing {

    //Geen gevaar dat hacker foute variabele parameter ingeeft!
    private static final String URL = "jdbc:mysql://localhost/tuincentrum";
    private static final String USER = "cursist";
    private static final String PASSWORD = "cursist";
    private static final String UPDATE_SQL
            = "update planten set verkoopprijs = verkoopprijs *1.1 where naam=?";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Naam: ");
            String naam = scanner.nextLine();
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
                statement.setString(1, naam);
                System.out.println(statement.executeUpdate());
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }
}
