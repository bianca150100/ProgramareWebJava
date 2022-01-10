package cabinetMedical.util;

import cabinetMedical.dto.DoctorDto;

public class DoctorDtoUtil {
    public static DoctorDto aDoctorDto(String firstName, String lastName) {
        return DoctorDto.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email("email@email.com")
                .description("medic stomatolog")
                .specialization("medic stomatolog")
                .experience(12)
                .sunday("11-12")
                .monday("11-12")
                .thursday("11-12")
                .wednesday("11-12")
                .tuesday("11-12")
                .saturday("11-12")
                .friday("11-12")
                .saturday("11-12")
                .build();
    }


}
