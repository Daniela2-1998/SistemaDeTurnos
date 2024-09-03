package com.daniela.sistemaDeTurnos.service;

import com.daniela.sistemaDeTurnos.dto.request.TurnosConIdRequestDto;
import com.daniela.sistemaDeTurnos.dto.request.TurnosRequestDto;
import com.daniela.sistemaDeTurnos.dto.response.TurnosResponseDto;
import com.daniela.sistemaDeTurnos.dto.response.messages.DeleteMensajeResponseDto;
import com.daniela.sistemaDeTurnos.exception.InsertionDBException;
import com.daniela.sistemaDeTurnos.model.*;
import com.daniela.sistemaDeTurnos.repository.ITurnosRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnosServiceImp implements ITurnosService {

    @Autowired
    ITurnosRepository turnosRepository;

    ModelMapper mapper = new ModelMapper();



    // Métodos

    //Básicos
    @Override
    public Page<TurnosResponseDto> getAllTurnos(int page, int size, String sortBy) {
        // Configuración de la página
        Sort sort = Sort.by(Sort.Direction.DESC, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        // Recupero de data
        Page<Turnos> turnosPage = turnosRepository.findAll(pageable);
        if(turnosPage.isEmpty()){
            throw new EntityNotFoundException("No se encontraron turnos en la base de datos.");
        }
        return turnosPage.map(turno -> mapper.map(turno, TurnosResponseDto.class));
    }

    @Override
    public TurnosResponseDto getTurnosById(Long id) {
        Turnos turno = turnosRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró un turno con ID: " + id));
        return mapper.map(turno, TurnosResponseDto.class);
    }

    @Override
    public TurnosResponseDto addTurnos(TurnosRequestDto turnosRequestDto) {
        Turnos turno = mapper.map(turnosRequestDto, Turnos.class);
        Turnos turnoPersist = turnosRepository.save(turno);
        if (turnoPersist == null){
            throw new InsertionDBException("No se pudo guardar el turno en la base de datos.");
        }
        return mapper.map(turnoPersist, TurnosResponseDto.class);
    }

    @Override
    public TurnosResponseDto editTurnos(TurnosConIdRequestDto turnosConIdRequestDto) {
        Optional<Turnos> turnoRecuperado = turnosRepository.findById(turnosConIdRequestDto.getId());
        if (turnoRecuperado == null) {
            return null;
        } else {
            Turnos turno = mapper.map(turnosConIdRequestDto, Turnos.class);
            Turnos turnoPersist = turnosRepository.save(turno);
            if (turnoPersist == null) {
                throw new InsertionDBException("No se pudo actualizar el turno " + turno.getPaciente() + "-"
                        + turno.getDoctor() + "(" + turno.getDia() + " " + turno.getHora() + ")");
            }

            return mapper.map(turnoPersist, TurnosResponseDto.class);
        }
    }

    @Override
    public DeleteMensajeResponseDto deleteTurnos(Long id) {
        turnosRepository.deleteById(id);
        return new DeleteMensajeResponseDto("Se eliminó correctamente el turno solicitado.");
    }


    // Extras
    @Override
    public List<TurnosResponseDto> getTurnosBySucursal(Sucursales sucursal) {
        List<Turnos> turnos = turnosRepository.findBySucursal(sucursal);
        List<TurnosResponseDto> turnosLista = new ArrayList<>();
        turnosLista.stream().forEach(turno -> turnosLista.add(mapper.map(turno, TurnosResponseDto.class)));
        return turnosLista;
    }

    @Override
    public List<TurnosResponseDto> getTurnosByDia(LocalDate dia) {
        List<Turnos> turnos = turnosRepository.findByDia(dia);
        List<TurnosResponseDto> turnosLista = new ArrayList<>();
        turnosLista.stream().forEach(turno -> turnosLista.add(mapper.map(turno, TurnosResponseDto.class)));
        return turnosLista;
    }

    @Override
    public List<TurnosResponseDto> getTurnosByHora(String hora) {
        List<Turnos> turnos = turnosRepository.findByHora(hora);
        List<TurnosResponseDto> turnosLista = new ArrayList<>();
        turnosLista.stream().forEach(turno -> turnosLista.add(mapper.map(turno, TurnosResponseDto.class)));
        return turnosLista;
    }

    @Override
    public List<TurnosResponseDto> getTurnosByDoctor(Doctores doctor) {
        List<Turnos> turnos = turnosRepository.findByDoctor(doctor);
        List<TurnosResponseDto> turnosLista = new ArrayList<>();
        turnosLista.stream().forEach(turno -> turnosLista.add(mapper.map(turno, TurnosResponseDto.class)));
        return turnosLista;    }

    @Override
    public List<TurnosResponseDto> getTurnosByPaciente(Pacientes paciente) {
        List<Turnos> turnos = turnosRepository.findByPaciente(paciente);
        List<TurnosResponseDto> turnosLista = new ArrayList<>();
        turnosLista.stream().forEach(turno -> turnosLista.add(mapper.map(turno, TurnosResponseDto.class)));
        return turnosLista;
    }

    @Override
    public List<TurnosResponseDto> getTurnosBySucursalAndDia(Sucursales sucursal, LocalDate dia) {
        List<Turnos> turnos = turnosRepository.findBySucursalAndDia(sucursal, dia);
        List<TurnosResponseDto> turnosLista = new ArrayList<>();
        turnosLista.stream().forEach(turno -> turnosLista.add(mapper.map(turno, TurnosResponseDto.class)));
        return turnosLista;
    }

    @Override
    public List<TurnosResponseDto> getTurnosByDiaAndHora(LocalDate dia, String hora) {
        List<Turnos> turnos = turnosRepository.findByDiaAndHora(dia, hora);
        List<TurnosResponseDto> turnosLista = new ArrayList<>();
        turnosLista.stream().forEach(turno -> turnosLista.add(mapper.map(turno, TurnosResponseDto.class)));
        return turnosLista;
    }

    @Override
    public List<TurnosResponseDto> getTurnosByDoctorAndPaciente(Doctores doctor, Pacientes paciente) {
        List<Turnos> turnos = turnosRepository.findByDoctorAndPaciente(doctor, paciente);
        List<TurnosResponseDto> turnosLista = new ArrayList<>();
        turnosLista.stream().forEach(turno -> turnosLista.add(mapper.map(turno, TurnosResponseDto.class)));
        return turnosLista;
    }

    @Override
    public List<TurnosResponseDto> getTurnosByDoctorAndPacienteAndDia(Doctores doctor, Pacientes paciente, LocalDate dia) {
        List<Turnos> turnos = (List<Turnos>) turnosRepository.findByDoctorAndPacienteAndDia(doctor, paciente, dia);
        List<TurnosResponseDto> turnosLista = new ArrayList<>();
        turnosLista.stream().forEach(turno -> turnosLista.add(mapper.map(turno, TurnosResponseDto.class)));
        return turnosLista;
    }
}
