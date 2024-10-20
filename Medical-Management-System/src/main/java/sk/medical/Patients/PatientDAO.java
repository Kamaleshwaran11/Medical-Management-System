package sk.medical.Patients;
import sk.medical.databaseconnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class PatientDAO {

    // Add a patient
    public void addPatient(String patient_name, int patient_age, String gender, String patient_contact, String medicalHistory) throws SQLException {
        String query = "INSERT INTO patients (patient_name, patient_age, patient_gender, patient_contact, medical_history) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, patient_name);
            pstmt.setInt(2, patient_age);
            pstmt.setString(3, gender);
            pstmt.setString(4, patient_contact);
            pstmt.setString(5, medicalHistory);
            pstmt.executeUpdate();
            System.out.println("Patient added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get patient details
    public void getPatient(int patientId) throws SQLException {
        String query = "SELECT * FROM patients WHERE patient_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, patientId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("Patient ID: " + rs.getInt("patient_id"));
                System.out.println("Name: " + rs.getString("patient_name"));
                System.out.println("Age: " + rs.getInt("patient_age"));
                System.out.println("Gender: " + rs.getString("patient_gender"));
                System.out.println("Contact Info: " + rs.getString("patient_contact"));
                System.out.println("Medical History: " + rs.getString("medical_history"));
            } else {
                System.out.println("No patient found with ID " + patientId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update patient details
    public void updatePatient(int patientId, String name, int age, String gender, String contactInfo, String medicalHistory) throws SQLException {
        String query = "UPDATE patients SET patient_name = ?, patient_age = ?, patient_gender = ?, patient_contact = ?, medical_history = ? WHERE patient_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, gender);
            pstmt.setString(4, contactInfo);
            pstmt.setString(5, medicalHistory);
            pstmt.setInt(6, patientId);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Patient updated successfully.");
            } else {
                System.out.println("No patient found with ID " + patientId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete patient
    public void deletePatient(int patientId) throws SQLException {
        String query = "DELETE FROM patients WHERE patient_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, patientId);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Patient deleted successfully.");
            } else {
                System.out.println("No patient found with ID " + patientId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
