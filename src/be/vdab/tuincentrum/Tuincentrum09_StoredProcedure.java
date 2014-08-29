/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.tuincentrum;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author marjolein
 */
public class Tuincentrum09_StoredProcedure {

    private static final String URL = "jdbc:mysql://locahlhost/tuincentrum?noAccessToProcedureBodies=true";
    private static final String USER = "cursist";
    private static final String PASSWORD = "cursist";
    private static final String CALL = "{call PlantenMetEenWoord(?)}";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Woord: ");
            String woord = scanner.nextLine();
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    CallableStatement statement = connection.prepareCall(CALL)) {
                statement.setString(1, "%" + woord + "%");
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        System.out.println(resultSet.getString("naam"));
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
