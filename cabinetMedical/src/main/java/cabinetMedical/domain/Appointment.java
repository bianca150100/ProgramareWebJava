package cabinetMedical.domain;
import cabinetMedical.dto.DoctorDto;
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
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data")
    private LocalDate Data;

    @Column(name = "hour")
    private String Hour;

    @Column(name = "notes")
    private String notes;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinTable(name = "appointments_doctors",
            joinColumns = {
                    @JoinColumn(name = "appointments", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "doctors", referencedColumnName = "id")
            }
    )
    private Doctor doctor;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinTable(name = "appointments_users",
            joinColumns = {
                    @JoinColumn(name = "appointments", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "users", referencedColumnName = "id")
            }
    )
    private User pacient;
}
