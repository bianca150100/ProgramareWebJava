package cabinetMedical.mapper;

import cabinetMedical.domain.Doctor;
import cabinetMedical.dto.DoctorDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
    @Mapping(target = "specialization", source = "doctorDetails.specialization")
    @Mapping(target = "description", source = "doctorDetails.description")
    @Mapping(target = "experience", source = "doctorDetails.experience")
    @Mapping(target = "sunday", source = "doctorAvailableTime.sunday")
    @Mapping(target = "monday", source = "doctorAvailableTime.monday")
    @Mapping(target = "tuesday", source = "doctorAvailableTime.tuesday")
    @Mapping(target = "wednesday", source = "doctorAvailableTime.wednesday")
    @Mapping(target = "thursday", source = "doctorAvailableTime.thursday")
    @Mapping(target = "friday", source = "doctorAvailableTime.friday")
    @Mapping(target = "saturday", source = "doctorAvailableTime.saturday")
    DoctorDto mapToDto(Doctor doctor);

    @Mapping(target = "doctorDetails.specialization", source = "specialization")
    @Mapping(target = "doctorDetails.description", source = "description")
    @Mapping(target = "doctorDetails.experience", source = "experience")
    @Mapping(target = "doctorAvailableTime.sunday", source = "sunday")
    @Mapping(target = "doctorAvailableTime.monday", source = "monday")
    @Mapping(target = "doctorAvailableTime.tuesday", source = "tuesday")
    @Mapping(target = "doctorAvailableTime.wednesday", source = "wednesday")
    @Mapping(target = "doctorAvailableTime.thursday", source = "thursday")
    @Mapping(target = "doctorAvailableTime.friday", source = "friday")
    @Mapping(target = "doctorAvailableTime.saturday", source = "saturday")
    Doctor mapToEntity(DoctorDto user);

    //void updateCustomerFromDto(DoctorDto dto, @MappingTarget Doctor entity);
}
