package br.com.mediagenda.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;
import br.com.mediagenda.domain.Appointment;
import br.com.mediagenda.domain.AppointmentStatus;
import br.com.mediagenda.domain.Doctor;
import br.com.mediagenda.domain.Patient;
import br.com.mediagenda.repository.AppointmentRepository;
import br.com.mediagenda.repository.DoctorRepository;
import br.com.mediagenda.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {
    @Mock private AppointmentRepository appointmentRepository;
    @Mock private PatientRepository patientRepository;
    @Mock private DoctorRepository doctorRepository;
    private AppointmentService service;

    @BeforeEach void setUp() { service = new AppointmentService(appointmentRepository, patientRepository, doctorRepository); }

    @Test void confirmsAnScheduledAppointment() {
        Appointment appointment = new Appointment();
        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment));
        service.confirm(1L);
        assertEquals(AppointmentStatus.CONFIRMADA, appointment.getStatus());
    }

    @Test void cancelsAppointmentAndKeepsReason() {
        Appointment appointment = new Appointment();
        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment));
        service.cancel(1L, "Imprevisto pessoal");
        assertEquals(AppointmentStatus.CANCELADA, appointment.getStatus());
        assertEquals("Imprevisto pessoal", appointment.getCancellationReason());
    }

    @Test void refusesSchedulingWhenDoctorHasBusySlot() {
        when(appointmentRepository.existsByDoctorIdAndScheduledAtAndStatusNot(any(), any(), any())).thenReturn(true);
        assertThrows(IllegalStateException.class, () -> service.schedule(1L, 1L, LocalDateTime.now()));
    }
}
