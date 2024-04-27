package com.prueba.consultorioMedico.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="consulting_rooms")
public class ConsultingRoom {
    @Id
    private String consultingRoomName;
}
