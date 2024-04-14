package com.prueba.consultorioMedico.controller;

import com.prueba.consultorioMedico.dto.Message;
import com.prueba.consultorioMedico.exception.OutOfServiceException;
import com.prueba.consultorioMedico.exception.OutOfTimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

//En esta clase manejo todas las excepciones y sus respectivas respuestas
@ControllerAdvice
public class RestExceptionHandler {

    //Si la clinica esta fuera de servicio
    @ExceptionHandler(OutOfServiceException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<Message> outOfService(OutOfServiceException e){
        Message err = new Message(HttpStatus.NOT_ACCEPTABLE,e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(err);
    }

    //Si el profesional esta fuera de servicio
    //Si se quiere eliminar la cita y queda 1 hora o menos
    @ExceptionHandler(OutOfTimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Message> outOfTime(OutOfTimeException e){
        Message err = new Message(HttpStatus.BAD_REQUEST,e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }




}
