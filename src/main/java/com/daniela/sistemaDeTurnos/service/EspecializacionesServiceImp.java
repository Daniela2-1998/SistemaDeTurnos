package com.daniela.sistemaDeTurnos.service;

import com.daniela.sistemaDeTurnos.dto.request.EspecializacionesConIdRequestDto;
import com.daniela.sistemaDeTurnos.dto.request.EspecializacionesRequestDto;
import com.daniela.sistemaDeTurnos.dto.response.EspecializacionesResponseDto;
import com.daniela.sistemaDeTurnos.dto.response.messages.DeleteMensajeResponseDto;

import com.daniela.sistemaDeTurnos.exception.InsertionDBException;
import com.daniela.sistemaDeTurnos.model.Especializaciones;
import com.daniela.sistemaDeTurnos.repository.IEspecializacionesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecializacionesServiceImp implements IEspecializacionesService{

    @Autowired
    IEspecializacionesRepository especializacionesRepository;

    ModelMapper mapper = new ModelMapper();


    // Métodos

    //Básicos
    @Override
    public Page<EspecializacionesResponseDto> getAllEspecializaciones(int page, int size, String sortBy) {
        // Configuración de la página
        Sort sort = Sort.by(Sort.Direction.DESC, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        // Recupero de data
        Page<Especializaciones> especializacionesPage = especializacionesRepository.findAll(pageable);
        if (especializacionesPage.isEmpty()){
            throw new EntityNotFoundException("No se encontraron registros de especializaciones");
        }

        return especializacionesPage.map(especializacion -> mapper.map(especializacion, EspecializacionesResponseDto.class));
    }

    @Override
    public EspecializacionesResponseDto getEspecializacionById(Long id) {
        Especializaciones especializacion = especializacionesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró una especialización con el ID: " + id));
        return mapper.map(especializacion, EspecializacionesResponseDto.class);
    }

    @Override
    public EspecializacionesResponseDto addEspecializacion(EspecializacionesRequestDto especializacionesRequestDto) {
        Especializaciones especializacion = mapper.map(especializacionesRequestDto, Especializaciones.class);
        Especializaciones especializacionPersist = especializacionesRepository.save(especializacion);
        if (especializacionPersist == null){
            throw new InsertionDBException("No se pudo guardar la especializacion " + especializacionesRequestDto.getNombre());
        }

        return mapper.map(especializacionPersist, EspecializacionesResponseDto.class);
    }

    @Override
    public EspecializacionesResponseDto editEspecializacion(EspecializacionesConIdRequestDto especializacionesConIdRequestDto) {
        Especializaciones especializacion = mapper.map(especializacionesConIdRequestDto, Especializaciones.class);
        Especializaciones especializacionPersist = especializacionesRepository.save(especializacion);
        if (especializacionPersist == null){
            throw new InsertionDBException("No se pudo actualizar la especializacion " + especializacionesConIdRequestDto.getNombre());
        }

        return mapper.map(especializacionPersist, EspecializacionesResponseDto.class);
    }

    @Override
    public DeleteMensajeResponseDto deleteEspecializacion(Long id) {
        especializacionesRepository.deleteById(id);
        return new DeleteMensajeResponseDto("Se eliminó correctamente la especialización solicitada.");
    }

    // Extra
    @Override
    public EspecializacionesResponseDto getEspecializacionByNombre(String nombre) {
        Especializaciones especializacion = especializacionesRepository.findByNombre(nombre);

        if(especializacion == null){
            throw new EntityNotFoundException("No se encontró una especialización de " + nombre);
        }

        return mapper.map(especializacion, EspecializacionesResponseDto.class);
    }

}
