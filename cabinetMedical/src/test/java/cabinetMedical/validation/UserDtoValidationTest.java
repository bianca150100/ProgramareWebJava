package cabinetMedical.validation;
import cabinetMedical.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static cabinetMedical.util.UserDtoUtil.aUserDto;
import static org.assertj.core.api.Assertions.assertThat;

public class UserDtoValidationTest {
    private Validator validator;
    private UserDto request;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        request = aUserDto("Username1","Fistname1","Lastname1");

    }

    @Test
    @DisplayName("Test request when all fields are valid")
    void test_request_whenIsValid() {
        //Arrange

        //Act
        Set<ConstraintViolation<UserDto>> violations = validator.validate(request);

        //Assert
        assertThat(violations).isEmpty();
    }

    @Test
    void test_request_whenUsername_isInvalid() {
        request.setUsername("us");

        //Act
        Set<ConstraintViolation<UserDto>> violations = validator.validate(request);

        //Assert
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    void test_request_whenFirstName_isInvalid() {
        request.setFirstName("fs");

        //Act
        Set<ConstraintViolation<UserDto>> violations = validator.validate(request);

        //Assert
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    void test_request_whenFirstName_LastName_isInvalid() {
        request.setFirstName("fs");
        request.setLastName("ln");

        //Act
        Set<ConstraintViolation<UserDto>> violations = validator.validate(request);

        //Assert
        assertThat(violations.size()).isEqualTo(2);
    }

    @Test
    void test_request_whenPassword_isInvalid() {
        request.setPassword("pass");

        //Act
        Set<ConstraintViolation<UserDto>> violations = validator.validate(request);

        //Assert
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    void test_request_whenEmail_isInvalid() {
        request.setEmail("email");

        //Act
        Set<ConstraintViolation<UserDto>> violations = validator.validate(request);

        //Assert
        assertThat(violations.size()).isEqualTo(1);
    }
}
