package cabinetMedical.controller;

import cabinetMedical.dto.*;
import cabinetMedical.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
public class DonationAppointmentController {

    @Autowired
    private DonationAppointmentService donationAppointmentService;

    @PostMapping("/donationAppointment/create")
    public String createAppointment(@Valid @RequestBody DonationAppointmentDto appointmentDto, HttpServletResponse response) {
        long result = donationAppointmentService.create(appointmentDto);

        if (result >= 1) {
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
            return String.format("Appointment %s was created. See you soon!", result);
        } else if (result == 0) {
            response.setStatus(404);
            return "Appointment cannot be created - you are not logged with the corresponding account";
        } else if (result == -1) {
            response.setStatus(404);
            return "Appointment cannot be created - the specified doctor does not exist";
        } else {
            response.setStatus(404);
            return "Appointment cannot be created - specified data is not available";
        }
    }

    @GetMapping("/donationAppointments")
    public List<DonationAppointmentDto> getAllDonationAppointments() {
        return donationAppointmentService.getAllDonationAppointments();
    }

    @GetMapping("/donationAppointmentsUser")
    public List<DonationAppointmentDto> getAllDonationAppointmentsByUser(@RequestParam String firstname,
                                          @RequestParam String lastname) {
        return donationAppointmentService.getAllDonationAppointmentsByUser(firstname, lastname);
    }

    @DeleteMapping("/deleteDonationAppointment")
    public String cleanupDonationAppointments(@RequestParam int id , HttpServletResponse response) {
        int result = donationAppointmentService.removeAppoint(id);
        if (result == 1) {
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
            return String.format("Appointment %s was removed", id);
        } else if (result == 0){
            response.setStatus(404);
            return String.format("Appointment %s was not removed - you are not logged with the correct account", id);
        } else {
            response.setStatus(404);
            return String.format("Appointment %s is invalid", id);
        }
    }
}
