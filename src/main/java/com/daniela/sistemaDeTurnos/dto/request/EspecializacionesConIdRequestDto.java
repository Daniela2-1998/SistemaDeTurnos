package com.daniela.sistemaDeTurnos.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class EspecializacionesConIdRequestDto {

    @NotNull(message = "El ID no puede ser nulo")
    private Long id;

    @NotNull(message = "El nombre de la especialización no puede ser nulo")
    private String nombre;

}
