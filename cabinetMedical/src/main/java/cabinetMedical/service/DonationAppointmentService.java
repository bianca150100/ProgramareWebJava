package cabinetMedical.service;

import cabinetMedical.domain.*;
import cabinetMedical.dto.AppointmentDto;
import cabinetMedical.dto.DonationAppointmentDto;
import cabinetMedical.mapper.AppointmentMapper;
import cabinetMedical.mapper.DonationAppointmentMapper;
import cabinetMedical.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class DonationAppointmentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RequestDonationRepository donationRequestRepository;

    @Autowired
    private DonationAppointmentRepository donationAppointmentRepository;

    @Autowired
    private DonationAppointmentMapper donationAppointmentMapper;

    public Long create(DonationAppointmentDto appDto) {
        // get user
        User pacient = userRepository.findByUsername(appDto.getUsername());

        if (pacient.getLoggedStatus() != 1) {
            return (long)0;
        }

        // get donation request
        RequestDonation donationRequest = donationRequestRepository.findByName(appDto.getFirstNameRequestPacient(),appDto.getLastNameRequestPacient());

        if (donationRequest == null) {
            return (long) -1;
        }

        // we you'll check now if there is already an appointment in the specified hour/day
        List<DonationAppointment> allAppoints = donationAppointmentRepository.getAll();
        for (DonationAppointment a : allAppoints) {
                if (a.getHour().equals(appDto.getHour()) && a.getData().equals(appDto.getData())) {
                    return (long) -2;
                }
        }

        DonationAppointment appoint = new DonationAppointment();

        appoint.setData(appDto.getData());
        appoint.setHour(appDto.getHour());

        appoint.setUser(pacient);
        appoint.setRequestDonation(donationRequest);

        DonationAppointment savedApp = donationAppointmentRepository.save(appoint);

        //return appointmentMapper.mapToDto(savedApp);
        return savedApp.getId();
    }

    //getAllDonationAppointments
    public List<DonationAppointmentDto> getAllDonationAppointments() {
        return donationAppointmentRepository.getAll()
                .stream()
                .map(app -> donationAppointmentMapper.mapToDto(app))
                .collect(Collectors.toList());
    }

    //getAllDonationAppointmentsByUser
    public List<DonationAppointmentDto> getAllDonationAppointmentsByUser(String firstname, String lastname) {
        List<DonationAppointmentDto> appoints = donationAppointmentRepository.getAll()
                .stream()
                .map(app -> donationAppointmentMapper.mapToDto(app))
                .collect(Collectors.toList());
        List<DonationAppointmentDto> EmptyList = new ArrayList<>();
        for (DonationAppointmentDto temp : appoints) {
            if (temp != null) {
            if (temp.getFirstNameUser().equals(firstname) && temp.getLastNameUser().equals(lastname)) {
                    EmptyList.add(temp);
                }
            }
        }

        return EmptyList;
    }

    //removeAppoint
    @Transactional
    public int removeAppoint(int id) {
        DonationAppointment app = donationAppointmentRepository.getById((long) id);
        if (app == null) {
            return -1;
        }
        User user = userRepository.findByUsername(app.getUser().getUsername());
        if (user.getLoggedStatus() == 1) {
            donationAppointmentRepository.deleteAppointUsers((long) id);
            donationAppointmentRepository.deleteAppointDonationRequest((long) id);
            donationAppointmentRepository.deleteAppoint((long) id);
            return 1;
        } else  {
            return 0;
        }
    }

}
