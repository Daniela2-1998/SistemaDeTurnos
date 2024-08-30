package com.daniela.sistemaDeTurnos.dto.response;

import com.daniela.sistemaDeTurnos.model.Doctores;
import com.daniela.sistemaDeTurnos.model.Pacientes;
import com.daniela.sistemaDeTurnos.model.Sucursales;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class TurnosResponseDto {

    private Long id;

    private Sucursales sucursal;

    private LocalDate dia;

    private String hora;

    private Doctores doctor;

    private Pacientes paciente;

}
