package cabinetMedical.validation;

import cabinetMedical.dto.AppointmentDto;
import cabinetMedical.dto.DoctorDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static cabinetMedical.util.AppointmentDtoUtil.aAppointmentDto;
import static cabinetMedical.util.DoctorDtoUtil.aDoctorDto;
import static org.assertj.core.api.Assertions.assertThat;

public class AppointmentDtoValidation {
    private Validator validator;
    private AppointmentDto request;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        request = aAppointmentDto("2022-10-10", "12");
    }

    @Test
    @DisplayName("Test request when all fields are valid")
    void test_request_whenIsValid() {
        //Arrange

        //Act
        Set<ConstraintViolation<AppointmentDto>> violations = validator.validate(request);

        //Assert
        assertThat(violations).isEmpty();
    }

    @Test
    void test_request_whenFirstNameDoctor_isInvalid() {
        request.setFirstNameDoctor(null);

        //Act
        Set<ConstraintViolation<AppointmentDto>> violations = validator.validate(request);

        //Assert
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    void test_request_whenFirstName_LastName_isInvalid() {
        request.setFirstNameUser("fs");
        request.setLastNameUser("ln");

        //Act
        Set<ConstraintViolation<AppointmentDto>> violations = validator.validate(request);

        //Assert
        assertThat(violations.size()).isEqualTo(2);
    }

    @Test
    void test_request_whenProgram_isInvalid() {
        request.setHour("102");

        //Act
        Set<ConstraintViolation<AppointmentDto>> violations = validator.validate(request);

        //Assert
        assertThat(violations.size()).isEqualTo(1);
    }
}
