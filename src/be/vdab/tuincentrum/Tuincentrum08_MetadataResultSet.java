/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.tuincentrum;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author marjolein
 */
public class Tuincentrum08_MetadataResultSet {

    private static final String URL = "jdbc:mysql://localhost/tuincentrum";
    private static final String USER = "cursist";
    private static final String PASSWORD = "cursist";
    private static final String SQL_SELECT
            = "select id, voornaam, indienst from werknemers";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SQL_SELECT)) {
                ResultSetMetaData metaData = resultSet.getMetaData();
                for (int index = 1; index <= metaData.getColumnCount(); index++) {
                    System.out.println(metaData.getColumnName(index) + " " + metaData.getColumnTypeName(index));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
