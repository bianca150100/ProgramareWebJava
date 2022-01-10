package cabinetMedical.repository;
import cabinetMedical.domain.Appointment;
import cabinetMedical.domain.Doctor;
import cabinetMedical.domain.DonationAppointment;
import cabinetMedical.dto.AppointmentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DonationAppointmentRepository extends JpaRepository<DonationAppointment, Long> {
    public default List<DonationAppointment> getAll() {
        return this.findAll();
    }


    @Modifying
    @Query(value = "DELETE FROM Donation_appointments u WHERE u.id = :id", nativeQuery = true)
    void deleteAppoint(Long id);

    @Modifying
    @Query(value = "DELETE FROM Donation_appointments_users u WHERE u.donation_appointments = :id", nativeQuery = true)
    void deleteAppointUsers(Long id);

    @Modifying
    @Query(value = "DELETE FROM Donation_appointments_request_donations u WHERE u.donation_appointments = :id", nativeQuery = true)
    void deleteAppointDonationRequest(Long id);

}
