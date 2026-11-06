package br.com.mediagenda.service;

import br.com.mediagenda.domain.Appointment;
import br.com.mediagenda.domain.MedicalRecord;
import br.com.mediagenda.repository.AppointmentRepository;
import br.com.mediagenda.repository.MedicalRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MedicalRecordService {
    private final AppointmentRepository appointments;
    private final MedicalRecordRepository records;

    public MedicalRecordService(AppointmentRepository appointments, MedicalRecordRepository records) {
        this.appointments = appointments;
        this.records = records;
    }

    @Transactional
    public MedicalRecord register(Long appointmentId, String diagnosis, String prescription, String notes) {
        Appointment appointment = appointments.lockById(appointmentId)
                .orElseThrow(() -> new IllegalArgumentException("Consulta não encontrada."));
        if (records.existsByAppointmentId(appointmentId)) {
            throw new IllegalStateException("Esta consulta já possui prontuário.");
        }
        return records.save(new MedicalRecord(appointment, diagnosis, prescription, notes));
    }
}
