package br.com.mediagenda.domain;

import jakarta.persistence.*;

@Entity
public class MedicalRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(nullable = false, unique = true)
    private Appointment appointment;

    @Column(nullable = false, length = 4000)
    private String diagnosis;
    @Column(nullable = false, length = 4000)
    private String prescription;
    @Column(length = 4000)
    private String notes;

    protected MedicalRecord() {}

    public MedicalRecord(Appointment appointment, String diagnosis, String prescription, String notes) {
        if (appointment.getStatus() != AppointmentStatus.REALIZADA) {
            throw new IllegalStateException("Conclua a consulta antes de registrar o prontuário.");
        }
        this.appointment = appointment;
        this.diagnosis = field(diagnosis, "Diagnóstico", true);
        this.prescription = field(prescription, "Prescrição", true);
        this.notes = field(notes, "Observações", false);
    }

    private static String field(String value, String label, boolean required) {
        String text = value == null ? "" : value.strip();
        if (required && text.isEmpty()) throw new IllegalArgumentException("Preencha o campo " + label + ".");
        if (text.length() > 4000) throw new IllegalArgumentException(label + " excede 4.000 caracteres.");
        return text;
    }

    public Long getId() { return id; }
    public Appointment getAppointment() { return appointment; }
    public String getDiagnosis() { return diagnosis; }
    public String getPrescription() { return prescription; }
    public String getNotes() { return notes; }
}
