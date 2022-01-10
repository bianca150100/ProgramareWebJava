package cabinetMedical.mapper;
import cabinetMedical.domain.Appointment;
import cabinetMedical.dto.AppointmentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    @Mapping(target = "firstNameDoctor", source = "doctor.firstName")
    @Mapping(target = "lastNameDoctor", source = "doctor.lastName")
    @Mapping(target = "username", source = "pacient.username")
    @Mapping(target = "firstNameUser", source = "pacient.firstName")
    @Mapping(target = "lastNameUser", source = "pacient.lastName")
    AppointmentDto mapToDto(Appointment appointment);

    @Mapping(target = "doctor.firstName", source = "firstNameDoctor")
    @Mapping(target = "doctor.lastName", source = "lastNameDoctor")
    @Mapping(target = "pacient.username", source = "username")
    @Mapping(target = "pacient.firstName", source = "firstNameUser")
    @Mapping(target = "pacient.lastName", source = "lastNameUser")
    Appointment mapToEntity(AppointmentDto app);

}
