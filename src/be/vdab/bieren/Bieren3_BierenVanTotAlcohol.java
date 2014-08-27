/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.bieren;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author marjolein.vancelst
 */
public class Bieren3_BierenVanTotAlcohol {
    
    private static final String URL = "jdbc:mysql://localhost/bieren";
    private static final String USER = "cursist";
    private static final String PASSWORD = "cursist";
    private static final String SELECT_SQL
            = "select alcohol, naam from bieren where alcohol between ? and ? order by alcohol, naam";
    
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Geef voor alcohol een minimum: ");
            BigDecimal minimum = scanner.nextBigDecimal();
            System.out.println("Geef voor alcohol een maximum: ");
            BigDecimal maximum = scanner.nextBigDecimal();
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    PreparedStatement statement = connection.prepareStatement(SELECT_SQL)) {
                statement.setBigDecimal(1, minimum);
                statement.setBigDecimal(2, maximum);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        System.out.println(resultSet.getBigDecimal("alcohol") + " "
                                + resultSet.getString("naam"));
                    }
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }
}
