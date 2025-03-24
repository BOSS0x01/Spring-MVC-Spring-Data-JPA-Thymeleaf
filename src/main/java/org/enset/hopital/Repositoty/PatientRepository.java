package org.enset.hopital.Repositoty;

import org.enset.hopital.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
