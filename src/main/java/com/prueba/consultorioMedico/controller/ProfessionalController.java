package com.prueba.consultorioMedico.controller;

import com.prueba.consultorioMedico.dto.Message;
import com.prueba.consultorioMedico.dto.ProfessionalDto;
import com.prueba.consultorioMedico.model.ConsultingRoom;
import com.prueba.consultorioMedico.model.Professional;
import com.prueba.consultorioMedico.service.IProfessionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professional")
//Recomendable poner la url del cliente frontend
@CrossOrigin("*")
public class ProfessionalController {

    @Autowired
    private IProfessionalService professionalService;

    @GetMapping("/get")
    public ResponseEntity<List<Professional>> findAll(){
        return ResponseEntity.ok(professionalService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Message> addProfessional(@RequestBody ProfessionalDto professionalDto){
        Professional professional = Professional.builder().name(professionalDto.getName())
                        .lastname(professionalDto.getLastname()).dni(professionalDto.getDni())
                        .start(professionalDto.getStart()).end(professionalDto.getEnd()).build();
        professionalService.add(professional);
        Message message = Message.builder().status(HttpStatus.OK).message("Profesional guardado correctamente").build();
        return ResponseEntity.ok(message);
    }
}
