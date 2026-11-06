package br.com.mediagenda.repository;

import br.com.mediagenda.domain.MedicalRecord;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    boolean existsByAppointmentId(Long appointmentId);
    List<MedicalRecord> findByAppointmentPatientIdOrderByAppointmentScheduledAtDescIdDesc(Long patientId);
}
