package br.com.mediagenda;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import br.com.mediagenda.repository.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(properties = {"spring.datasource.url=jdbc:h2:mem:webhistory", "spring.jpa.hibernate.ddl-auto=create-drop"})
@AutoConfigureMockMvc
class ClinicalWebTest {
    @Autowired MockMvc web;
    @Autowired PatientRepository patients;
    @Autowired DoctorRepository doctors;
    @Autowired AppointmentRepository appointments;

    @Test
    void registrationFormsRender() throws Exception {
        web.perform(get("/patients/new")).andExpect(status().isOk());
        web.perform(get("/doctors/new")).andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Cardiologia")));
        web.perform(get("/appointments/new")).andExpect(status().isOk());
    }

    @Test
    void receptionAndDoctorCanCompleteAVisitAndReadTheSavedRecord() throws Exception {
        web.perform(post("/patients").param("name", "Ana Teste").param("cpf", "00000000000")
                .param("email", "ana@example.org").param("phone", "8100000000")
                .param("birthDate", "2000-05-12")).andExpect(status().is3xxRedirection());
        web.perform(post("/doctors").param("name", "Dra. Lia Teste").param("crm", "TESTE01")
                .param("specialty", "Clínica médica").param("email", "lia@example.org")
                .param("phone", "8100000001")).andExpect(status().is3xxRedirection());
        var patient = patients.findAll().stream().filter(p -> p.getCpf().equals("00000000000")).findFirst().orElseThrow();
        var doctor = doctors.findAll().stream().filter(d -> d.getCrm().equals("TESTE01")).findFirst().orElseThrow();
        assertEquals("2000-05-12", patient.getBirthDate().toString());
        assertEquals("lia@example.org", doctor.getEmail());
        String history = "/patients/" + patient.getId() + "/history";
        web.perform(get(history)).andExpect(content().string(org.hamcrest.Matchers.containsString("ainda não possui")));
        web.perform(post("/appointments").param("patientId", patient.getId().toString())
                .param("doctorId", doctor.getId().toString()).param("scheduledAt", "2025-08-12T09:00"))
                .andExpect(redirectedUrl("/agenda"));
        String visit = "/appointments/" + appointments.findAll().stream().filter(a -> a.getDoctor().getId().equals(doctor.getId())).findFirst().orElseThrow().getId();
        web.perform(post(visit + "/complete")).andExpect(redirectedUrl("/agenda"));
        web.perform(get("/")).andExpect(content().string(org.hamcrest.Matchers.containsString("REALIZADA")));
        web.perform(get(visit + "/record")).andExpect(content().string(org.hamcrest.Matchers.containsString("Salvar prontuário")));
        web.perform(post(visit + "/record").param("diagnosis", "Revisão de rotina")
                .param("prescription", "Acompanhamento").param("notes", "Registro fictício"))
                .andExpect(redirectedUrl(history));
        web.perform(get(history)).andExpect(content().string(org.hamcrest.Matchers.containsString("Registro fictício")));
        web.perform(get(visit + "/record")).andExpect(redirectedUrl(history));
    }

    @Test
    void cancellationReleasesTheSlotAndConfirmationPersists() throws Exception {
        web.perform(post("/patients").param("name", "Maria Teste").param("cpf", "22222222222")
                .param("phone", "8100000002").param("email", "maria@example.org").param("birthDate", "2000-01-01"));
        web.perform(post("/doctors").param("name", "Dr. Teste").param("crm", "TESTE02")
                .param("phone", "8100000003").param("email", "medico@example.org").param("specialty", "Cardiologia"));
        var patient = patients.findAll().stream().filter(p -> p.getCpf().equals("22222222222")).findFirst().orElseThrow();
        var doctor = doctors.findAll().stream().filter(d -> d.getCrm().equals("TESTE02")).findFirst().orElseThrow();
        java.time.LocalDateTime time = java.time.LocalDateTime.of(2026, 1, 20, 14, 0);
        web.perform(post("/appointments").param("patientId", patient.getId().toString())
                .param("doctorId", doctor.getId().toString()).param("scheduledAt", time.toString()));
        var appointment = appointments.findAll().stream().filter(a -> a.getDoctor().getId().equals(doctor.getId())).findFirst().orElseThrow();
        String url = "/appointments/" + appointment.getId();
        web.perform(post("/appointments").param("patientId", patient.getId().toString())
                .param("doctorId", doctor.getId().toString()).param("scheduledAt", time.toString()))
                .andExpect(flash().attribute("error", "O médico já possui uma consulta nesse horário."));
        web.perform(post(url + "/confirm")).andExpect(redirectedUrl("/agenda"));
        assertEquals(br.com.mediagenda.domain.AppointmentStatus.CONFIRMADA,
                appointments.findById(appointment.getId()).orElseThrow().getStatus());
        web.perform(get(url)).andExpect(status().isOk());
        web.perform(post(url + "/cancel").param("reason", " "))
                .andExpect(flash().attribute("error", "Informe o motivo do cancelamento."));
        web.perform(post(url + "/cancel").param("reason", "Imprevisto pessoal")).andExpect(redirectedUrl("/agenda"));
        var cancelled = appointments.findById(appointment.getId()).orElseThrow();
        assertEquals(br.com.mediagenda.domain.AppointmentStatus.CANCELADA, cancelled.getStatus());
        assertEquals("Imprevisto pessoal", cancelled.getCancellationReason());
        web.perform(post(url + "/confirm"))
                .andExpect(flash().attribute("error", "Somente consultas agendadas podem ser confirmadas."));
        web.perform(post("/appointments").param("patientId", patient.getId().toString())
                .param("doctorId", doctor.getId().toString()).param("scheduledAt", time.toString()))
                .andExpect(flash().attribute("success", "Consulta agendada com sucesso."));
        assertEquals(2, appointments.findAll().stream().filter(a -> a.getDoctor().getId().equals(doctor.getId())).count());
    }
}
