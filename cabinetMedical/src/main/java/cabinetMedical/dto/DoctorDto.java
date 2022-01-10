package cabinetMedical.dto;

import cabinetMedical.domain.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {

    private Long id;

    @NotNull
    @Size(min = 4, message = "Fistname should have at least 4 characters")
    private String firstName;

    @NotNull
    @Size(min = 4, message = "Lastname should have at least 4 characters")
    private String lastName;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 2, message = "Specialization should have at least 2 characters")
    private String specialization;

    @NotNull
    @Size(min = 2, message = "Description should have at least 2 characters")
    private String description;

    @NotNull
    @Min(value=0, message="must be equal or greater than 0")
    @Max(value=45, message="must be equal or less than 45")
    private int experience;

    @Pattern(regexp="^(1[0-9]|20)-(1[0-9]|20)|-",message="length must be 5, format HH-HH or -")
    private String sunday;

    @Pattern(regexp="^(1[0-9]|20)-(1[0-9]|20)|-",message="length must be 5, format HH-HH or -")
    private String monday;

    @Pattern(regexp="^(1[0-9]|20)-(1[0-9]|20)|-",message="length must be 5, format HH-HH or -")
    private String tuesday;

    @Pattern(regexp="^(1[0-9]|20)-(1[0-9]|20)|-",message="length must be 5, format HH-HH or -")
    private String wednesday;

    @Pattern(regexp="^(1[0-9]|20)-(1[0-9]|20)|-",message="length must be 5, format HH-HH or -")
    private String thursday;

    @Pattern(regexp="^(1[0-9]|20)-(1[0-9]|20)|-",message="length must be 5, format HH-HH or -")
    private String friday;

    @Pattern(regexp="^(1[0-9]|20)-(1[0-9]|20)|-",message="length must be 5, format HH-HH or -")
    private String saturday;
}
