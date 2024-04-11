package com.prueba.consultorioMedico.repository;

import com.prueba.consultorioMedico.model.ConsultingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IConsultingRoomRepository extends JpaRepository<ConsultingRoom, String> {

}
