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
@Table(name = "doctor_available_time")
public class DoctorAvailableTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sunday_program")
    private String sunday;

    @Column(name = "monday_program")
    private String monday;

    @Column(name = "tuesday_program")
    private String tuesday;

    @Column(name = "wednesday_program")
    private String wednesday;

    @Column(name = "thursday_program")
    private String thursday;

    @Column(name = "friday_program")
    private String friday;

    @Column(name = "saturday_program")
    private String saturday;
}
