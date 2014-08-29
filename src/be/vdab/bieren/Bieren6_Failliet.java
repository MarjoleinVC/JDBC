/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.bieren;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author marjolein
 */
public class Bieren6_Failliet {

    private static final String URL = "jdbc:mysql://localhost/bieren";
    private static final String USER = "cursist";
    private static final String PASSWORD = "cursist";
    private static final String SQL_UPDATE1
            = "update bieren set brouwerid = 2 where brouwerid = 1 and alcohol >= 8.5";
    private static final String SQL_UPDATE2
            = "update bieren set brouwerid = 3 where brouwerid = 1";
    private static final String SQL_DELETE
            = "delete from brouwers where id = 1";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.addBatch(SQL_UPDATE1);
            statement.addBatch(SQL_UPDATE2);
            statement.addBatch(SQL_DELETE);
            statement.executeBatch();
            connection.commit();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
