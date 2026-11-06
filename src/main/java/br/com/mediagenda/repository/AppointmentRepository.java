package br.com.mediagenda.repository;
import java.time.LocalDateTime;
import br.com.mediagenda.domain.Appointment;
import br.com.mediagenda.domain.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @org.springframework.data.jpa.repository.Lock(jakarta.persistence.LockModeType.PESSIMISTIC_WRITE)
    @org.springframework.data.jpa.repository.Query("select a from Appointment a where a.id = :id")
    java.util.Optional<Appointment> lockById(@org.springframework.data.repository.query.Param("id") Long id);

    boolean existsByDoctorIdAndScheduledAtAndStatusNot(Long doctorId, LocalDateTime scheduledAt, AppointmentStatus status);
}
