package com.daniela.sistemaDeTurnos.service;

import com.daniela.sistemaDeTurnos.dto.request.SucursalesConIdRequestDto;
import com.daniela.sistemaDeTurnos.dto.request.SucursalesRequestDto;
import com.daniela.sistemaDeTurnos.dto.response.SucursalesResponseDto;
import com.daniela.sistemaDeTurnos.dto.response.messages.DeleteMensajeResponseDto;
import com.daniela.sistemaDeTurnos.exception.InsertionDBException;
import com.daniela.sistemaDeTurnos.model.Sucursales;
import com.daniela.sistemaDeTurnos.repository.ISucursalesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;



@Service
public class SucursalesServiceImp implements ISucursalesService{

    @Autowired
    ISucursalesRepository sucursalesRepository;

    ModelMapper mapper = new ModelMapper();


    // Métodos

    //Básicos
    @Override
    public Page<SucursalesResponseDto> getAllSucursales(int page, int size, String sortBy) {
        // Configuración de la página
        Sort sort = Sort.by(Sort.Direction.DESC, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        // Recupero de data
        Page<Sucursales> sucursalesPage = sucursalesRepository.findAll(pageable);
        if (sucursalesPage.isEmpty()){
            throw new EntityNotFoundException("No se encontraron sucursales");
        }

        return sucursalesPage.map(sucursal -> mapper.map(sucursal, SucursalesResponseDto.class));
    }

    @Override
    public SucursalesResponseDto getSucursalById(Long id) {
        Sucursales sucursales = sucursalesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró una sucursal con el ID: " + id));
        return mapper.map(sucursales, SucursalesResponseDto.class);
    }

    @Override
    public SucursalesResponseDto addSucursal(SucursalesRequestDto sucursalesRequestDto) {
        Sucursales sucursal = mapper.map(sucursalesRequestDto, Sucursales.class);
        Sucursales sucursalPersist = sucursalesRepository.save(sucursal);
        if (sucursalPersist == null){
            new InsertionDBException("No se pudo guardar la sucursal en la base de datos");
        }
        return mapper.map(sucursalPersist, SucursalesResponseDto.class);
    }

    @Override
    public SucursalesResponseDto editSucursal(SucursalesConIdRequestDto sucursalesConIdRequestDto) {
        getSucursalById(sucursalesConIdRequestDto.getId());

        Sucursales sucursal = mapper.map(sucursalesConIdRequestDto, Sucursales.class);
        Sucursales sucursalPersist = sucursalesRepository.save(sucursal);
        if (sucursalPersist == null){
            new InsertionDBException("No se pudo actualizar la sucursal " + sucursalesConIdRequestDto.getBarrio());
        }

        return mapper.map(sucursalPersist, SucursalesResponseDto.class);
    }

    @Override
    public DeleteMensajeResponseDto deleteSucursal(Long id) {
        sucursalesRepository.deleteById(id);
        return new DeleteMensajeResponseDto("Se eliminó correctamente la sucursal solicitada.");
    }

    // Extras
    @Override
    public SucursalesResponseDto getSucursalByBarrio(String barrio) {
        Sucursales sucursal = sucursalesRepository.findByBarrio(barrio);
        if (sucursal == null){
            throw new EntityNotFoundException("No se encontró una sucursal en " + barrio);
        }

        return mapper.map(sucursal, SucursalesResponseDto.class);
    }

    @Override
    public SucursalesResponseDto getSucursalByDireccion(String direccion) {
        Sucursales sucursal = sucursalesRepository.findByBarrio(direccion);
        if (sucursal == null){
            throw new EntityNotFoundException("No se encontró una sucursal en " + direccion);
        }

        return mapper.map(sucursal, SucursalesResponseDto.class);
    }
}
