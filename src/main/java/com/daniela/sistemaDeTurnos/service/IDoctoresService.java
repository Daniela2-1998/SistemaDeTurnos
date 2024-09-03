package com.daniela.sistemaDeTurnos.service;

import com.daniela.sistemaDeTurnos.dto.request.DoctoresConIdRequestDto;
import com.daniela.sistemaDeTurnos.dto.request.DoctoresRequestDto;
import com.daniela.sistemaDeTurnos.dto.response.DoctoresResponseDto;
import com.daniela.sistemaDeTurnos.dto.response.messages.DeleteMensajeResponseDto;
import com.daniela.sistemaDeTurnos.model.Usuarios;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IDoctoresService {

    Page<DoctoresResponseDto> getAllDoctores(int page, int size, String sortBy);

    DoctoresResponseDto getDoctorById(Long id);

    DoctoresResponseDto addDoctor(DoctoresRequestDto doctoresRequestDto);

    DoctoresResponseDto editDoctor(DoctoresConIdRequestDto doctoresConIdRequestDto);

    DeleteMensajeResponseDto deleteDoctor(Long id);


    // Funciones extras

    // Comúnes de usuarios
    DoctoresResponseDto getDoctorByMail(String mail);

    List<DoctoresResponseDto> getDoctorByNombre(String nombre);

    DoctoresResponseDto getDoctorByIdentificacion(String identificacion);

    List<DoctoresResponseDto> getDoctorByEstado(Usuarios.Estado estado);

    // Específicas de doctor
    List<DoctoresResponseDto> getDoctorByEspecializacion(String especializacion);

    List<DoctoresResponseDto> getDoctorBySueldo(Double sueldo);

    DoctoresResponseDto getDoctorByCelular(String celular);




}
