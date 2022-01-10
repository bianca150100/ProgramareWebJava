package cabinetMedical.mapper;
import cabinetMedical.domain.Appointment;
import cabinetMedical.domain.DonationAppointment;
import cabinetMedical.dto.AppointmentDto;
import cabinetMedical.dto.DonationAppointmentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DonationAppointmentMapper {
    @Mapping(target = "firstNameRequestPacient", source = "requestDonation.firstName")
    @Mapping(target = "lastNameRequestPacient", source = "requestDonation.lastName")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "firstNameUser", source = "user.firstName")
    @Mapping(target = "lastNameUser", source = "user.lastName")
    DonationAppointmentDto mapToDto(DonationAppointment appointment);

    @Mapping(target = "requestDonation.firstName", source = "firstNameRequestPacient")
    @Mapping(target = "requestDonation.lastName", source = "lastNameRequestPacient")
    @Mapping(target = "user.username", source = "username")
    @Mapping(target = "user.firstName", source = "firstNameUser")
    @Mapping(target = "user.lastName", source = "lastNameUser")
    DonationAppointment mapToEntity(DonationAppointmentDto app);
}
