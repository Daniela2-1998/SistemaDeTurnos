package com.daniela.sistemaDeTurnos.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdministrativosResponseDto extends UsuariosResponseDto {

    private String cargo;

    private String horarios;

    private Double sueldo;


}
