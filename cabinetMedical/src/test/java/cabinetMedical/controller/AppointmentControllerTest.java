package cabinetMedical.controller;

import cabinetMedical.dto.AppointmentDto;
import cabinetMedical.dto.RequestDonationDto;
import cabinetMedical.dto.UserDto;
import cabinetMedical.service.AppointmentService;
import cabinetMedical.service.UserService;
import cabinetMedical.util.AppointmentDtoUtil;
import cabinetMedical.util.RequestDonationDtoUtil;
import cabinetMedical.util.UserDtoUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AppointmentController.class)
class AppointmentControllerTest {
    
    @MockBean
    private AppointmentService appointmentService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    @DisplayName("Testing creating a new appointment")
    void createAppointment_happyFlow() throws Exception {
        AppointmentDto dto = AppointmentDtoUtil.aAppointmentDto("2022-10-11","13");
        long resultCreation = 1;
        when(appointmentService.create(any())).thenReturn(resultCreation);

        //Act
        MvcResult result = mockMvc.perform(post("/appointment/create")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andReturn();

    }

    @Test
    @DisplayName("Testing creating a new appointment - invalid username")
    void createAppointment_InvalidUsername() throws Exception {
        AppointmentDto dto = AppointmentDtoUtil.aAppointmentDto("2022-10-11","13");
        long resultCreation = 0;
        when(appointmentService.create(any())).thenReturn(resultCreation);

        //Act
        MvcResult result = mockMvc.perform(post("/appointment/create")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404))
                .andReturn();

    }

    @Test
    @DisplayName("Testing creating a new appointment - invalid doctor")
    void createAppointment_InvalidDoctor() throws Exception {
        AppointmentDto dto = AppointmentDtoUtil.aAppointmentDto("2022-10-11","13");
        long resultCreation = -1;
        when(appointmentService.create(any())).thenReturn(resultCreation);

        //Act
        MvcResult result = mockMvc.perform(post("/appointment/create")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404))
                .andReturn();

    }

    @Test
    @DisplayName("Testing creating a new appointment - invalid data")
    void createAppointment_InvalidData() throws Exception {
        AppointmentDto dto = AppointmentDtoUtil.aAppointmentDto("2022-10-11","13");
        long resultCreation = -5;
        when(appointmentService.create(any())).thenReturn(resultCreation);

        //Act
        MvcResult result = mockMvc.perform(post("/appointment/create")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404))
                .andReturn();

    }


    @Test
    void getAll() throws Exception {
        List<AppointmentDto> dtos = new ArrayList<AppointmentDto>();
        AppointmentDto dto = AppointmentDtoUtil.aAppointmentDto("2022-10-11","13");
        dtos.add(dto);
        when(appointmentService.getAllAppointments()).thenReturn(dtos);

        MvcResult result = mockMvc.perform(get("/appointments")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = result.getResponse().getContentAsString();

        assertThat(content.split("\\{").length).isEqualTo(2);

    }


    @Test
    @DisplayName("Testing deleting an appointment")
    void cleanupAppointments() throws Exception {
        AppointmentDto dto = AppointmentDtoUtil.aAppointmentDto("2022-10-11","13");
        int resultCreation = 1;
        int id = 1;
        when(appointmentService.removeAppoint(id)).thenReturn((int) resultCreation);

        //Act
        MvcResult result = mockMvc.perform(delete("/deleteAppointment/?id="+ id)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isAccepted())
                        .andReturn();
    }

    @Test
    @DisplayName("Testing deleting an appointment")
    void cleanupAppointmentsExceptionUser() throws Exception {
        AppointmentDto dto = AppointmentDtoUtil.aAppointmentDto("2022-10-11","13");
        int resultCreation = 0;
        int id = 1;
        when(appointmentService.removeAppoint(id)).thenReturn((int) resultCreation);

        //Act
        MvcResult result = mockMvc.perform(delete("/deleteAppointment/?id="+ id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404))
                .andReturn();
    }

    @Test
    @DisplayName("Testing deleting an appointment")
    void cleanupAppointmentsExceptionInvalidId() throws Exception {
        AppointmentDto dto = AppointmentDtoUtil.aAppointmentDto("2022-10-11","13");
        int resultCreation = -1;
        int id = 1;
        when(appointmentService.removeAppoint(id)).thenReturn((int) resultCreation);

        //Act
        MvcResult result = mockMvc.perform(delete("/deleteAppointment/?id="+ id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404))
                .andReturn();
    }
}