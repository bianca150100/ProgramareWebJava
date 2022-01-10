package cabinetMedical.controller;

import cabinetMedical.dto.AppointmentDto;
import cabinetMedical.dto.DonationAppointmentDto;
import cabinetMedical.service.AppointmentService;
import cabinetMedical.service.DonationAppointmentService;
import cabinetMedical.util.AppointmentDtoUtil;
import cabinetMedical.util.DonationAppointmentDtoUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DonationAppointmentController.class)
class DonationAppointmentControllerTest {

    @MockBean
    private DonationAppointmentService appointmentService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @DisplayName("Testing creating a new donation appointment")
    void createAppointment_happyFlow() throws Exception {
        DonationAppointmentDto dto = DonationAppointmentDtoUtil.aDonationAppointmentDto("Ionel", "Vasile");
        long resultCreation = 1;
        when(appointmentService.create(any())).thenReturn(resultCreation);

        //Act
        MvcResult result = mockMvc.perform(post("/donationAppointment/create")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andReturn();

    }

    @Test
    @DisplayName("Testing creating a new donation appointment - invalid username")
    void createAppointment_InvalidUsername() throws Exception {
        DonationAppointmentDto dto = DonationAppointmentDtoUtil.aDonationAppointmentDto("Ionel", "Vasile");
        long resultCreation = 0;
        when(appointmentService.create(any())).thenReturn(resultCreation);

        //Act
        MvcResult result = mockMvc.perform(post("/donationAppointment/create")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404))
                .andReturn();

    }

    @Test
    @DisplayName("Testing deleting a donation appointment")
    void cleanupAppointments() throws Exception {
        DonationAppointmentDto dto = DonationAppointmentDtoUtil.aDonationAppointmentDto("Ionel", "Vasile");
        int resultCreation = 1;
        int id = 1;
        when(appointmentService.removeAppoint(id)).thenReturn((int) resultCreation);

        //Act
        MvcResult result = mockMvc.perform(delete("/deleteDonationAppointment/?id="+ id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andReturn();
    }

    @Test
    @DisplayName("Testing deleting a donation appointment")
    void cleanupAppointmentsExceptionUser() throws Exception {
        DonationAppointmentDto dto = DonationAppointmentDtoUtil.aDonationAppointmentDto("Ionel", "Vasile");
        int resultCreation = 0;
        int id = 1;
        when(appointmentService.removeAppoint(id)).thenReturn((int) resultCreation);

        //Act
        MvcResult result = mockMvc.perform(delete("/deleteDonationAppointment/?id="+ id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404))
                .andReturn();
    }

}