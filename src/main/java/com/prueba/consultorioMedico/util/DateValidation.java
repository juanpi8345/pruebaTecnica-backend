package com.prueba.consultorioMedico.util;

import com.prueba.consultorioMedico.exception.OutOfServiceException;
import com.prueba.consultorioMedico.exception.OutOfTimeException;
import com.prueba.consultorioMedico.exception.TimeException;
import org.springframework.cglib.core.Local;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateValidation {

    public static void validateDayOfWeek(LocalDateTime date) throws OutOfServiceException{
        if(date.getDayOfWeek() == DayOfWeek.SUNDAY)
            throw new OutOfServiceException("Error, la clinica no otorga turnos los dias domingo");
    }

    public static void validateAppointmentTime(LocalTime date) throws OutOfServiceException{
        if(date.isBefore(LocalTime.of(8,0)) || date.isAfter(LocalTime.of(23,0)))
            throw new OutOfServiceException("Error, la clinica atiende de 8hs a 23hs");
    }

    //Que los horarios del prfoesional coincidan con el horario de servicio de la clinica
    public static void validateProfessionalTimeOnService(LocalTime start,LocalTime end) throws OutOfServiceException{
        if(start.isBefore(LocalTime.of(8,0)) || end.isAfter(LocalTime.of(23,0)))
            throw new OutOfServiceException("Error, la clinica atiende de 8hs a 23hs");
    }

    public static void validateTime(LocalTime start,LocalTime end) throws TimeException {
        if(start.isAfter(end))
            throw new TimeException("Error, horas invalidas");
    }


    public static void validateProfessionalTime(LocalTime appointmentTime,
                                                LocalTime professionalStartTime,
                                                LocalTime professionalEndTime) throws OutOfServiceException{
        if(appointmentTime.isBefore( professionalStartTime) || appointmentTime.isAfter(professionalEndTime))
            throw new OutOfTimeException("Error, el profesional no esta disponible a las "+ appointmentTime+ "hs");
    }

    public static void validateAbleToModifyOrDelete(LocalDateTime appointmentTime) throws OutOfTimeException{
        LocalDateTime now = LocalDateTime.now();
        //Si el turno es antes que el dia y horario actual + 1 hora.
        if(appointmentTime.isBefore(now.plusHours(1)) ){
            throw new OutOfTimeException("Error no se puede modificar o eliminar el turno dado a que falta menos de una hora para el mismo");
        }
    }



}
