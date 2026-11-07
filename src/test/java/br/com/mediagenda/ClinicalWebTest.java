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
    void receptionAndDoctorCanCompleteAVisitAndReadTheSavedRecord() throws Exception {
        web.perform(post("/patients").param("name", "Ana Teste").param("cpf", "00000000000")
                .param("email", "ana@example.org").param("phone", "8100000000")
                .param("birthDate", "2000-05-12")).andExpect(status().is3xxRedirection());
        web.perform(post("/doctors").param("name", "Dra. Lia Teste").param("crm", "TESTE01")
                .param("specialty", "Clínica médica").param("email", "lia@example.org")
                .param("phone", "8100000001")).andExpect(status().is3xxRedirection());
        var patient = patients.findAll().get(0);
        var doctor = doctors.findAll().get(0);
        assertEquals("2000-05-12", patient.getBirthDate().toString());
        assertEquals("lia@example.org", doctor.getEmail());
        String history = "/patients/" + patient.getId() + "/history";
        web.perform(get(history)).andExpect(content().string(org.hamcrest.Matchers.containsString("ainda não possui")));
        web.perform(post("/appointments").param("patientId", patient.getId().toString())
                .param("doctorId", doctor.getId().toString()).param("scheduledAt", "2025-08-12T09:00"))
                .andExpect(redirectedUrl("/"));
        String visit = "/appointments/" + appointments.findAll().get(0).getId();
        web.perform(post(visit + "/complete")).andExpect(redirectedUrl("/"));
        web.perform(get("/")).andExpect(content().string(org.hamcrest.Matchers.containsString("REALIZADA")));
        web.perform(get(visit + "/record")).andExpect(content().string(org.hamcrest.Matchers.containsString("Salvar prontuário")));
        web.perform(post(visit + "/record").param("diagnosis", "Revisão de rotina")
                .param("prescription", "Acompanhamento").param("notes", "Registro fictício"))
                .andExpect(redirectedUrl(history));
        web.perform(get(history)).andExpect(content().string(org.hamcrest.Matchers.containsString("Registro fictício")));
        web.perform(get(visit + "/record")).andExpect(redirectedUrl(history));
    }
}
