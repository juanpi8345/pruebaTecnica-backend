package com.prueba.consultorioMedico.controller;

import com.prueba.consultorioMedico.dto.Message;
import com.prueba.consultorioMedico.model.ConsultingRoom;
import com.prueba.consultorioMedico.service.IConsultingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultingRoom")
//Recomendable poner la url del cliente frontend
@CrossOrigin("*")
public class ConsultingRoomController {

    @Autowired
    private IConsultingRoomService consultingRoomService;

    @GetMapping("/get")
    public ResponseEntity<List<ConsultingRoom>> findAll(){
        return ResponseEntity.ok(consultingRoomService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Message> addConsultingRoom(@RequestBody ConsultingRoom consultingRoom){
        consultingRoomService.add(consultingRoom);
        Message message = Message.builder().status(HttpStatus.OK).message("Edilicio agregado correctamente").build();
        return ResponseEntity.ok(message);
    }
}
