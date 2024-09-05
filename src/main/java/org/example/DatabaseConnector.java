package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {
    public Connection connect() {
        Connection connection = null;
        try {
            // Get the path to the resource folder
            String url = "jdbc:sqlite:" + getClass().getClassLoader().getResource("books.db").getPath();
            connection = DriverManager.getConnection(url);
            System.out.println("Successfully connected to the database!");
        } catch (SQLException e) {
            System.out.println("Error connecting to the database.");
            e.printStackTrace();
        }
        return connection;
    }
    public void selectAllBooks() {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            connection = this.connect();
            String sql = "SELECT * FROM Books2";  // Updated table name
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);

            // Iterate over the results
            while (rs.next()) {
                System.out.println(rs.getString("title") + "\t" +
                        rs.getString("author") + "\t" +
                        rs.getDouble("price"));
            }
        } catch (SQLException e) {
            System.out.println("Error executing SELECT statement");
            e.printStackTrace();
        } finally {
            // Ensure resources are closed
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
