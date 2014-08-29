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
 * @author marjolein
 */
public class Tuincentrum10_Transacties {

    private static final String URL = "jdbc:mysql://localhost/tuincentrum";
    private static final String USER = "cursist";
    private static final String PASSWORD = "cursist";
    private static final String SQL_UPDATE1 = "update planten set verkoopprijs ="
            + "verkoopprijs*1.1 where verkoopprijs >= 100";
    private static final String SQL_UPDATE2 = "update planten set verkoopprijs ="
            + "verkoopprijs*1.05 where verkoopprijs < 100";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.execute(SQL_UPDATE1);
            statement.execute(SQL_UPDATE2);
            connection.commit();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
