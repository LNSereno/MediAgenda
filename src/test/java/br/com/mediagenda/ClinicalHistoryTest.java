package br.com.mediagenda;

import java.time.LocalDateTime;
import br.com.mediagenda.domain.*;
import br.com.mediagenda.repository.*;
import br.com.mediagenda.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {"spring.datasource.url=jdbc:h2:mem:clinicaltest", "spring.jpa.hibernate.ddl-auto=create-drop"})
class ClinicalHistoryTest {
    @Autowired PatientRepository patients;
    @Autowired DoctorRepository doctors;
    @Autowired AppointmentService agenda;
    @Autowired MedicalRecordService charts;
    @Autowired MedicalRecordRepository records;

    private Patient patient(String cpf) {
        Patient patient = new Patient();
        patient.setName("Paciente fictício");
        patient.setPhone("8100000000");
        patient.setCpf(cpf);
        return patients.save(patient);
    }

    private Doctor doctor(String crm) {
        Doctor doctor = new Doctor();
        doctor.setCrm(crm);
        doctor.setName("Médica fictícia");
        doctor.setSpecialty("Clínica médica");
        return doctors.save(doctor);
    }

    @Test
    void patientHistoryContainsOnlyTheirVisitsWithNewestFirst() {
        Patient ana = patient("001");
        Patient other = patient("002");
        Doctor doctor = doctor("PE01");
        LocalDateTime time = LocalDateTime.of(2025, 8, 10, 9, 0);
        for (int day : new int[] {2, 1, 3, 4}) {
            Patient owner = day == 4 ? other : ana;
            var visit = agenda.schedule(owner.getId(), doctor.getId(), time.plusDays(day));
            agenda.complete(visit.getId());
            charts.register(visit.getId(), "Registro " + day, "Acompanhamento", "");
        }
        var history = records.findByAppointmentPatientIdOrderByAppointmentScheduledAtDescIdDesc(ana.getId());
        assertEquals(java.util.List.of("Registro 3", "Registro 2", "Registro 1"),
                history.stream().map(MedicalRecord::getDiagnosis).toList());
    }

    @Test
    void requiresCompletedVisitAndAcceptsOnlyOneRecord() {
        var visit = agenda.schedule(patient("003").getId(), doctor("PE02").getId(), LocalDateTime.now().minusHours(1));
        assertThrows(IllegalStateException.class, () -> charts.register(visit.getId(), "D", "P", ""));
        agenda.confirm(visit.getId());
        agenda.complete(visit.getId());
        assertThrows(IllegalArgumentException.class, () -> charts.register(visit.getId(), " ", "P", ""));
        charts.register(visit.getId(), "D", "P", "Retorno");
        assertThrows(IllegalStateException.class, () -> charts.register(visit.getId(), "D", "P", ""));
        assertThrows(IllegalStateException.class, () -> agenda.cancel(visit.getId(), "Teste"));
    }

    @Test
    void refusesFutureOrCancelledVisits() {
        var visit = agenda.schedule(patient("004").getId(), doctor("PE03").getId(), LocalDateTime.now().plusDays(2));
        assertThrows(IllegalStateException.class, () -> agenda.complete(visit.getId()));
        agenda.cancel(visit.getId(), "Imprevisto");
        assertThrows(IllegalStateException.class, () -> agenda.complete(visit.getId()));
    }
}
