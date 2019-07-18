package com.company.jdbc_util;

import com.company.com.company.exeception.DBException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A Singleton Java util connection class
 */
public class DBUtil {

    private static Connection connection;


    // make the db class private
    private DBUtil() {}

    public static synchronized Connection getConnection() throws DBException {
        if ( connection != null) {
            return connection;
        }
        else {
            try {
                String url = "Enter jdb url";
                String username = "Enter username";
                String password = "Enter password";

                // get and return connection
                connection = DriverManager.getConnection(
                        url, username, password);

                return connection;
            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }
    }

    public static synchronized  void closeConnection() throws DBException {
        if (connection != null) {
            try{
                connection.close();
            } catch (SQLException e) {
                throw new DBException(e.getMessage());
            } finally {
                connection = null;
            }
        }
    }
}
