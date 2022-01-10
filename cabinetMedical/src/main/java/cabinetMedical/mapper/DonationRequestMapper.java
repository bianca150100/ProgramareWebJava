package cabinetMedical.mapper;

import cabinetMedical.domain.RequestDonation;
import cabinetMedical.dto.RequestDonationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DonationRequestMapper {
    RequestDonationDto mapToDto(RequestDonation donationRequest);

    RequestDonation mapToEntity(RequestDonationDto donationRequestDto);
}
