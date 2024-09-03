package com.daniela.sistemaDeTurnos.service;

import com.daniela.sistemaDeTurnos.dto.request.TurnosConIdRequestDto;
import com.daniela.sistemaDeTurnos.dto.request.TurnosRequestDto;
import com.daniela.sistemaDeTurnos.dto.response.TurnosResponseDto;
import com.daniela.sistemaDeTurnos.dto.response.messages.DeleteMensajeResponseDto;
import com.daniela.sistemaDeTurnos.model.Doctores;
import com.daniela.sistemaDeTurnos.model.Pacientes;
import com.daniela.sistemaDeTurnos.model.Sucursales;
import com.daniela.sistemaDeTurnos.model.Turnos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;


public interface ITurnosService {

    Page<TurnosResponseDto> getAllTurnos(int page, int size, String sortBy);

    TurnosResponseDto getTurnosById(Long id);

    TurnosResponseDto addTurnos(TurnosRequestDto turnosRequestDto);

    TurnosResponseDto editTurnos(TurnosConIdRequestDto turnosConIdRequestDto);

    DeleteMensajeResponseDto deleteTurnos(Long id);


    // Funciones extras

    //Simples
    List<TurnosResponseDto> getTurnosBySucursal(Sucursales sucursal);

    List<TurnosResponseDto> getTurnosByDia(LocalDate dia);

    List<TurnosResponseDto> getTurnosByHora(String hora);

    List<TurnosResponseDto> getTurnosByDoctor(Doctores doctor);

    List<TurnosResponseDto> getTurnosByPaciente(Pacientes paciente);

    //Mixtas
    List<TurnosResponseDto> getTurnosBySucursalAndDia(Sucursales sucursal, LocalDate dia);

    List<TurnosResponseDto> getTurnosByDiaAndHora(LocalDate dia, String hora);

    List<TurnosResponseDto> getTurnosByDoctorAndPaciente(Doctores doctor, Pacientes paciente);

    List<TurnosResponseDto> getTurnosByDoctorAndPacienteAndDia(Doctores doctor, Pacientes paciente, LocalDate dia);

}
