package com.daniela.sistemaDeTurnos.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdministrativosConIdRequestDto extends UsuariosConIdRequestDto {

    @NotNull(message = "El cargo no puede ser nulo")
    private String cargo;

    @NotNull(message = "Debes ingresar un horario laboral")
    private String horarios;

    @Min(value = 1)
    @NotNull(message = "Debes ingresar un sueldo")
    private Double sueldo;


}
