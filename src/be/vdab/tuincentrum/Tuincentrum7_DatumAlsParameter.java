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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author marjolein.vancelst
 */
public class Tuincentrum7_DatumAlsParameter {

    private static final String URL = "jdbc:mysql://localhost/tuincentrum";
    private static final String USER = "cursist";
    private static final String PASSWORD = "cursist";
    private static final String SELECT_SQL
            = "select indienst, voornaam, familienaam from werknemers where indienst >= ? order by indienst";

    public static void main(String[] args) {
        System.out.print("Datum vanaf (dd/mm/yyyy): ");
        try (Scanner scanner = new Scanner(System.in)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/y");
            Date datum = dateFormat.parse(scanner.nextLine());
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    PreparedStatement statement = connection.prepareStatement(SELECT_SQL)) {
                statement.setDate(1, new java.sql.Date(datum.getTime()));
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        System.out.println(resultSet.getDate("indienst") + " "
                                + resultSet.getString("voornaam") + " "
                                + resultSet.getString("familienaam"));
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ParseException ex2) {
            System.out.println("Verkeerde datum");
        }
    }
}
