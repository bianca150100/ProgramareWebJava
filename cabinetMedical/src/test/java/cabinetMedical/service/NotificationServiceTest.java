package cabinetMedical.service;

import cabinetMedical.domain.Appointment;
import cabinetMedical.domain.Doctor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class NotificationServiceTest {

    private NotificationService notificationService = new NotificationService();

    @Test
    @DisplayName("Send notification when appointment is null")
    void sendNotificationForAppointmentNull() {
        //arrange (given)
        Appointment appointment = null;

        //act (when)
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> notificationService.sendNotificationForAppointment(appointment));

        //assert (then)
        assertEquals("Cannot send notification. Appointment is null.",
                exception.getMessage());
    }

    @Test
    @DisplayName("Send notification when appointment is correct")
    void sendNotificationAppointmentRequired() {
        //arrange
        Doctor doctor = new Doctor();
        doctor.setFirstName("Ioana");
        doctor.setLastName("Mihai");
        Appointment appointment = new Appointment();
        LocalDate localdata = LocalDate.now();
        appointment.setData(localdata);
        appointment.setHour("18");
        appointment.setDoctor(doctor);

        //act
        boolean result = notificationService.sendNotificationForAppointment(appointment);

        //assert
        assertTrue(result);
    }
}