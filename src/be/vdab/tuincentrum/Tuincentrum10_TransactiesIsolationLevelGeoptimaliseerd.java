/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.tuincentrum;

import java.math.BigDecimal;
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
public class Tuincentrum10_TransactiesIsolationLevelGeoptimaliseerd {

    private static final String URL = "jdbc:mysql://localhost/tuincentrum";
    private static final String USER = "cursist";
    private static final String PASSWORD = "cursist";
    private static final String SQL_SELECT
            = "select verkoopprijs from planten where id=?";
    private static final String SQL_UPDATE
            = "update planten set verkoopprijs=? where id=? and ?<=verkoopprijs*1.1";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Id: ");
            int id = scanner.nextInt();
            System.out.print("Verkoopprijs: ");
            BigDecimal nieuwePrijs = scanner.nextBigDecimal();
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    PreparedStatement statementUpdate
                    = connection.prepareStatement(SQL_UPDATE)) {
                statementUpdate.setBigDecimal(1, nieuwePrijs);
                statementUpdate.setInt(2, id);
                statementUpdate.setBigDecimal(3, nieuwePrijs);
                if (statementUpdate.executeUpdate() == 0) {
                    try (PreparedStatement statementSelect
                            = connection.prepareStatement(SQL_SELECT)) {
                        statementSelect.setInt(1, id);
                        try (ResultSet resultSet = statementSelect.executeQuery()) {
                            System.out.println(resultSet.next()
                                    ? "Nieuwe verkoopprijs te hoog"
                                    : "Plant niet gevonden");
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
