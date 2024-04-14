package com.prueba.consultorioMedico.repository;

import com.prueba.consultorioMedico.model.Professional;
import com.prueba.consultorioMedico.model.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISpecialityRepository extends JpaRepository<Speciality,String> {
    //Hago esto porque la relacion es 'vaga'
    @Query("SELECT s FROM Speciality s WHERE :professional MEMBER OF s.professionalList")
    List<Speciality> findAllByProfessional(@Param("professional") Professional professional);
}
