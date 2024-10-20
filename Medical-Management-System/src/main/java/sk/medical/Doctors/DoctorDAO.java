package sk.medical.Doctors;
import sk.medical.databaseconnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorDAO {

    // Add a new doctor
    public void addDoctor(String name, String specialty, String contactInfo, String availability) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "INSERT INTO doctors (name, specialty, contact_info, availability) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, specialty);
            pstmt.setString(3, contactInfo);
            pstmt.setString(4, availability);
            pstmt.executeUpdate();
            System.out.println("Doctor added successfully.");
        } finally {
            connection.close();
        }
    }

    // Retrieve doctor details by ID
    public void getDoctor(int doctorId) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT * FROM doctors WHERE doctor_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, doctorId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("Doctor ID: " + rs.getInt("doctor_id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Specialty: " + rs.getString("specialty"));
                System.out.println("Contact Info: " + rs.getString("contact_info"));
                System.out.println("Availability: " + rs.getString("availability"));
            } else {
                System.out.println("No doctor found with ID " + doctorId);
            }
        } finally {
            connection.close();
        }
    }

    // Update doctor details
    public void updateDoctor(int doctorId, String name, String specialty, String contactInfo, String availability) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "UPDATE doctors SET name = ?, specialty = ?, contact_info = ?, availability = ? WHERE doctor_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, specialty);
            pstmt.setString(3, contactInfo);
            pstmt.setString(4, availability);
            pstmt.setInt(5, doctorId);
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Doctor updated successfully.");
            } else {
                System.out.println("No doctor found with ID " + doctorId);
            }
        } finally {
            connection.close();
        }
    }

    // Delete a doctor
    public void deleteDoctor(int doctorId) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "DELETE FROM doctors WHERE doctor_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, doctorId);
            int rowsDeleted = pstmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Doctor deleted successfully.");
            } else {
                System.out.println("No doctor found with ID " + doctorId);
            }
        } finally {
            connection.close();
        }
    }
}

