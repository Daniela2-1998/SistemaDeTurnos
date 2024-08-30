package com.daniela.sistemaDeTurnos.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PacientesRequestDto extends UsuariosRequestDto{

    @NotNull(message = "El número de celular no puede ser nulo")
    private String celular;

}
