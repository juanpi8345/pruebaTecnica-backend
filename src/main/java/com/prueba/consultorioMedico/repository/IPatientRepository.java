package com.prueba.consultorioMedico.repository;

import com.prueba.consultorioMedico.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPatientRepository extends JpaRepository<Patient,String> {
}
