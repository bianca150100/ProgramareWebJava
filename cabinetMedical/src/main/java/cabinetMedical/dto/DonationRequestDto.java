package cabinetMedical.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonationRequestDto {
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String hospital;

    @NotNull
    private String description;

    @NotNull
    private String bloodType;

    @NotNull
    private int number;

    private LocalDateTime date;
}
