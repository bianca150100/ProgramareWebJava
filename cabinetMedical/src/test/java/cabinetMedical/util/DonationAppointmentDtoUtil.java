package cabinetMedical.util;

import cabinetMedical.dto.DonationAppointmentDto;
import cabinetMedical.dto.RequestDonationDto;

import java.time.LocalDate;

public class DonationAppointmentDtoUtil {
    public static DonationAppointmentDto aDonationAppointmentDto(String firstName, String lastName) {
        return DonationAppointmentDto.builder()
                .firstNameRequestPacient(firstName)
                .lastNameRequestPacient(lastName)
                .hour("12")
                .data(LocalDate.parse("2022-10-10"))
                .firstNameUser("Bianca")
                .lastNameUser("Buzoi")
                .username("Bianca100")
                .build();
    }
}
