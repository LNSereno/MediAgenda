package br.com.mediagenda.service;

import java.time.LocalDateTime;
import br.com.mediagenda.domain.Appointment;
import br.com.mediagenda.domain.AppointmentStatus;
import br.com.mediagenda.domain.Doctor;
import br.com.mediagenda.domain.Patient;
import br.com.mediagenda.repository.AppointmentRepository;
import br.com.mediagenda.repository.DoctorRepository;
import br.com.mediagenda.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    @Transactional
    public Appointment schedule(Long patientId, Long doctorId, LocalDateTime scheduledAt) {
        if (appointmentRepository.existsByDoctorIdAndScheduledAtAndStatusNot(doctorId, scheduledAt, AppointmentStatus.CANCELADA)) {
            throw new IllegalStateException("O médico já possui uma consulta nesse horário.");
        }
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado."));
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new IllegalArgumentException("Médico não encontrado."));
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setScheduledAt(scheduledAt);
        return appointmentRepository.save(appointment);
    }

    @Transactional
    public void confirm(Long id) {
        Appointment appointment = getById(id);
        if (appointment.getStatus() != AppointmentStatus.AGENDADA) throw new IllegalStateException("Somente consultas agendadas podem ser confirmadas.");
        appointment.setStatus(AppointmentStatus.CONFIRMADA);
    }

    @Transactional
    public void cancel(Long id, String reason) {
        Appointment appointment = getById(id);
        if (!appointment.isOpen()) throw new IllegalStateException("Somente consultas em aberto podem ser canceladas.");
        if (reason == null || reason.isBlank()) throw new IllegalArgumentException("Informe o motivo do cancelamento.");
        if (reason.strip().length() > 255) throw new IllegalArgumentException("O motivo deve ter até 255 caracteres.");
        appointment.setStatus(AppointmentStatus.CANCELADA);
        appointment.setCancellationReason(reason.trim());
    }

    @Transactional
    public void complete(Long id) {
        getById(id).complete();
    }

    private Appointment getById(Long id) { return appointmentRepository.lockById(id).orElseThrow(() -> new IllegalArgumentException("Consulta não encontrada.")); }
}
