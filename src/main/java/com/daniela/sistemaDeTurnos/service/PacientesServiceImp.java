package com.daniela.sistemaDeTurnos.service;

import com.daniela.sistemaDeTurnos.dto.request.PacientesConIdRequestDto;
import com.daniela.sistemaDeTurnos.dto.request.PacientesRequestDto;
import com.daniela.sistemaDeTurnos.dto.response.PacientesResponseDto;
import com.daniela.sistemaDeTurnos.dto.response.messages.DeleteMensajeResponseDto;
import com.daniela.sistemaDeTurnos.exception.InsertionDBException;
import com.daniela.sistemaDeTurnos.model.Pacientes;
import com.daniela.sistemaDeTurnos.model.Usuarios;
import com.daniela.sistemaDeTurnos.repository.IPacientesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacientesServiceImp implements IPacientesService{

    @Autowired
    IPacientesRepository pacientesRepository;

    ModelMapper mapper = new ModelMapper();



    // Métodos

    //Básicos
    @Override
    public Page<PacientesResponseDto> getAllPacientes(int page, int size, String sortBy) {
        // Configuración de la página
        Sort sort = Sort.by(Sort.Direction.DESC, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        // Recupero de data
        Page<Pacientes> pacientesPage = pacientesRepository.findAll(pageable);
        if (pacientesPage.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron pacientes en la base de datos");
        }

        return pacientesPage.map(paciente -> mapper.map(paciente, PacientesResponseDto.class));
    }
    @Override
    public PacientesResponseDto getPacienteById(Long id) {
        Pacientes paciente = pacientesRepository.findById(id)
                .orElseThrow(()  -> new EntityNotFoundException("No se encontró un paciente con ID: " + id));
        return mapper.map(paciente, PacientesResponseDto.class);
    }

    @Override
    public PacientesResponseDto addPaciente(PacientesRequestDto pacientesRequestDto) {
        Pacientes paciente = mapper.map(pacientesRequestDto, Pacientes.class);
        Pacientes pacientePersist = pacientesRepository.save(paciente);
        if(pacientePersist == null){
            throw new InsertionDBException("No se pudo guardar al paciente " + pacientesRequestDto.getNombre()
                    + " en la base de datos");
        }

        return mapper.map(pacientePersist, PacientesResponseDto.class);
    }

    @Override
    public PacientesResponseDto editPaciente(PacientesConIdRequestDto pacientesConIdRequestDto) {
        Optional<Pacientes> pacienteRecuperado = pacientesRepository.findById(pacientesConIdRequestDto.getId());
        if(pacienteRecuperado == null){
            return null;
        } else {
            Pacientes paciente = mapper.map(pacientesConIdRequestDto, Pacientes.class);
            Pacientes pacientePersist = pacientesRepository.save(paciente);
            if (pacientePersist == null) {
                throw new InsertionDBException("No se pudo actualizar al paciente " + pacientesConIdRequestDto.getNombre());
            }

            return mapper.map(pacientePersist, PacientesResponseDto.class);
        }
    }

    @Override
    public DeleteMensajeResponseDto deletePaciente(Long id) {
        pacientesRepository.deleteById(id);
        return new DeleteMensajeResponseDto("Paciente solicitado enviado éxitosamente");
    }


    // Extras
    @Override
    public PacientesResponseDto getPacienteByMail(String mail) {
        Pacientes paciente = pacientesRepository.findByMail(mail);
        if (paciente == null){
            throw new EntityNotFoundException("No hay un doctor con celular: " + mail);
        }
        return mapper.map(paciente, PacientesResponseDto.class);
    }

    @Override
    public List<PacientesResponseDto> getPacienteByNombre(String nombre) {
        List<Pacientes> pacientes = pacientesRepository.findByNombre(nombre);
        List<PacientesResponseDto> listaPacientes = new ArrayList<>();
        listaPacientes.stream().forEach(paciente -> listaPacientes.add(mapper.map(paciente, PacientesResponseDto.class)));
        return listaPacientes;
    }

    @Override
    public PacientesResponseDto getPacienteByIdentificacion(String identificacion) {
        Pacientes paciente = pacientesRepository.findByIdentificacion(identificacion);
        return mapper.map(paciente, PacientesResponseDto.class);    }

    @Override
    public List<PacientesResponseDto> getPacienteByEstado(Usuarios.Estado estado) {
        List<Pacientes> pacientes = pacientesRepository.findByEstado(estado);
        List<PacientesResponseDto> listaPacientes = new ArrayList<>();
        listaPacientes.stream().forEach(paciente -> listaPacientes.add(mapper.map(paciente, PacientesResponseDto.class)));
        return listaPacientes;
    }

    @Override
    public PacientesResponseDto getPacienteByCelular(String celular) {
        Pacientes paciente = pacientesRepository.findByCelular(celular);
        return mapper.map(paciente, PacientesResponseDto.class);
    }
}
