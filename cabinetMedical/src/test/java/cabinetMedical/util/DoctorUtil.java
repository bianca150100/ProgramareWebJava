package cabinetMedical.util;

import cabinetMedical.domain.Doctor;
import cabinetMedical.domain.DoctorAvailableTime;
import cabinetMedical.domain.DoctorDetails;

public class DoctorUtil {
    public static Doctor aDoctor(String firstName, String lastName) {
        return Doctor.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email("email@email.com")
                .doctorDetails(DoctorDetails.builder()
                        .experience(12)
                        .specialization("medic stomatolog")
                        .build())
                .doctorAvailableTime(DoctorAvailableTime.builder()
                         .sunday("10-12")
                        .monday("10-12")
                        .thursday("10-12")
                        .wednesday("10-12")
                        .tuesday("10-12")
                        .saturday("10-12")
                        .friday("10-12")
                        .saturday("10-12")
                        .build())
                .build();
    }

    public static Doctor aDoctor(Long id) {
        return Doctor.builder()
                .id(id)
                .firstName("firstName")
                .lastName("lastName")
                .email("email@email.com")
                .doctorDetails(DoctorDetails.builder()
                        .experience(12)
                        .specialization("medic stomatolog")
                        .build())
                .doctorAvailableTime(DoctorAvailableTime.builder()
                        .sunday("10-12")
                        .monday("10-12")
                        .thursday("10-12")
                        .wednesday("10-12")
                        .tuesday("10-12")
                        .saturday("10-12")
                        .friday("10-12")
                        .saturday("10-12")
                        .build())
                .build();
    }
}
