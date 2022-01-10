package cabinetMedical.controller;

import cabinetMedical.dto.AppointmentDto;
import cabinetMedical.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/appointment/create")
    public String createAppointment(@Valid @RequestBody AppointmentDto appointmentDto, HttpServletResponse response) {
        long result = appointmentService.create(appointmentDto);

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

    @GetMapping("/appointments")
    public List<AppointmentDto> getAll() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/appointmentsUser")
    public List<AppointmentDto> getByUser(@RequestParam String firstname,
                               @RequestParam String lastname) {
        return appointmentService.getByUser(firstname, lastname);
    }

    @GetMapping("/appointmentsDoctor")
    public List<AppointmentDto> getByDoctor(@RequestParam String firstname,
                               @RequestParam String lastname) {
        return appointmentService.getByDoctor(firstname, lastname);
    }

    @GetMapping("/futureAppointmentsUser")
    public List<AppointmentDto> futureApp(@RequestParam String username) {
        return appointmentService.getFutureApp(username);
    }

    @GetMapping("/passedAppointmentsUser")
    public List<AppointmentDto> passedApp(@RequestParam String username) {
        return appointmentService.getPassedApp(username);
    }

    @DeleteMapping("/deleteAppointment")
    public String cleanupAppointments(@RequestParam int id , HttpServletResponse response) {
        int result = appointmentService.removeAppoint(id);
        if (result >= 1) {
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
