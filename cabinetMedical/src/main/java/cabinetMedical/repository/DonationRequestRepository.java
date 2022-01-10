package cabinetMedical.repository;
import cabinetMedical.domain.RequestDonation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRequestRepository extends JpaRepository<RequestDonation, Long> {
    public default List<RequestDonation> getAll() {
        return this.findAll();
    }
}
