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
import java.util.Scanner;

/**
 *
 * @author marjolein.vancelst
 */
public class Tuincentrum5_PreparedStatementSQLcodeInjection {

    //SLECHTE GEWOONTE: GEVAAR VOOR HACKERS!
    private static final String URL = "jdbc:mysql://localhost/tuincentrum";
    private static final String USER = "cursist";
    private static final String PASSWORD = "cursist";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Naam: ");
            String naam = scanner.nextLine();
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    Statement statement = connection.createStatement()) {
                System.out.println(statement.executeUpdate(
                        "update planten set verkoopprijs = verkoopprijs * 1.1 where naam='" //als hacker volgende ingeeft: ' or ''=' kan hij alle prijzen wijzigen!
                        + naam + "'"));
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }
}
