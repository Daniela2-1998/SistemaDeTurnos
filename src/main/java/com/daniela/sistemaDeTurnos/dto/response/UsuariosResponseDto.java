package com.daniela.sistemaDeTurnos.dto.response;

import com.daniela.sistemaDeTurnos.model.Usuarios;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuariosResponseDto {

    private Long id;

    private String mail;

    private String contraseña;

    private String nombre;

    private String tipo;

    private String identificacion;

    private Usuarios.Estado estado;

}
