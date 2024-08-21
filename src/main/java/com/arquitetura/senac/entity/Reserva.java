package com.arquitetura.senac.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Reserva extends BaseEntity {
    private LocalDateTime dataReserva;
    private LocalDateTime dataDevolucao;

}
