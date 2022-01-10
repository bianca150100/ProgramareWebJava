package cabinetMedical.controller;

import cabinetMedical.domain.UserType;
import cabinetMedical.dto.UserDto;
import cabinetMedical.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity
                .ok()
                .body(userService.create(userDto, UserType.PACIENT));
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<UserDto> createAdmin(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity
                .ok()
                .body(userService.create(userDto, UserType.ADMIN));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> get(@PathVariable String username) {
        UserDto user = userService.getOne(username);
        if (user != null) {
            return ResponseEntity
                    .ok()
                    .body(user);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity
                .ok()
                .body(userService.getAll());
    }

    @PatchMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {
        int ok = userService.loginUser(username,password);
        if (ok == 1) {
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
            return "Successfully logged!";
        } else {
            response.setStatus(404);
            return "Invalid username/password!";
        }
    }

    @PatchMapping("/logout")
    public String logoutUser(@RequestParam String username, HttpServletResponse response) {
        int ok = userService.logoutUser(username);
        if (ok == 1) {
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
            return "Successfully logged out!";
        } else {
            response.setStatus(404);
            return "Invalid username!";
        }
    }


}
