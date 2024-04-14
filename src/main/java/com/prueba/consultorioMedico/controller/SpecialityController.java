package com.prueba.consultorioMedico.controller;

import com.prueba.consultorioMedico.dto.Message;
import com.prueba.consultorioMedico.dto.ProfessionalDto;
import com.prueba.consultorioMedico.dto.SpecialityDto;
import com.prueba.consultorioMedico.model.Professional;
import com.prueba.consultorioMedico.model.Speciality;
import com.prueba.consultorioMedico.service.ISpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/speciality")
//Recomendable poner la url del cliente frontend
@CrossOrigin("*")
public class SpecialityController {

    @Autowired
    private ISpecialityService specialityService;

    @GetMapping("/get")
    public ResponseEntity<List<Speciality>> findAll(){
        return ResponseEntity.ok(specialityService.findAll());
    }

    @GetMapping("/get/professional/{professionalDni}")
    public ResponseEntity<List<Speciality>> findAllByProfessional(@PathVariable String professionalDni){
        return ResponseEntity.ok(specialityService.findSpecialitiesByProfessional(professionalDni));
    }

    @PostMapping("/add")
    public ResponseEntity<Message> addProfessional(@RequestBody SpecialityDto specialityDto){
        Speciality speciality = Speciality.builder().name(specialityDto.getName()).build();
        specialityService.add(speciality);
        Message message = Message.builder().status(HttpStatus.OK).message("Especialidad guardada correctamente").build();
        return ResponseEntity.ok(message);
    }
}
