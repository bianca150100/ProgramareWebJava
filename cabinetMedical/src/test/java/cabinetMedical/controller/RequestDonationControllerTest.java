package cabinetMedical.controller;

import cabinetMedical.dto.DoctorDto;
import cabinetMedical.dto.RequestDonationDto;
import cabinetMedical.service.RequestDonationService;
import cabinetMedical.util.DoctorDtoUtil;
import cabinetMedical.util.RequestDonationDtoUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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

@WebMvcTest(controllers = RequestDonationController.class)
class RequestDonationControllerTest {
    @MockBean
    private RequestDonationService requestDonationService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Testing creating a new donation request")
    void test_createReqDonation_happyFlow() throws Exception {
        //Arrange
        RequestDonationDto dto = RequestDonationDtoUtil.aRequestDonation("firstname1","lastname1");
        int resultCreation = 1;
        when(requestDonationService.create(any())).thenReturn((long) resultCreation);

        //Act
        MvcResult result = mockMvc.perform(post("/donationRequest/create")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andReturn();

    }

    @Test
    @DisplayName("Testing creating a new donation request")
    void test_createReqDonationInvalid() throws Exception {
        //Arrange
        RequestDonationDto dto = RequestDonationDtoUtil.aRequestDonation("firstname1","lastname1");
        int resultCreation = 0;
        when(requestDonationService.create(any())).thenReturn((long) resultCreation);

        //Act
        MvcResult result = mockMvc.perform(post("/donationRequest/create")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404))
                .andReturn();
    }

    @Test
    void getAll() throws Exception {
        List<RequestDonationDto> dtos = new ArrayList<RequestDonationDto>();
        RequestDonationDto dto = RequestDonationDtoUtil.aRequestDonation("firstname1","lastname1");
        dtos.add(dto);
        when(requestDonationService.getAllDonationsRequests()).thenReturn(dtos);

        MvcResult result = mockMvc.perform(get("/donationRequest")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = result.getResponse().getContentAsString();

        assertThat(content.split("\\{").length).isEqualTo(2);

    }
}