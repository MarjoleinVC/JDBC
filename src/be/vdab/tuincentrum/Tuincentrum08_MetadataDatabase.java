/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.tuincentrum;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author marjolein
 */
public class Tuincentrum08_MetadataDatabase {

    private static final String URL = "jdbc:mysql://localhost/tuincentrum";
    private static final String USER = "cursist";
    private static final String PASSWORD = "cursist";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getDatabaseProductName() + " " + metaData.getDatabaseMajorVersion() + " " + metaData.getDatabaseMinorVersion());
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
