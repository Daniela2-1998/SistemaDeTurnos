package com.daniela.sistemaDeTurnos.service;


import com.daniela.sistemaDeTurnos.dto.request.PacientesConIdRequestDto;
import com.daniela.sistemaDeTurnos.dto.request.PacientesRequestDto;
import com.daniela.sistemaDeTurnos.dto.response.PacientesResponseDto;
import com.daniela.sistemaDeTurnos.dto.response.messages.DeleteMensajeResponseDto;
import com.daniela.sistemaDeTurnos.model.Usuarios;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IPacientesService {

    Page<PacientesResponseDto> getAllPacientes(int page, int size, String sortBy);

    PacientesResponseDto getPacienteById(Long id);

    PacientesResponseDto addPaciente(PacientesRequestDto pacientesRequestDto);

    PacientesResponseDto editPaciente(PacientesConIdRequestDto pacientesConIdRequestDto);

    DeleteMensajeResponseDto deletePaciente(Long id);


    // Funciones extras

    // Comúnes de usuarios
    PacientesResponseDto getPacienteByMail(String mail);

    List<PacientesResponseDto> getPacienteByNombre(String nombre);

    PacientesResponseDto getPacienteByIdentificacion(String identificacion);

    List<PacientesResponseDto> getPacienteByEstado(Usuarios.Estado estado);

    // Específicas de paciente
    PacientesResponseDto getPacienteByCelular(String celular);

}
