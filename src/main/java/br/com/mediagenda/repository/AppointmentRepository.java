package br.com.mediagenda.repository;
import java.time.LocalDateTime;
import br.com.mediagenda.domain.Appointment;
import br.com.mediagenda.domain.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByDoctorIdAndScheduledAtAndStatusNot(Long doctorId, LocalDateTime scheduledAt, AppointmentStatus status);
}
