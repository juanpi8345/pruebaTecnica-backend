package com.prueba.consultorioMedico.repository;

import com.prueba.consultorioMedico.model.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISpecialityRepository extends JpaRepository<Speciality,Long> {
}
