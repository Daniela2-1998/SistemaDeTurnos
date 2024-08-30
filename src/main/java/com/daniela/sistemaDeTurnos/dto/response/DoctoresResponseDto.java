package com.daniela.sistemaDeTurnos.dto.response;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoctoresResponseDto extends UsuariosResponseDto {

    private List<String> especializaciones;

    private Double sueldo;

    private String celular;

}
