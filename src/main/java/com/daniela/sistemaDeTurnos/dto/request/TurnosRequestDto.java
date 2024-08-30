package com.daniela.sistemaDeTurnos.dto.request;

import com.daniela.sistemaDeTurnos.model.Doctores;
import com.daniela.sistemaDeTurnos.model.Pacientes;
import com.daniela.sistemaDeTurnos.model.Sucursales;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class TurnosRequestDto {


    @NotNull(message = "La sucursal no puede ser nula")
    private Sucursales sucursal;

    @NotNull(message = "El día no puede ser nulo")
    private LocalDate dia;

    @NotNull(message = "La hora no puede ser nula")
    private String hora;

    @NotNull(message = "Debe haber un doctor registrado en el turno")
    private Doctores doctor;

    private Pacientes paciente;

}
