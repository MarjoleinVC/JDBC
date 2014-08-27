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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author marjolein.vancelst
 */
public class Tuincentrum5_PreparedStatementMeerdereXuitvoeren {
    private static final String URL = "jdbc:mysql://localhost/tuincentrum";
    private static final String USER = "cursist";
    private static final String PASSWORD = "cursist";
    private static final String INSERT_SQL =
            "insert into soorten(naam) values (?)";
    
    @SuppressWarnings("empty-statement")
    public static void main(String[] args){
        List<String> namen = new ArrayList<>();
        try (Scanner scanner = new Scanner(System.in)){
            System.out.println("Tik soortnamen, tik stop na de laatste naam");
            for (String naam; !"stop".equalsIgnoreCase(naam = scanner.nextLine());
                    namen.add(naam));
        }
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement statement = connection.prepareStatement(INSERT_SQL)){
            for (String naam: namen){
                statement.setString(1, naam);
                System.out.println(statement.executeUpdate() + " " + naam);
            }
        }catch (SQLException ex){
            System.out.println(ex);
        }
    }
}
