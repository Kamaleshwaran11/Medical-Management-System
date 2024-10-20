package sk.medical.Appointments;
import sk.medical.databaseconnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class AppointmentDAO {

    // Add a new appointment
    public void addAppointment(int patientId, int doctorId, Date appointmentDate, String status) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, status) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, patientId);
            pstmt.setInt(2, doctorId);
            pstmt.setDate(3, appointmentDate);
            pstmt.setString(4, status);
            pstmt.executeUpdate();
            System.out.println("Appointment added successfully.");
        } finally {
            connection.close();
        }
    }

    // Retrieve appointment details by ID
    public void getAppointment(int appointmentId) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT * FROM appointments WHERE appointment_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, appointmentId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("Appointment ID: " + rs.getInt("appointment_id"));
                System.out.println("Patient ID: " + rs.getInt("patient_id"));
                System.out.println("Doctor ID: " + rs.getInt("doctor_id"));
                System.out.println("Appointment Date: " + rs.getDate("appointment_date"));
                System.out.println("Status: " + rs.getString("status"));
            } else {
                System.out.println("No appointment found with ID " + appointmentId);
            }
        } finally {
            connection.close();
        }
    }

    // Update appointment details
    public void updateAppointment(int appointmentId, Date appointmentDate, String status) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "UPDATE appointments SET appointment_date = ?, status = ? WHERE appointment_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setDate(1, appointmentDate);
            pstmt.setString(2, status);
            pstmt.setInt(3, appointmentId);
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Appointment updated successfully.");
            } else {
                System.out.println("No appointment found with ID " + appointmentId);
            }
        } finally {
            connection.close();
        }
    }

    // Delete an appointment
    public void deleteAppointment(int appointmentId) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "DELETE FROM appointments WHERE appointment_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, appointmentId);
            int rowsDeleted = pstmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Appointment deleted successfully.");
            } else {
                System.out.println("No appointment found with ID " + appointmentId);
            }
        } finally {
            connection.close();
        }
    }
}

