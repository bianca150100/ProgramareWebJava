package cabinetMedical.domain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "donationAppointments")
public class DonationAppointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data")
    private LocalDate Data;

    @Column(name = "hour")
    private String Hour;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinTable(name = "donationAppointments_requestDonations",
            joinColumns = {
                    @JoinColumn(name = "donationAppointments", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "requestDonations", referencedColumnName = "id")
            }
    )
    private RequestDonation requestDonation;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinTable(name = "donationAppointments_users",
            joinColumns = {
                    @JoinColumn(name = "donationAppointments", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "users", referencedColumnName = "id")
            }
    )
    private User user;
}
