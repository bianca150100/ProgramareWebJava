package cabinetMedical.controller;

import cabinetMedical.dto.DoctorDto;
import cabinetMedical.dto.UserDto;
import cabinetMedical.service.DoctorService;
import cabinetMedical.service.UserService;
import cabinetMedical.util.DoctorDtoUtil;
import cabinetMedical.util.UserDtoUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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

@WebMvcTest(controllers = DoctorController.class)
class DoctorControllerTest {
    @MockBean
    private DoctorService doctorService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Testing creating a new doctor")
    void test_createDoctor_happyFlow() throws Exception {
        //Arrange
        DoctorDto dto = DoctorDtoUtil.aDoctorDto("firstname1","lastname1");
        int resultCreation = 1;
        when(doctorService.create(any())).thenReturn((long) resultCreation);

        //Act
        MvcResult result = mockMvc.perform(post("/doctor/create")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andReturn();

    }

    @Test
    @DisplayName("Testing registering a new admin")
    void test_createDoctorInvalid() throws Exception {
        //Arrange
        DoctorDto dto = DoctorDtoUtil.aDoctorDto("firstname1","lastname1");
        int resultCreation = 0;
        when(doctorService.create(any())).thenReturn((long) resultCreation);

        //Act
        MvcResult result = mockMvc.perform(post("/doctor/create")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404))
                .andReturn();
    }

    @Test
    @DisplayName("Testing updating a doctor")
    void test_updateDoctor_happyFlow() throws Exception {
        //Arrange
        DoctorDto dto = DoctorDtoUtil.aDoctorDto("firstname1","lastname1");
        int resultCreation = 1;
        when(doctorService.updateDoctor(any())).thenReturn((long) resultCreation);

        //Act
        MvcResult result = mockMvc.perform(patch("/doctor/update")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andReturn();

    }

    @Test
    @DisplayName("Testing updating a doctor")
    void test_UpdateDoctorInvalid() throws Exception {
        //Arrange
        DoctorDto dto = DoctorDtoUtil.aDoctorDto("firstname1","lastname1");
        int resultCreation = 0;
        when(doctorService.updateDoctor(any())).thenReturn((long) resultCreation);

        //Act
        MvcResult result = mockMvc.perform(patch("/doctor/update")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404))
                .andReturn();
    }


    @Test
    void test_getDoctor() throws Exception {
        String firstname = "Ioana";
        String lastname = "Graa";
        DoctorDto dto = DoctorDtoUtil.aDoctorDto(firstname,lastname);
        when(doctorService.getByName(firstname,lastname)).thenReturn(dto);

        MvcResult result = mockMvc.perform(get("/doctor/details?firstname=" + firstname + "&lastname=" + lastname))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("firstName", is(dto.getFirstName())))
                .andReturn();
        System.out.println(result) ;
    }

    @Test
    void test_getDoctor_Exception() throws Exception {
        String firstname = "Ioana";
        String lastname = "Graa";
        DoctorDto dto = DoctorDtoUtil.aDoctorDto(firstname,lastname);
        when(doctorService.getByName(firstname,lastname)).thenReturn(null);

        mockMvc.perform(get("/doctor/details?firstname=" + firstname + "&lastname=" + lastname))
                .andExpect(status().isNoContent());


    }

}