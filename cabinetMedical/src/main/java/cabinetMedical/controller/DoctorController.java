package cabinetMedical.controller;

import cabinetMedical.dto.DoctorDto;
import cabinetMedical.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping("/doctor/create")
    public String createDoctor(@Valid @RequestBody DoctorDto doctorDto, HttpServletResponse response) {
        Long result = doctorService.create(doctorDto);
        if (result >= 1) {
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
            return String.format("Doctor %s was created!", result);
        } else {
            response.setStatus(404);
            return "Doctor cannot be created - you are not logged with an admin account!";
        }
    }

    @GetMapping("/doctors")
    public List<DoctorDto> getAll() {
        return doctorService.getAllPersons();
    }

    @GetMapping("/doctor/details")
    public ResponseEntity<DoctorDto> getByName(@RequestParam String firstname,
                                     @RequestParam String lastname) {
        DoctorDto doctor = doctorService.getByName(firstname, lastname);
        if (doctor != null ) {
            return ResponseEntity
                    .ok()
                    .body(doctor);
         } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PatchMapping("/doctor/update")
    public String updateDoctor(@Valid @RequestBody DoctorDto doctorDto, HttpServletResponse response) {
        Long result = doctorService.updateDoctor(doctorDto);
        if (result >= 1) {
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
            return String.format("Doctor %s was updated!", result);
        } else {
            response.setStatus(404);
            return "Doctor cannot be updated - you are not logged with an admin account!";
        }

    }
}
