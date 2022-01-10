package cabinetMedical.service;

import cabinetMedical.domain.Appointment;
import cabinetMedical.domain.Doctor;
import cabinetMedical.domain.User;
import cabinetMedical.dto.AppointmentDto;
import cabinetMedical.mapper.AppointmentMapper;
import cabinetMedical.repository.AppointmentRepository;
import cabinetMedical.repository.DoctorRepository;
import cabinetMedical.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentMapper appointmentMapper;

    private NotificationService notificationService = new NotificationService();

    public Long create(AppointmentDto appDto) {
        // get user
        User pacient = userRepository.findByUsername(appDto.getUsername());

        if (pacient.getLoggedStatus() != 1) {
            return (long)0;
        }

        // get doctor
        Doctor doctor = doctorRepository.findByName(appDto.getFirstNameDoctor(),appDto.getLastNameDoctor());

        if (doctor == null) {
            return (long) -1;
        }

        //verify if the doctor is available in that interval
        LocalDate requiredData = appDto.getData();
        String requiredHour = appDto.getHour();
        DayOfWeek dataDay = requiredData.getDayOfWeek();

        String programDayDoctor = "";
        if (dataDay.getValue() == 1) {
            programDayDoctor = doctor.getDoctorAvailableTime().getMonday();
        }
        if (dataDay.getValue() == 2) {
            programDayDoctor = doctor.getDoctorAvailableTime().getTuesday();
        }
        if (dataDay.getValue() == 3) {
            programDayDoctor = doctor.getDoctorAvailableTime().getWednesday();
        }
        if (dataDay.getValue() == 4) {
            programDayDoctor = doctor.getDoctorAvailableTime().getThursday();
        }
        if (dataDay.getValue() == 5) {
            programDayDoctor = doctor.getDoctorAvailableTime().getFriday();
        }
        if (dataDay.getValue() == 6) {
            programDayDoctor = doctor.getDoctorAvailableTime().getSaturday();
        }
        if (dataDay.getValue() == 7) {
            programDayDoctor = doctor.getDoctorAvailableTime().getSunday();
        }
        if (programDayDoctor.trim() == "-") {
            return (long) -2;
        }
        Integer hourStart = Integer.valueOf(programDayDoctor.trim().split("-")[0]);
        Integer hourFinal = Integer.valueOf(programDayDoctor.trim().split("-")[1]);

        if (Integer.valueOf(requiredHour) < hourStart || Integer.valueOf(requiredHour) > hourFinal) {
            return (long) -2;
        }

        // now you're sure that the doctor has program in the specified interval
        // we you'll check now if there is already an appointment to that doctor in the specified hour/day
        List <Appointment> allAppoints = appointmentRepository.getAll();
        for (Appointment a : allAppoints) {
            if (a.getDoctor().getLastName().equals(appDto.getLastNameDoctor()) && a.getDoctor().getFirstName().equals(appDto.getFirstNameDoctor())) {
                if (a.getHour().equals(appDto.getHour()) && a.getData().equals(appDto.getData())) {
                    return (long) -2;
                }
            }
        }

        Appointment appoint = new Appointment();

        appoint.setData(appDto.getData());
        appoint.setHour(appDto.getHour());
        appoint.setNotes(appDto.getNotes());
        appoint.setHour(appDto.getHour());
        appoint.setData(appDto.getData());

        appoint.setPacient(pacient);
        appoint.setDoctor(doctor);

        Appointment savedApp = appointmentRepository.save(appoint);

        notificationService.sendNotificationForAppointment(savedApp);

        //return appointmentMapper.mapToDto(savedApp);
        return savedApp.getId();
    }

    //getAllAppointments

    public List<AppointmentDto> getAllAppointments() {
        return appointmentRepository.getAll()
                .stream()
                .map(app -> appointmentMapper.mapToDto(app))
                .collect(Collectors.toList());
    }

    //getByUser
    public List<AppointmentDto> getByUser(String firstname, String lastname) {
        List<AppointmentDto> appoints = appointmentRepository.getAll()
                .stream()
                .map(app -> appointmentMapper.mapToDto(app))
                .collect(Collectors.toList());
        List<AppointmentDto> EmptyList = new ArrayList<>();
        for (AppointmentDto temp : appoints) {
            if (temp.getFirstNameUser().equals(firstname) && temp.getLastNameUser().equals(lastname)) {
                if (temp != null) {
                    EmptyList.add(temp);
                }
            }
        }

        return EmptyList;
    }

    //getByDoctor
    public List<AppointmentDto> getByDoctor(String firstname, String lastname) {
        List<AppointmentDto> appoints = appointmentRepository.getAll()
                .stream()
                .map(app -> appointmentMapper.mapToDto(app))
                .collect(Collectors.toList());
        List<AppointmentDto> EmptyList = new ArrayList<>();
        for (AppointmentDto temp : appoints) {
            if (temp.getFirstNameDoctor().equals(firstname) && temp.getLastNameDoctor().equals(lastname)) {
                if (temp != null) {
                    EmptyList.add(temp);
                }
            }
        }

        return EmptyList;
    }

    //getPassedApp
    public List<AppointmentDto> getPassedApp(String firstname) {
        List<AppointmentDto> appoints = appointmentRepository.getAll()
                .stream()
                .map(app -> appointmentMapper.mapToDto(app))
                .collect(Collectors.toList());
        List<AppointmentDto> EmptyList = new ArrayList<>();
        for (AppointmentDto temp : appoints) {
            if (temp.getUsername().equals(firstname) && temp.getData().isBefore(LocalDate.now())) {
                if (temp != null) {
                    EmptyList.add(temp);
                }
            }
        }

        return EmptyList;
    }

    //getFutureApp
    public List<AppointmentDto> getFutureApp(String firstname) {
        List<AppointmentDto> appoints = appointmentRepository.getAll()
                .stream()
                .map(app -> appointmentMapper.mapToDto(app))
                .collect(Collectors.toList());
        List<AppointmentDto> EmptyList = new ArrayList<>();
        for (AppointmentDto temp : appoints) {
            if (temp.getUsername().equals(firstname) && temp.getData().isAfter(LocalDate.now())) {
                if (temp != null) {
                    EmptyList.add(temp);
                }
            }
        }

        return EmptyList;
    }

    //removeAppoint
    @Transactional
    public int removeAppoint(int id) {
        Appointment app = appointmentRepository.getById((long) id);
        if (app == null) {
            return -1;
        }
        User user = userRepository.findByUsername(app.getPacient().getUsername());
        if (user.getLoggedStatus() == 1) {
            appointmentRepository.deleteAppointUsers((long) id);
            appointmentRepository.deleteAppointDoctors((long) id);
            appointmentRepository.deleteAppoint((long) id);
            return 1;
        } else  {
            return 0;
        }
    }
}
