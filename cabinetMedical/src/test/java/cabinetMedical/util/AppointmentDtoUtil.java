package cabinetMedical.util;

import cabinetMedical.domain.Appointment;
import cabinetMedical.dto.AppointmentDto;

import java.time.LocalDate;

import static cabinetMedical.util.DoctorUtil.aDoctor;
import static cabinetMedical.util.UserUtil.aUser;

public class AppointmentDtoUtil {
    public static AppointmentDto aAppointmentDto(String data, String hour) {
        return AppointmentDto.builder()
                .firstNameDoctor("Ioana")
                .lastNameDoctor("Bratu")
                .firstNameUser("Bianca")
                .lastNameUser("Buzoi")
                .username("Bianca10")
                .data(LocalDate.parse(data))
                .hour(hour)
                .notes("notes")
                .build();
    }
}
