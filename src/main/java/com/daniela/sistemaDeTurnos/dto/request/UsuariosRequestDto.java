package com.daniela.sistemaDeTurnos.dto.request;

import com.daniela.sistemaDeTurnos.model.Usuarios;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuariosRequestDto {

    @Email
    @NotNull(message = "El mail no puede ser nulo")
    private String mail;

    @Size(min = 6, max = 12, message = "La contraseña debe tener entre 6 y 12 caracteres")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "La contraseña debe contener al menos una mayúscula, " +
                    "una minúscula, un número y un carácter especial")
    private String contraseña;

    @Size(min = 2, max = 20)
    @NotNull(message = "El nombre de la persona no puede ser nulo")
    private String nombre;

    @Size(min = 2, max = 20)
    @NotNull(message = "El apellido de la persona no puede ser nulo")
    private String apellido;

    @NotNull(message = "El tipo de usuario no puede ser nulo")
    private String tipo;

    @Size(min = 1, max = 10, message = "La identificación debe tener entre 1 y 10 digitos")
    @NotNull(message = "Debe haber algún tipo de identificación")
    private String identificacion;

    @Enumerated(EnumType.STRING)
    private Usuarios.Estado estado;
}
