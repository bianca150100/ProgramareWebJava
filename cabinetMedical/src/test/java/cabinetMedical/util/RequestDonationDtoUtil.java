package cabinetMedical.util;

import cabinetMedical.dto.RequestDonationDto;

public class RequestDonationDtoUtil {
    public static RequestDonationDto aRequestDonation(String firstName, String lastName) {
        return RequestDonationDto.builder()
                .firstName(firstName)
                .lastName(lastName)
                .hospital("Spitalul Judetean")
                .bloodType("0")
                .number(10)
                .description("accident rutier")
                .build();
    }

}
