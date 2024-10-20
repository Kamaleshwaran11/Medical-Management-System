package sk.medical.Billings;
import sk.medical.databaseconnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BillingDAO {

    // Add a new billing record
    public void addBill(int patientId, int appointmentId, double totalAmount, String paymentStatus) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "INSERT INTO billing (patient_id, appointment_id, total_amount, payment_status) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, patientId);
            pstmt.setInt(2, appointmentId);
            pstmt.setDouble(3, totalAmount);
            pstmt.setString(4, paymentStatus);
            pstmt.executeUpdate();
            System.out.println("Bill added successfully.");
        } finally {
            connection.close();
        }
    }

    // Retrieve billing details by ID
    public void getBill(int billId) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT * FROM billing WHERE bill_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, billId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("Bill ID: " + rs.getInt("bill_id"));
                System.out.println("Patient ID: " + rs.getInt("patient_id"));
                System.out.println("Appointment ID: " + rs.getInt("appointment_id"));
                System.out.println("Total Amount: " + rs.getDouble("total_amount"));
                System.out.println("Payment Status: " + rs.getString("payment_status"));
            } else {
                System.out.println("No bill found with ID " + billId);
            }
        } finally {
            connection.close();
        }
    }

    // Update billing details
    public void updateBill(int billId, double totalAmount, String paymentStatus) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "UPDATE billing SET total_amount = ?, payment_status = ? WHERE bill_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setDouble(1, totalAmount);
            pstmt.setString(2, paymentStatus);
            pstmt.setInt(3, billId);
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Bill updated successfully.");
            } else {
                System.out.println("No bill found with ID " + billId);
            }
        } finally {
            connection.close();
        }
    }

    // Delete a bill
    public void deleteBill(int billId) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "DELETE FROM billing WHERE bill_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, billId);
            int rowsDeleted = pstmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Bill deleted successfully.");
            } else {
                System.out.println("No bill found with ID " + billId);
            }
        } finally {
            connection.close();
        }
    }
}


