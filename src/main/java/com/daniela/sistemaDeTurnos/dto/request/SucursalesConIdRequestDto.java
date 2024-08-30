package com.daniela.sistemaDeTurnos.dto.request;

import com.daniela.sistemaDeTurnos.model.Doctores;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class SucursalesConIdRequestDto {

    @NotNull(message = "El ID no puede ser nulo")
    private Long id;

    @NotNull(message = "El barrio no puede ser nulo")
    private String barrio;

    @NotNull(message = "La dirección no puede ser nula")
    private String direccion;

    private List<String> especializaciones = new ArrayList<>();

    private List<Doctores> doctores = new ArrayList<>();

}
