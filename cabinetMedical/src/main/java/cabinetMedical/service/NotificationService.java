package cabinetMedical.service;

import cabinetMedical.domain.Appointment;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class NotificationService {

    public boolean sendNotificationForAppointment(Appointment app) {
        if(app == null) {
            throw new RuntimeException("Cannot send notification. Appointment is null.");
        }

        System.out.println("New appointment created:  " + app.getData().toString() + " - " + app.getHour() + " .Doctor"
                + app.getDoctor().getFirstName() + " " + app.getDoctor().getLastName());

        return true;
    }
}
