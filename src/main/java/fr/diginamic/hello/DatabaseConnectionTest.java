package fr.diginamic.hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionTest {

    public static void main(String[] args) {
        Connection conn = null;

        try {
            String url = "jdbc:mysql://localhost:3306/recensement";
            String username = "root";
            String password = "3-!zPv%2X62Zo";

            conn = DriverManager.getConnection(url, username, password);

            if (conn != null) {
                System.out.println("Connected to database successfully!");
            } else {
                System.err.println("Failed to connect to database.");
            }
        } catch (SQLException e) {
            System.err.println("Failed to connect to database.");
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

