package cabinetMedical.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinTable(name = "doctors_doctor_details",
            joinColumns = {
                    @JoinColumn(name = "doctors", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "doctor_details", referencedColumnName = "id")
            }
    )
    private DoctorDetails doctorDetails;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinTable(name = "doctors_doctor_available_time",
            joinColumns = {
                    @JoinColumn(name = "doctors", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "doctor_available_time", referencedColumnName = "id")
            }
    )
    private DoctorAvailableTime doctorAvailableTime;

}
