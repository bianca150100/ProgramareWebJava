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
public class UserDto {

    private Long id;

    @NotNull
    @Size(min = 4, message = "Username should have at least 4 characters")
    private String username;

    @NotNull
    @Size(min = 3, message = "Firstname should have at least 3 characters")
    private String firstName;

    @NotNull
    @Size(min = 3, message = "LastName should have at least 3 characters")
    private String lastName;

    @NotNull
    @Email
    private String email;

    @NotEmpty
    @Size(min = 5, message = "password should have at least 5 characters")
    private String password;
}