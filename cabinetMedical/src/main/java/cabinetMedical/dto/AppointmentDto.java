package cabinetMedical.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {
    private Long id;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;

    @NotNull
    @Size(min = 1)
    @Size(max = 2)
    private String hour;

    @NotNull
    @Size(min = 2, message = "Notes should have at least 2 characters")
    private String notes;

    @NotNull
    @Size(min = 4, message = "Fistname should have at least 4 characters")
    private String firstNameDoctor;

    @NotNull
    @Size(min = 4, message = "Lastname should have at least 4 characters")
    private String lastNameDoctor;

    @NotNull
    @Size(min = 4, message = "Username should have at least 4 characters")
    private String username;

    @NotNull
    @Size(min = 4, message = "Fistname should have at least 4 characters")
    private String firstNameUser;

    @NotNull
    @Size(min = 4, message = "Lastname should have at least 4 characters")
    private String lastNameUser;
}
