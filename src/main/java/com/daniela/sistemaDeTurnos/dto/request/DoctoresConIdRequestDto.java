package com.daniela.sistemaDeTurnos.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoctoresConIdRequestDto extends UsuariosConIdRequestDto{

    @NotNull(message = "El doctor debe tener al menos una especialización")
    private String especializacion;

    @Min(value = 1, message = "El sueldo debe ser superior a 1")
    @NotNull(message = "El cargo no puede ser nulo")
    private Double sueldo;

    @NotNull(message = "El celular no puede ser nulo")
    private String celular;

}
