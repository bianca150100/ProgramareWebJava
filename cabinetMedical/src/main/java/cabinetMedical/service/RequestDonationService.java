package cabinetMedical.service;
import cabinetMedical.domain.RequestDonation;
import cabinetMedical.domain.User;
import cabinetMedical.domain.UserType;
import cabinetMedical.dto.RequestDonationDto;
import cabinetMedical.mapper.RequestDonationMapper;
import cabinetMedical.repository.RequestDonationRepository;
import cabinetMedical.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestDonationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RequestDonationRepository donationRequestRepository;

    @Autowired
    private RequestDonationMapper donationRequestMapper;

    public Long create(RequestDonationDto donationRequestDto) {
        //check if you are an admin - only admins can create doctors
        List<User> users = userRepository.getAll();
        int ok = 0;
        for (User u:users) {
            if (u.getUserType().equals(UserType.ADMIN) && u.getLoggedStatus() == 1) {
                ok = 1;
            }
        }
        if (ok == 0) {
            return (long) -1;
        }


        RequestDonation donationRequest = donationRequestMapper.mapToEntity(donationRequestDto);
        donationRequest.setData(LocalDateTime.now());
        RequestDonation savedDonation = donationRequestRepository.save(donationRequest);

        return (long) savedDonation.getId();
    }

    //getAllDonationsRequests
    public List<RequestDonationDto> getAllDonationsRequests() {
        return donationRequestRepository.getAll()
                .stream()
                .map(person -> donationRequestMapper.mapToDto(person))
                .collect(Collectors.toList());
    }

    //updateDonationRequest
    public Long updateDonationRequest(RequestDonationDto donationRequestDto) {
        //check if you are an admin - only admins can update doctors details
        List<User> users = userRepository.getAll();
        int ok = 0;
        for (User u:users) {
            if (u.getUserType().equals(UserType.ADMIN) && u.getLoggedStatus() == 1) {
                ok = 1;
            }
        }
        if (ok == 0) {
            return (long) -1;
        }

        RequestDonation oldDonation = donationRequestMapper.mapToEntity(donationRequestDto);
        RequestDonation savedDoctor = donationRequestRepository.save(oldDonation);
        return savedDoctor.getId();
    }
}
