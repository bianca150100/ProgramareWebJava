package cabinetMedical.service;

import cabinetMedical.domain.Doctor;
import cabinetMedical.dto.DoctorDto;
import cabinetMedical.mapper.DoctorMapper;
import cabinetMedical.repository.DoctorRepository;
import org.junit.jupiter.api.Test;

import static cabinetMedical.util.DoctorDtoUtil.aDoctorDto;
import static cabinetMedical.util.DoctorUtil.aDoctor;
import static org.junit.jupiter.api.Assertions.*;
import cabinetMedical.domain.Appointment;
import cabinetMedical.domain.User;
import cabinetMedical.domain.UserType;
import cabinetMedical.dto.UserDto;
import cabinetMedical.mapper.UserMapper;
import cabinetMedical.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static cabinetMedical.util.UserDtoUtil.aUserDto;
import static cabinetMedical.util.UserUtil.aUser;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private DoctorMapper doctorMapper;

    @Mock
    private DoctorService doctorService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    /*    @Test
    @DisplayName("Create new doctor when admin not logged")
   void create_when_admin_not_logged() {
        //arrange (given)
        User admin = aUser("Maria11","Maria","Creanga");
        admin.setUserType(UserType.ADMIN);
        admin.setLoggedStatus(0);
        userRepository.save(admin);

        DoctorDto doctorDto = aDoctorDto("Ion","Popescu");
        Doctor doctor = aDoctor("Ion","Popescu");
        Doctor savedDoctor = aDoctor(1L);

        when(userService.adminLogged()).thenReturn(0);

        //act (when)
        Long result = doctorService.create(doctorDto);

        //assert (then)
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("Create new doctor when admin logged")
    void create_when_admin_logged() {
        //arrange (given)
        User admin = aUser("Maria11","Maria","Creanga");
        admin.setUserType(UserType.ADMIN);
        admin.setLoggedStatus(1);
        userRepository.save(admin);
        List<User> users = new ArrayList<User>();
        users.add(admin);

        DoctorDto doctorDto = aDoctorDto("Ion","Popescu");
        Doctor doctor = aDoctor("Ion","Popescu");
        Doctor savedDoctor = aDoctor(1L);

        lenient().when(userRepository.getAll()).thenReturn(users);
        when(doctorMapper.mapToEntity(doctorDto)).thenReturn(doctor);
        when(doctorRepository.save(doctor)).thenReturn(savedDoctor);
        when(doctorMapper.mapToDto(savedDoctor)).thenReturn(doctorDto);

        //act (when)
        Long result = doctorService.create(doctorDto);

        //assert (then)
        System.out.println(result);

        assertThat(result).isEqualTo(0);

    }
*/
}