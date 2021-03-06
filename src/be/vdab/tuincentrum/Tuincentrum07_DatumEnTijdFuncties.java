/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.tuincentrum;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author marjolein.vancelst
 */
public class Tuincentrum07_DatumEnTijdFuncties {

    private static final String URL = "jdbc:mysql://localhost/tuincentrum";
    private static final String USER = "cursist";
    private static final String PASSWORD = "cursist";
    private static final String SELECT_SQL = "select geboorte, voornaam, familienaam from werknemers where {fn month(geboorte)} = {fn month({fn curdat()})} order by {fn dayofmonth(geboorte)}";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SELECT_SQL)) {
            while (resultSet.next()) {
                System.out.println(resultSet.getDate("geboorte") + " "
                        + resultSet.getString("voornaam") + " "
                        + resultSet.getString("familienaam"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
