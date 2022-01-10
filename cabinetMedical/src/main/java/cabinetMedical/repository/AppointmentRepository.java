package cabinetMedical.repository;

import cabinetMedical.domain.Appointment;
import cabinetMedical.domain.Doctor;
import cabinetMedical.dto.AppointmentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    public default List<Appointment> getAll() {
        return this.findAll();
    }

    /*
    @Query(value = "SELECT DISTINCT new cabinetMedical.domain.Appointment (a.Data, a.Hour, a.Notes, u.firstName, u.lastName,  from Appointment a JOIN appointments_users au JOIN users u WHERE u.firstName=:firstname and u.lastName=:lastname ")
    List<AppointmentDto> findByUser(String firstname, String lastname);

    @Query(value = "select a from Appointment a JOIN appointments_doctors au JOIN Doctor u WHERE u.firstName=:firstname and u.lastName=:lastname ")
    List<AppointmentDto> findByDoctor(String firstname, String lastname);*/

    @Modifying
    @Query(value = "DELETE FROM Appointments u WHERE u.id = :id", nativeQuery = true)
    void deleteAppoint(Long id);

    @Modifying
    @Query(value = "DELETE FROM Appointments_users u WHERE u.appointments = :id", nativeQuery = true)
    void deleteAppointUsers(Long id);

    @Modifying
    @Query(value = "DELETE FROM Appointments_doctors u WHERE u.appointments = :id", nativeQuery = true)
    void deleteAppointDoctors(Long id);

}