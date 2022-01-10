package cabinetMedical.controller;

import cabinetMedical.dto.RequestDonationDto;
import cabinetMedical.service.RequestDonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
public class RequestDonationController {

    @Autowired
    private RequestDonationService donationRequestService;

    @PostMapping("/donationRequest/create")
    public String createDonationRequest(@Valid @RequestBody RequestDonationDto donationRequestDto, HttpServletResponse response) {
        Long result = donationRequestService.create(donationRequestDto);
        if (result >= 1) {
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
            return String.format("DonationRequest %s was created!", result);
        } else {
            response.setStatus(404);
            return "DonationRequest cannot be created - you are not logged with an admin account!";
        }
    }

    @GetMapping("/donationRequest")
    public List<RequestDonationDto> getAll() {
        return donationRequestService.getAllDonationsRequests();
    }

}
