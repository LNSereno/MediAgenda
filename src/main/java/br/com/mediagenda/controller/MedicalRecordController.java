package br.com.mediagenda.controller;

import br.com.mediagenda.domain.*;
import br.com.mediagenda.repository.*;
import br.com.mediagenda.service.MedicalRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MedicalRecordController {
    private final PatientRepository patients;
    private final AppointmentRepository appointments;
    private final MedicalRecordRepository records;
    private final MedicalRecordService service;

    public MedicalRecordController(PatientRepository patients, AppointmentRepository appointments,
                                   MedicalRecordRepository records, MedicalRecordService service) {
        this.patients = patients;
        this.appointments = appointments;
        this.records = records;
        this.service = service;
    }

    @GetMapping("/patients/{id}/history")
    public String history(@PathVariable Long id, Model model) {
        model.addAttribute("patient", patients.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado.")));
        model.addAttribute("records", records.findByAppointmentPatientIdOrderByAppointmentScheduledAtDescIdDesc(id));
        return "history";
    }

    @GetMapping("/appointments/{id}/record")
    public String form(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        Appointment appointment = appointments.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Consulta não encontrada."));
        if (records.existsByAppointmentId(id)) return "redirect:/patients/" + appointment.getPatient().getId() + "/history";
        if (appointment.getStatus() != AppointmentStatus.REALIZADA) {
            redirect.addFlashAttribute("error", "Conclua o atendimento antes de registrar o prontuário.");
            return "redirect:/";
        }
        model.addAttribute("appointment", appointment);
        return "record-form";
    }

    @PostMapping("/appointments/{id}/record")
    public String save(@PathVariable Long id, @RequestParam String diagnosis, @RequestParam String prescription,
                       @RequestParam(defaultValue = "") String notes, RedirectAttributes redirect) {
        try {
            MedicalRecord record = service.register(id, diagnosis, prescription, notes);
            redirect.addFlashAttribute("success", "Prontuário registrado.");
            return "redirect:/patients/" + record.getAppointment().getPatient().getId() + "/history";
        } catch (IllegalArgumentException | IllegalStateException ex) {
            redirect.addFlashAttribute("error", ex.getMessage());
            redirect.addFlashAttribute("diagnosis", diagnosis);
            redirect.addFlashAttribute("prescription", prescription);
            redirect.addFlashAttribute("notes", notes);
            return "redirect:/appointments/" + id + "/record";
        }
    }
}
