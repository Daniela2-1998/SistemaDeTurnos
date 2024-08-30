package com.daniela.sistemaDeTurnos.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PacientesResponseDto extends UsuariosResponseDto {

    private String celular;

}
