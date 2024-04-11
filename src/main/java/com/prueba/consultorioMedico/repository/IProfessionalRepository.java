package com.prueba.consultorioMedico.repository;

import com.prueba.consultorioMedico.model.Professional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfessionalRepository extends JpaRepository<Professional,String> {
}
