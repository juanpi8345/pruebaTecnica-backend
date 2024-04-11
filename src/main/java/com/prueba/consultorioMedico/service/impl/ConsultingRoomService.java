package com.prueba.consultorioMedico.service.impl;

import com.prueba.consultorioMedico.model.ConsultingRoom;
import com.prueba.consultorioMedico.repository.IConsultingRoomRepository;
import com.prueba.consultorioMedico.service.IConsultingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultingRoomService implements IConsultingRoomService {

    @Autowired
    private IConsultingRoomRepository consultingRoomRepository;

    @Override
    public List<ConsultingRoom> findAll() {
        return consultingRoomRepository.findAll();
    }

    @Override
    public void add(ConsultingRoom consultingRoom) {
        consultingRoomRepository.save(consultingRoom);
    }
}
