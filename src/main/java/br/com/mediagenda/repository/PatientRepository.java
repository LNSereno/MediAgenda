package br.com.mediagenda.repository;
import br.com.mediagenda.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PatientRepository extends JpaRepository<Patient, Long> { }
