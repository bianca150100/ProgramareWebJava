package cabinetMedical.util;

import cabinetMedical.domain.Appointment;
import cabinetMedical.domain.User;
import cabinetMedical.domain.UserType;
import org.mapstruct.control.MappingControl;

import java.time.LocalDate;

import static cabinetMedical.util.DoctorUtil.aDoctor;
import static cabinetMedical.util.UserUtil.aUser;

public class AppointmentUtil {
    public static Appointment aAppointment(String hour, String data) {
        return Appointment.builder()
                .Data(LocalDate.parse(data))
                .Hour(hour)
                .pacient(aUser("Bia123","Bianca","Buzoi"))
                .doctor(aDoctor("Ioana","Bratu"))
                .build();
    }
}
