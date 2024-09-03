package com.daniela.sistemaDeTurnos.service;

import com.daniela.sistemaDeTurnos.dto.request.EspecializacionesConIdRequestDto;
import com.daniela.sistemaDeTurnos.dto.request.EspecializacionesRequestDto;
import com.daniela.sistemaDeTurnos.dto.response.EspecializacionesResponseDto;
import com.daniela.sistemaDeTurnos.dto.response.messages.DeleteMensajeResponseDto;
import org.springframework.data.domain.Page;


public interface IEspecializacionesService {

    Page<EspecializacionesResponseDto> getAllEspecializaciones(int page, int size, String sortBy);

    EspecializacionesResponseDto getEspecializacionById(Long id);

    EspecializacionesResponseDto addEspecializacion(EspecializacionesRequestDto especializacionesRequestDto);

    EspecializacionesResponseDto editEspecializacion(EspecializacionesConIdRequestDto especializacionesConIdRequestDto);

    DeleteMensajeResponseDto deleteEspecializacion(Long id);


    // Funciones extras
    EspecializacionesResponseDto getEspecializacionByNombre(String nombre);

}
