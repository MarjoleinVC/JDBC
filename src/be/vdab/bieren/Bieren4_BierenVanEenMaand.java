/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.bieren;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author marjolein
 */
public class Bieren4_BierenVanEenMaand {

    private static final String URL = "jdbc:mysql://localhost/bieren";
    private static final String USER = "cursist";
    private static final String PASSWORD = "cursist";
    private static final String SELECT_SQL = "select verkochtsinds, naam from bieren where {fn month(verkochtsinds)} = ? order by verkochtsinds";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Geef de maand die onderzocht wordt (getal van 1 tot 12)");
            int maand = scanner.nextInt();
            if (maand >= 1 && maand <= 12) {
                try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                        PreparedStatement statement = connection.prepareStatement(SELECT_SQL)) {
                    statement.setInt(1, maand);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            System.out.println(resultSet.getDate("verkochtsinds") + " " + resultSet.getString("naam"));
                        }
                    }
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            } else {
                System.out.println("Geef een correcte maand op.");
            }
        }
    }
}
