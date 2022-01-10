package cabinetMedical.repository;
import cabinetMedical.domain.Doctor;
import cabinetMedical.domain.RequestDonation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestDonationRepository extends JpaRepository<RequestDonation, Long> {
    public default List<RequestDonation> getAll() {
        return this.findAll();
    }

    @Query(value = "SELECT u FROM RequestDonation u WHERE u.firstName = :firstname and u.lastName = :lastname")
    RequestDonation findByName(String firstname, String lastname);
}
