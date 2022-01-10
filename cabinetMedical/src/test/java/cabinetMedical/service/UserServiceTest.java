package cabinetMedical.service;

import cabinetMedical.domain.Appointment;
import cabinetMedical.domain.User;
import cabinetMedical.domain.UserType;
import cabinetMedical.dto.UserDto;
import cabinetMedical.exception.UserNotFoundException;
import cabinetMedical.mapper.UserMapper;
import cabinetMedical.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static cabinetMedical.util.UserDtoUtil.aUserDto;
import static cabinetMedical.util.UserUtil.aUser;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("Create new pacient")
    void create() {
        //arrange (given)
        UserDto userDto = aUserDto("Maria11","Maria","Creanga");
        UserType type = UserType.PACIENT;
        User user = aUser("Maria11","Maria","Creanga");
        User savedUser = aUser(1L);

        when(userMapper.mapToEntity(userDto)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(savedUser);
        when(userMapper.mapToDto(savedUser)).thenReturn(userDto);

        //act (when)
        UserDto result = userService.create(userDto, type);

        //assert (then)
        assertThat(result).isNotNull();
        verify(userMapper, times(1)).mapToEntity(userDto);
        verify(userMapper, times(1)).mapToDto(savedUser);
        verify(userRepository, times(1)).save(user);

    }
/*
    @Test
    void test_getOne_whenUsernameExists() {
        //arrange
        String username = "Maria";
        UserDto userDto = aUserDto("Maria11","Maria","Creanga");
        User user = aUser("Maria11","Maria","Creanga");

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(userMapper.mapToDto(user)).thenReturn(userDto);

        //Act
        UserDto result = userService.getOne(username);

        //Assert
        assertEquals(userDto, result);
    }

    @Test
    void test_getOne_whenUsernameDoesNotExist() {
        //arrange
        String username = "Maria";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        //Act
        UserNotFoundException exception = assertThrows(UserNotFoundException.class,
                () -> userService.getOne(username));

        assertEquals(":(", exception.getMessage());
    }
*/
}