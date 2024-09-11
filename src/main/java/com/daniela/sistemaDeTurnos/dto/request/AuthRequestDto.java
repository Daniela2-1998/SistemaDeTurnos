package com.daniela.sistemaDeTurnos.dto.request;


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
public class AuthRequestDto {

    @Email
    @NotNull(message = "El mail no puede ser nulo")
    private String mail;

    @Size(min = 6, max = 12, message = "La contraseña debe tener entre 6 y 12 caracteres")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "La contraseña debe contener al menos una mayúscula, " +
                    "una minúscula, un número y un carácter especial")
    private String contraseña;

    @NotNull(message = "El tipo de usuario no puede ser nulo")
    private String tipoUsuario;

}
