package com.daniela.sistemaDeTurnos.service;

import com.daniela.sistemaDeTurnos.dto.request.DoctoresConIdRequestDto;
import com.daniela.sistemaDeTurnos.dto.request.DoctoresRequestDto;
import com.daniela.sistemaDeTurnos.dto.response.DoctoresResponseDto;
import com.daniela.sistemaDeTurnos.dto.response.messages.DeleteMensajeResponseDto;
import com.daniela.sistemaDeTurnos.exception.InsertionDBException;
import com.daniela.sistemaDeTurnos.model.Doctores;
import com.daniela.sistemaDeTurnos.model.Usuarios;
import com.daniela.sistemaDeTurnos.repository.IDoctoresRepository;
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

@Service
public class DoctoresServiceImp implements IDoctoresService {

    @Autowired
    IDoctoresRepository doctoresRepository;

    ModelMapper mapper = new ModelMapper();


    // Métodos

    //Básicos
    @Override
    public Page<DoctoresResponseDto> getAllDoctores(int page, int size, String sortBy) {
        // Configuración de la página
        Sort sort = Sort.by(Sort.Direction.DESC, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        // Recupero de data
        Page<Doctores> doctoresPage = doctoresRepository.findAll(pageable);
        if (doctoresPage.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron doctores en la base de datos");
        }

        return doctoresPage.map(doctor -> mapper.map(doctor, DoctoresResponseDto.class));
    }

    @Override
    public DoctoresResponseDto getDoctorById(Long id) {
        Doctores doctor = doctoresRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No en encontró un doctor con ID: " + id));
        return mapper.map(doctor, DoctoresResponseDto.class);
    }

    @Override
    public DoctoresResponseDto addDoctor(DoctoresRequestDto doctoresRequestDto) {
        Doctores doctor = mapper.map(doctoresRequestDto, Doctores.class);
        Doctores doctorPersist = doctoresRepository.save(doctor);
        if (doctorPersist == null) {
            throw new InsertionDBException("No se pudo guardar el doctor " + doctoresRequestDto.getNombre() + " en la base de datos");
        }

        return mapper.map(doctorPersist, DoctoresResponseDto.class);
    }

    @Override
    public DoctoresResponseDto editDoctor(DoctoresConIdRequestDto doctoresConIdRequestDto) {
        Doctores doctor = mapper.map(doctoresConIdRequestDto, Doctores.class);
        Doctores doctorPersist = doctoresRepository.save(doctor);
        if (doctorPersist == null) {
            throw new InsertionDBException("No se puede actualizar al doctor " + doctoresConIdRequestDto.getNombre());
        }

        return mapper.map(doctorPersist, DoctoresResponseDto.class);
    }

    @Override
    public DeleteMensajeResponseDto deleteDoctor(Long id) {
        doctoresRepository.deleteById(id);
        return new DeleteMensajeResponseDto("Se eliminó correctamente al doctor solicitado");
    }


    // Extras
    @Override
    public DoctoresResponseDto getDoctorByMail(String mail) {
        Doctores doctor = doctoresRepository.findByMail(mail);
        if (doctor == null) {
            throw new EntityNotFoundException("No se encontró un doctor con mail: " + mail);
        }

        return mapper.map(doctor, DoctoresResponseDto.class);
    }

    @Override
    public List<DoctoresResponseDto> getDoctorByNombre(String nombre) {
        List<Doctores> doctores = doctoresRepository.findByNombre(nombre);
        if (doctores.isEmpty()) {
            throw new EntityNotFoundException("No se encontró un doctor con nombre: " + nombre);
        }
        List<DoctoresResponseDto> doctoresList = new ArrayList<>();
        doctoresList.stream().forEach(doctor -> doctoresList.add(mapper.map(doctor, DoctoresResponseDto.class)));
        return doctoresList;
    }

    @Override
    public DoctoresResponseDto getDoctorByIdentificacion(String identificacion) {
        Doctores doctor = doctoresRepository.findByIdentificacion(identificacion);
        if (doctor == null){
            throw new EntityNotFoundException("No hay un doctor con identificación: " + identificacion);
        }
        return mapper.map(doctor, DoctoresResponseDto.class);
    }

    @Override
    public List<DoctoresResponseDto> getDoctorByEstado(Usuarios.Estado estado) {
        List<Doctores> doctores = doctoresRepository.findByEstado(estado);
        if(doctores.isEmpty()){
            throw new EntityNotFoundException("No se encontró un doctor con estado: " + estado);
        }
        List<DoctoresResponseDto> doctoresList = new ArrayList<>();
        doctoresList.stream().forEach(doctor -> doctoresList.add(mapper.map(doctor, DoctoresResponseDto.class)));
        return doctoresList;
    }

    @Override
    public List<DoctoresResponseDto> getDoctorByEspecializacion(String especializacion) {
        List<Doctores> doctores = doctoresRepository.findByEspecializacion(especializacion);
        if(doctores.isEmpty()){
            throw new EntityNotFoundException("No se encontró un doctor con especialización: " + especializacion);
        }
        List<DoctoresResponseDto> doctoresList = new ArrayList<>();
        doctoresList.stream().forEach(doctor -> doctoresList.add(mapper.map(doctor, DoctoresResponseDto.class)));
        return doctoresList;    }

    @Override
    public List<DoctoresResponseDto> getDoctorBySueldo(Double sueldo) {
        List<Doctores> doctores = doctoresRepository.findBySueldo(sueldo);
        if(doctores.isEmpty()){
            throw new EntityNotFoundException("No se encontró un doctor con sueldo: " + sueldo);
        }
        List<DoctoresResponseDto> doctoresList = new ArrayList<>();
        doctoresList.stream().forEach(doctor -> doctoresList.add(mapper.map(doctor, DoctoresResponseDto.class)));
        return doctoresList;    }

    @Override
    public DoctoresResponseDto getDoctorByCelular(String celular) {
        Doctores doctor = doctoresRepository.findByCelular(celular);
        if (doctor == null){
            throw new EntityNotFoundException("No hay un doctor con celular: " + celular);
        }
        return mapper.map(doctor, DoctoresResponseDto.class);
    }
}
