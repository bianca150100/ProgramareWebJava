package cabinetMedical.repository;

import cabinetMedical.domain.Doctor;
import cabinetMedical.domain.User;
import cabinetMedical.domain.UserType;
import cabinetMedical.dto.DoctorDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor findByFirstName(String firstName);

    public default List<Doctor> getAll() {
        return this.findAll();
    }

    @Query(value = "SELECT u FROM Doctor u WHERE u.firstName = :firstname and u.lastName = :lastname")
    Doctor findByName(String firstname, String lastname);
}
