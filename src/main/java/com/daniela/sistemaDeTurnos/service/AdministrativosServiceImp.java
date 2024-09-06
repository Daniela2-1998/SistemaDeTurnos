package com.daniela.sistemaDeTurnos.service;

import com.daniela.sistemaDeTurnos.dto.request.AdministrativosConIdRequestDto;
import com.daniela.sistemaDeTurnos.dto.request.AdministrativosRequestDto;
import com.daniela.sistemaDeTurnos.dto.response.AdministrativosResponseDto;
import com.daniela.sistemaDeTurnos.dto.response.messages.DeleteMensajeResponseDto;
import com.daniela.sistemaDeTurnos.exception.InsertionDBException;
import com.daniela.sistemaDeTurnos.model.Administrativos;
import com.daniela.sistemaDeTurnos.model.Usuarios;
import com.daniela.sistemaDeTurnos.repository.IAdministrativosRepository;
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
public class AdministrativosServiceImp implements IAdministrativosService{

    @Autowired
    IAdministrativosRepository administrativosRepository;

    ModelMapper mapper = new ModelMapper();



    // Métodos

    //Básicos
    @Override
    public Page<AdministrativosResponseDto> getAllAdministrativos(int page, int size, String sortBy) {
        // Configuración de la página
        Sort sort = Sort.by(Sort.Direction.DESC, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        // Recupero de data
        Page<Administrativos> administrativosPage = administrativosRepository.findAll(pageable);
        if(administrativosPage.isEmpty()){
            throw new EntityNotFoundException("No se encontraron administrativos en la base de datos.");
        }

        return administrativosPage.map(administrativo -> mapper.map(administrativo, AdministrativosResponseDto.class));
    }

    @Override
    public AdministrativosResponseDto getAdministrativoById(Long id) {
        Administrativos administrativo = administrativosRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró un administrativo con el ID: " + id));
        return mapper.map(administrativo, AdministrativosResponseDto.class);
    }

    @Override
    public AdministrativosResponseDto addAdministrativo(AdministrativosRequestDto administrativosRequestDto) {
        Administrativos administrativo = mapper.map(administrativosRequestDto, Administrativos.class);
        Administrativos administrativoPersist = administrativosRepository.save(administrativo);
        if (administrativoPersist == null){
            throw new InsertionDBException("No se pudo guardar al administrativo " + administrativo.getNombre());
        }
        return mapper.map(administrativoPersist, AdministrativosResponseDto.class);
    }

    @Override
    public AdministrativosResponseDto editAdministrativo(AdministrativosConIdRequestDto administrativosConIdRequestDto) {
        Optional<Administrativos> administrativoRecuperado = administrativosRepository.findById(administrativosConIdRequestDto.getId());
        if(administrativoRecuperado == null){
            return null;
        } else {
            Administrativos administrativo = mapper.map(administrativosConIdRequestDto, Administrativos.class);
            Administrativos administrativoPersist = administrativosRepository.save(administrativo);
            if (administrativoPersist == null) {
                throw new InsertionDBException("No se pudo actualizar al paciente " + administrativo.getNombre());
            }

            return mapper.map(administrativoPersist, AdministrativosResponseDto.class);
        }
    }

    @Override
    public DeleteMensajeResponseDto deleteAdministrativo(Long id) {
        administrativosRepository.deleteById(id);
        return new DeleteMensajeResponseDto("Se eliminó correctamente al administrativo solicitado.");
    }


    // Extras
    @Override
    public AdministrativosResponseDto getAdministrativoByMail(String mail) {
        Administrativos administrativo = administrativosRepository.findByMail(mail);
        return mapper.map(administrativo, AdministrativosResponseDto.class);
    }

    @Override
    public List<AdministrativosResponseDto> getAdministrativoByNombre(String nombre) {
        List<Administrativos> administrativos = administrativosRepository.findByNombre(nombre);
        return administrativos.stream()
                .map(administrativo -> mapper.map(administrativo, AdministrativosResponseDto.class))
                .toList();
    }

    @Override
    public AdministrativosResponseDto getAdministrativoByIdentificacion(String identificacion) {
        Administrativos administrativo = administrativosRepository.findByIdentificacion(identificacion);
        return mapper.map(administrativo, AdministrativosResponseDto.class);
    }

    @Override
    public List<AdministrativosResponseDto> getAdministrativoByEstado(Usuarios.Estado estado) {
        List<Administrativos> administrativos = administrativosRepository.findByEstado(estado);
        return administrativos.stream()
                .map(administrativo -> mapper.map(administrativo, AdministrativosResponseDto.class))
                .toList();
    }

    @Override
    public List<AdministrativosResponseDto> getAdministrativoByCargo(String cargo) {
        List<Administrativos> administrativos = administrativosRepository.findByCargo(cargo);
        return administrativos.stream()
                .map(administrativo -> mapper.map(administrativo, AdministrativosResponseDto.class))
                .toList();
    }

    @Override
    public List<AdministrativosResponseDto> getAdministrativoByHorario(String horario) {
        List<Administrativos> administrativos = administrativosRepository.findByHorarios(horario);
        return administrativos.stream()
                .map(administrativo -> mapper.map(administrativo, AdministrativosResponseDto.class))
                .toList();
    }

    @Override
    public List<AdministrativosResponseDto> getAdministrativoBySueldo(Double sueldo) {
        List<Administrativos> administrativos = administrativosRepository.findBySueldo(sueldo);
        return administrativos.stream()
                .map(administrativo -> mapper.map(administrativo, AdministrativosResponseDto.class))
                .toList();
    }
}
