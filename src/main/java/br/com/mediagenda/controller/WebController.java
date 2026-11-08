package br.com.mediagenda.controller;

import java.time.LocalDateTime;
import br.com.mediagenda.domain.Doctor;
import br.com.mediagenda.domain.Patient;
import br.com.mediagenda.repository.AppointmentRepository;
import br.com.mediagenda.repository.DoctorRepository;
import br.com.mediagenda.repository.PatientRepository;
import br.com.mediagenda.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WebController {
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final AppointmentService appointmentService;

    public WebController(PatientRepository patientRepository, DoctorRepository doctorRepository, AppointmentRepository appointmentRepository, AppointmentService appointmentService) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
        this.appointmentService = appointmentService;
    }

    @org.springframework.web.bind.annotation.InitBinder
    public void protectIdentifiers(org.springframework.web.bind.WebDataBinder binder) {
        binder.setDisallowedFields("id");
    }

    @GetMapping({"/", "/agenda"})
    public String home(Model model) {
        model.addAttribute("appointments", appointmentRepository.findAll());
        model.addAttribute("patients", patientRepository.findAll());
        model.addAttribute("doctors", doctorRepository.findAll());
        return "index";
    }

    @GetMapping("/appointments/{id}")
    public String appointmentDetails(@PathVariable Long id, Model model) {
        model.addAttribute("appointment", appointmentRepository.findById(id).orElseThrow(() ->
                new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND, "Consulta não encontrada.")));
        return "appointment-details";
    }

    @GetMapping("/patients/new")
    public String patientForm(Model model) { model.addAttribute("patient", new Patient()); return "patient-form"; }

    @PostMapping("/patients")
    public String createPatient(@Valid @ModelAttribute Patient patient, BindingResult result, RedirectAttributes redirect) {
        if (patient.getBirthDate() == null) result.rejectValue("birthDate", "required", "Informe a data de nascimento.");
        if (result.hasErrors()) return "patient-form";
        try {
            patientRepository.save(patient);
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            result.rejectValue("cpf", "duplicate", "Já existe um paciente com esse CPF.");
            return "patient-form";
        }
        redirect.addFlashAttribute("success", "Paciente cadastrado com sucesso.");
        return "redirect:/agenda";
    }

    @GetMapping("/doctors/new")
    public String doctorForm(Model model) { model.addAttribute("doctor", new Doctor()); return "doctor-form"; }

    @PostMapping("/doctors")
    public String createDoctor(@Valid @ModelAttribute Doctor doctor, BindingResult result, RedirectAttributes redirect) {
        if (doctor.getPhone() == null || doctor.getPhone().isBlank()) result.rejectValue("phone", "required", "Informe o telefone.");
        if (doctor.getEmail() == null || doctor.getEmail().isBlank()) result.rejectValue("email", "required", "Informe o e-mail.");
        if (result.hasErrors()) return "doctor-form";
        try {
            doctorRepository.save(doctor);
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            result.rejectValue("crm", "duplicate", "Já existe um médico com esse CRM.");
            return "doctor-form";
        }
        redirect.addFlashAttribute("success", "Médico cadastrado com sucesso.");
        return "redirect:/agenda";
    }

    @GetMapping("/appointments/new")
    public String appointmentForm(Model model) { loadParticipants(model); return "appointment-form"; }

    @PostMapping("/appointments")
    public String createAppointment(@RequestParam Long patientId, @RequestParam Long doctorId,
                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime scheduledAt,
                                    RedirectAttributes redirect) {
        try {
            appointmentService.schedule(patientId, doctorId, scheduledAt);
            redirect.addFlashAttribute("success", "Consulta agendada com sucesso.");
        } catch (IllegalArgumentException | IllegalStateException exception) {
            redirect.addFlashAttribute("error", exception.getMessage());
        }
        return "redirect:/agenda";
    }

    @PostMapping("/appointments/{id}/confirm")
    public String confirm(@PathVariable Long id, RedirectAttributes redirect) { return changeStatus(() -> appointmentService.confirm(id), redirect, "Presença confirmada."); }

    @PostMapping("/appointments/{id}/cancel")
    public String cancel(@PathVariable Long id, @RequestParam String reason, RedirectAttributes redirect) { return changeStatus(() -> appointmentService.cancel(id, reason), redirect, "Consulta cancelada; horário liberado."); }

    @PostMapping("/appointments/{id}/complete")
    public String complete(@PathVariable Long id, RedirectAttributes redirect) {
        return changeStatus(() -> appointmentService.complete(id), redirect, "Atendimento concluído.");
    }

    private String changeStatus(Runnable action, RedirectAttributes redirect, String success) {
        try { action.run(); redirect.addFlashAttribute("success", success); }
        catch (IllegalArgumentException | IllegalStateException exception) { redirect.addFlashAttribute("error", exception.getMessage()); }
        return "redirect:/agenda";
    }
    private void loadParticipants(Model model) { model.addAttribute("patients", patientRepository.findAll()); model.addAttribute("doctors", doctorRepository.findAll()); }
}
