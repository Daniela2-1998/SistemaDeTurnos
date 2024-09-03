package com.daniela.sistemaDeTurnos.service;

import com.daniela.sistemaDeTurnos.dto.request.AdministrativosConIdRequestDto;
import com.daniela.sistemaDeTurnos.dto.request.AdministrativosRequestDto;
import com.daniela.sistemaDeTurnos.dto.response.AdministrativosResponseDto;
import com.daniela.sistemaDeTurnos.dto.response.messages.DeleteMensajeResponseDto;
import com.daniela.sistemaDeTurnos.model.Usuarios;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IAdministrativosService {

    Page<AdministrativosResponseDto> getAllAdministrativos(int page, int size, String sortBy);

    AdministrativosResponseDto getAdministrativoById(Long id);

    AdministrativosResponseDto addAdministrativo(AdministrativosRequestDto administrativosRequestDto);

    AdministrativosResponseDto editAdministrativo(AdministrativosConIdRequestDto administrativosConIdRequestDto);

    DeleteMensajeResponseDto deleteAdministrativo(Long id);


    // Funciones extras

    // Comúnes de usuarios
    AdministrativosResponseDto getAdministrativoByMail(String mail);

    List<AdministrativosResponseDto> getAdministrativoByNombre(String nombre);

    AdministrativosResponseDto getAdministrativoByIdentificacion(String identificacion);

    List<AdministrativosResponseDto> getAdministrativoByEstado(Usuarios.Estado estado);

    // Específicas de administrativo
    List<AdministrativosResponseDto> getAdministrativoByCargo(String cargo);

    List<AdministrativosResponseDto>  getAdministrativoByHorario(String horario);

    List<AdministrativosResponseDto> getAdministrativoBySueldo(Double sueldo);

}
