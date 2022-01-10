package cabinetMedical.service;

import cabinetMedical.domain.Doctor;
import cabinetMedical.domain.User;
import cabinetMedical.domain.UserType;
import cabinetMedical.dto.DoctorDto;
import cabinetMedical.dto.UserDto;
import cabinetMedical.mapper.DoctorMapper;
import cabinetMedical.repository.DoctorRepository;
import cabinetMedical.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorMapper doctorMapper;

    private UserService userService = new UserService();

    public Long create(DoctorDto doctorDto) {
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

        Doctor doctor = doctorMapper.mapToEntity(doctorDto);
        Doctor savedDoctor = doctorRepository.save(doctor);

        User user = new User();
        user.setUserType(UserType.DOCTOR);
        user.setUsername(doctor.getFirstName().concat(doctor.getLastName()));
        user.setFirstName(doctor.getFirstName());
        user.setLastName(doctor.getLastName());
        user.setEmail(doctor.getEmail());
        user.setPassword(doctor.getLastName().concat("2021"));
        user.setLoggedStatus(0);
        User userSaved = userRepository.save(user);

        //return doctorMapper.mapToDto(savedDoctor);
        return savedDoctor.getId();
    }

    public DoctorDto getOne(String firstName) {
        return doctorMapper.mapToDto(doctorRepository.findByFirstName(firstName));

    }

    public List<DoctorDto> getAllPersons() {
        return doctorRepository.getAll()
                .stream()
                .map(person -> doctorMapper.mapToDto(person))
                .collect(Collectors.toList());
    }

    public DoctorDto getByName(String firstname, String lastname) {
        return doctorMapper.mapToDto(doctorRepository.findByName(firstname, lastname));

    }

    public Long updateDoctor(DoctorDto doctorDto) {
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

        Doctor oldDoctor = doctorMapper.mapToEntity(doctorDto);
        Doctor savedDoctor = doctorRepository.save(oldDoctor);
        return savedDoctor.getId();
    }
}

