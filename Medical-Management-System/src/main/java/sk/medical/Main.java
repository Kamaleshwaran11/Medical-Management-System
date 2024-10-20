package sk.medical;

import sk.medical.Appointments.AppointmentDAO;
import sk.medical.Billings.BillingDAO;
import sk.medical.Doctors.DoctorDAO;
import sk.medical.Patients.PatientDAO;

import java.sql.*;
public class Main {
    public static void main(String[] args) {
        PatientDAO patientDAO = new PatientDAO();
        DoctorDAO doctorDAO=new DoctorDAO();
        AppointmentDAO appointmentDAO=new AppointmentDAO();
        BillingDAO billingDAO=new BillingDAO();

        try {
            //CRUD operations for Patient Management
            System.out.println("---Patient Management---");
            // Create a new patient
            patientDAO.addPatient("John Doe", 30, "Male", "123-456-7890", "No known allergies");

            // Read a patient record
            patientDAO.getPatient(1);

            // Update a patient record
            patientDAO.updatePatient(1, "John Doe", 31, "Male", "123-456-7890", "Updated medical history");

            // Delete a patient record
            //patientDAO.deletePatient(1); //uncomment to delete patient

            // CRUD operations for Doctor Management
            System.out.println("---- Doctor Management ----");
            // Add a new doctor
            doctorDAO.addDoctor("Dr. John Smith", "Cardiologist", "123-456-7890", "9:00 AM - 5:00 PM");
            // Retrieve doctor details
            doctorDAO.getDoctor(1);  // Assuming doctor with ID 1 exists
            // Update doctor information
            doctorDAO.updateDoctor(1, "Dr. John Smith", "Cardiologist", "123-456-7890", "10:00 AM - 6:00 PM");
            // Delete doctor
            // doctorDAO.deleteDoctor(1);  // Uncomment to delete doctor

            // CRUD operations for Appointment Scheduling
            System.out.println("\n---- Appointment Scheduling ----");
            // Add a new appointment
            Date appointmentDate = Date.valueOf("2024-10-15");  // Appointment date
            appointmentDAO.addAppointment(1, 1, appointmentDate, "Scheduled");  // patientId=1, doctorId=1
            // Retrieve appointment details
            appointmentDAO.getAppointment(1);  // Assuming appointment with ID 1 exists
            // Update appointment details
            Date newAppointmentDate = Date.valueOf("2024-10-20");
            appointmentDAO.updateAppointment(1, newAppointmentDate, "Rescheduled");
            // Delete appointment
            // appointmentDAO.deleteAppointment(1);  // Uncomment to delete appointment

            // CRUD operations for Billing Management
            System.out.println("\n---- Billing Management ----");
            // Add a new billing record
            billingDAO.addBill(1, 1, 150.00, "Pending");  // patientId=1, appointmentId=1
            // Retrieve billing details
            billingDAO.getBill(1);  // Assuming bill with ID 1 exists
            // Update billing information
            billingDAO.updateBill(1, 150.00, "Paid");
            // Delete billing record
            // billingDAO.deleteBill(1);  // Uncomment to delete bill

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}