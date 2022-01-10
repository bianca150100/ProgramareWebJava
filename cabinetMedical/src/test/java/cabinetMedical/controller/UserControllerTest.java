package cabinetMedical.controller;
import cabinetMedical.domain.UserType;
import cabinetMedical.dto.UserDto;
import cabinetMedical.service.UserService;
import cabinetMedical.util.UserDtoUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {
    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Testing registering a new user")
    void test_createUser_happyFlow() throws Exception {
        //Arrange
        UserDto dto = UserDtoUtil.aUserDto("username1","firstname1","lastname1");
        when(userService.create(any(), any())).thenReturn(dto);

        //Act
        MvcResult result = mockMvc.perform(post("/register")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        //Assert
        assertThat(result.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(dto));
    }

    @Test
    @DisplayName("Testing registering a new admin")
    void test_createAdmin_happyFlow() throws Exception {
        //Arrange
        UserDto dto = UserDtoUtil.aUserDto("username1","firstname1","lastname1");
        when(userService.create(any(), any())).thenReturn(dto);

        //Act
        MvcResult result = mockMvc.perform(post("/registerAdmin")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        //Assert
        assertThat(result.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(dto));
    }

    @Test
    void test_getUser() throws Exception {
        String username = "Bianca12";
        UserDto dto = UserDtoUtil.aUserDto(username,"Bianca","Buzoi");
        when(userService.getOne(username)).thenReturn(dto);

        MvcResult result = mockMvc.perform(get("/" + username))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("username", is(dto.getUsername())))
                .andReturn();
        System.out.println(result) ;
    }

    @Test
    void test_getOneUser_Exception() throws Exception {
        String username = "Bianca12";
        when(userService.getOne(username)).thenReturn(null);

        mockMvc.perform(get("/" + username))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Testing login")
    void test_login_happyFlow() throws Exception {
        //Arrange
        String username = "Bianca12";
        String password = "password";
        when(userService.loginUser(username, password)).thenReturn(1);

        //Act
        MvcResult result = mockMvc.perform(patch("/login?username=" + username + "&password=" + password)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andReturn();

        //Assert
        assertThat(result.getResponse().getContentAsString()).isEqualTo("Successfully logged!");
    }

    @Test
    @DisplayName("Testing login")
    void test_login_Exception() throws Exception {
        //Arrange
        String username = "Bianca12";
        String password = "password";
        when(userService.loginUser(username, password)).thenReturn(0);

        //Act
        MvcResult result = mockMvc.perform(patch("/login?username=" + username + "&password=" + password)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404))
                .andReturn();

        //Assert
        assertThat(result.getResponse().getContentAsString()).isEqualTo("Invalid username/password!");
    }

    @Test
    @DisplayName("Testing logout")
    void test_logout_happyFlow() throws Exception {
        //Arrange
        String username = "Bianca12";
        when(userService.logoutUser(username)).thenReturn(1);

        //Act
        MvcResult result = mockMvc.perform(patch("/logout?username=" + username)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andReturn();

        //Assert
        assertThat(result.getResponse().getContentAsString()).isEqualTo("Successfully logged out!");
    }

    @Test
    @DisplayName("Testing login")
    void test_logout_Exception() throws Exception {
        //Arrange
        String username = "Bianca12";
        when(userService.logoutUser(username)).thenReturn(0);

        //Act
        MvcResult result = mockMvc.perform(patch("/logout?username=" + username)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404))
                .andReturn();

        //Assert
        assertThat(result.getResponse().getContentAsString()).isEqualTo("Invalid username!");
    }

}
