package cabinetMedical.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDonationDto {
    private Long id;

    @NotNull
    @Size(min = 4, message = "Fistname should have at least 4 characters")
    private String firstName;

    @NotNull
    @Size(min = 4, message = "Lastname should have at least 4 characters")
    private String lastName;

    @NotNull
    @Size(min = 3, message = "HospitalName should have at least 3 characters")
    private String hospital;

    @NotNull
    @Size(min = 2, message = "Description should have at least 2 characters")
    private String description;

    @NotNull
    @Pattern(regexp="^A|B|0|AB",message="Group should be A|B|0|AB")
    private String bloodType;

    @NotNull
    @Min(value=1, message="must be equal or greater than 1")
    private int number;

}
