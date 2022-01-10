package cabinetMedical.validation;

import cabinetMedical.dto.DoctorDto;
import cabinetMedical.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static cabinetMedical.util.DoctorDtoUtil.aDoctorDto;
import static org.assertj.core.api.Assertions.assertThat;

public class DoctorDtoValidationTest {
    private Validator validator;
    private DoctorDto request;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        request = aDoctorDto("Fistname1","Lastname1");

    }
    @Test
    @DisplayName("Test request when all fields are valid")
    void test_request_whenIsValid() {
        //Arrange

        //Act
        Set<ConstraintViolation<DoctorDto>> violations = validator.validate(request);

        //Assert
        assertThat(violations).isEmpty();
    }

    @Test
    void test_request_whenFirstName_isInvalid() {
        request.setFirstName(null);

        //Act
        Set<ConstraintViolation<DoctorDto>> violations = validator.validate(request);

        //Assert
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    void test_request_whenFirstName_LastName_isInvalid() {
        request.setFirstName("fs");
        request.setLastName("ln");

        //Act
        Set<ConstraintViolation<DoctorDto>> violations = validator.validate(request);

        //Assert
        assertThat(violations.size()).isEqualTo(2);
    }

    @Test
    void test_request_whenProgram_isInvalid() {
        request.setSaturday("10");

        //Act
        Set<ConstraintViolation<DoctorDto>> violations = validator.validate(request);

        //Assert
        assertThat(violations.size()).isEqualTo(1);
    }
}
