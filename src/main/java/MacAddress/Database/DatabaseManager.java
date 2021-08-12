package MacAddress.Database;

import java.io.File;
import java.io.IOException;
import java.sql.*;

/**
 * Database Manager will connect the sql database and do the executions
 * Initialize, insert, select and update is executable
 */
public class DatabaseManager {

    /**
     * Initializee the database - If the database is existing, the method will not execute the initialization
     * @return boolean - whether the database is initialized
     */
    // Will not be used by users (built-in for maintain database)
    public boolean initialize() {
        Connection connection = null;
        File dbFile = new File("src/main/resources/macAddress.db");
        if (!dbFile.exists()) {
            try {
                dbFile.createNewFile();
                // create a database connection
                connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/macAddress.db");
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);  // set timeout to 30 sec.

                statement.executeUpdate("drop table if exists MacAddress");
                statement.executeUpdate("create table MacAddress(address string, information string, primary key (address))");


            } catch (SQLException | IOException e) {
                // if the error message is "out of memory",
                // it probably means no database file is found
                System.err.println(e.getMessage());
            } finally {
                try {
                    if (connection != null)
                        connection.close();
                } catch (SQLException e) {
                    // connection close failed.
                    System.err.println(e.getMessage());
                }
            }
            return true;
        }
    return true;
    }

    /**
     * @param address the inserted address
     * @param information the inserted information
     * @return boolean - whether the insert execution is successful
     */
    public boolean insertInfo(String address, String information) {
        Connection connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/macAddress.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            String sqlQuery = "insert into MacAddress(address, information) values(?,?);";
            PreparedStatement preStmt = connection.prepareStatement(sqlQuery);
            preStmt.setString(1, address);;
            preStmt.setString(2, information);
            preStmt.executeUpdate();
            preStmt.close();

        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        return true;
    }

    /**
     * @param address the inserted address
     * @return String - the selected information's string from database
     */
    public String selectInfo(String address) {
        Connection connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/macAddress.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            String sqlQuery = "select information from MacAddress where address=?";
            PreparedStatement preStmt = connection.prepareStatement(sqlQuery);
            preStmt.setString(1, address);;
            String result = preStmt.executeQuery().getString("information");
            preStmt.close();
            return result;

        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    /**
     * @param address the updated address
     * @param information the updated information
     * @return boolean - whether the update execution is successful
     */
    public boolean updateInfo(String address, String information) {
        Connection connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/macAddress.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            String sqlQuery = "replace into MacAddress(address, information) values(?,?);";
            PreparedStatement preStmt = connection.prepareStatement(sqlQuery);
            preStmt.setString(1, address);;
            preStmt.setString(2, information);
            preStmt.executeUpdate();
            preStmt.close();

        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        return true;
    }

}
