package sk.medical.databaseconnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Register the driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Set up the connection with the correct database name
            String url = "jdbc:mysql://localhost:3306/mms?user=root&password=Sk@3117";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
        }
        return conn;
    }
}