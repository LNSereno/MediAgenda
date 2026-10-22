package br.com.mediagenda.repository;
import br.com.mediagenda.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DoctorRepository extends JpaRepository<Doctor, Long> { }
