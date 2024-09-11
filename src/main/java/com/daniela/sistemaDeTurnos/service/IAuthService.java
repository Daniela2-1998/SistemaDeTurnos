package com.daniela.sistemaDeTurnos.service;

import com.daniela.sistemaDeTurnos.dto.request.AuthConIdRequestDto;
import com.daniela.sistemaDeTurnos.dto.request.AuthRequestDto;
import com.daniela.sistemaDeTurnos.dto.response.AuthResponseDto;
import com.daniela.sistemaDeTurnos.dto.response.messages.DeleteMensajeResponseDto;

import java.util.List;

public interface IAuthService {

    AuthResponseDto getAuthById(Long id);

    AuthResponseDto addAuth(AuthRequestDto authRequestDto);

    AuthResponseDto editAuth(AuthConIdRequestDto authConIdRequestDto);

    DeleteMensajeResponseDto deleteAuth(Long id);


    // Funciones extras
    AuthResponseDto getAuthByMail(String mail);

    AuthResponseDto login (String mail, String contraseña);

    List<AuthResponseDto> getAuthByTipoUsuario(String tipoUsuario);

}
