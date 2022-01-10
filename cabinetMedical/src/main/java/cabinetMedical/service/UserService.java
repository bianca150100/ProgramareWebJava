package cabinetMedical.service;

import cabinetMedical.domain.User;
import cabinetMedical.domain.UserType;
import cabinetMedical.dto.UserDto;
import cabinetMedical.mapper.UserMapper;
import cabinetMedical.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserDto create(UserDto userDto, UserType type) {
        User user = userMapper.mapToEntity(userDto);
        user.setUserType(type);
        user.setLoggedStatus(0);
        User savedUser = userRepository.save(user);

        return userMapper.mapToDto(savedUser);
    }

    public UserDto getOne(String username) {
        UserDto user= userMapper.mapToDto(userRepository.findByUsername(username));
        return user;

    }

    public List<UserDto> getAll() {

        List<User> users = userRepository.filter(UserType.PACIENT);

        return users.stream().map(u -> userMapper.mapToDto(u)).collect(Collectors.toList());
    }

    @Transactional
    public int loginUser(String username, String password) {
        userRepository.logoutAllUsers();
        List<User> ok = userRepository.checkExistingUser(username, password);
        if (ok.size() >= 1) {
            userRepository.loginUser(username, password);
            return 1;
        }
        return -1;
    }

    @Transactional
    public int logoutUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            userRepository.logoutUser(username);
            return 1;
        }
        return -1;
    }

    public int adminLogged() {
        List<User> users = userRepository.getAll();
        int ok = 0;
        for (User u:users) {
            if (u.getUserType().equals(UserType.ADMIN) && u.getLoggedStatus() == 1) {
                return 1;
            }
        }

        return 0;
    }

}

