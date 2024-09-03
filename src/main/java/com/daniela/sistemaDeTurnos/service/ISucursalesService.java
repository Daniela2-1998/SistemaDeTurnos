package com.daniela.sistemaDeTurnos.service;

import com.daniela.sistemaDeTurnos.dto.request.SucursalesConIdRequestDto;
import com.daniela.sistemaDeTurnos.dto.request.SucursalesRequestDto;
import com.daniela.sistemaDeTurnos.dto.response.SucursalesResponseDto;
import com.daniela.sistemaDeTurnos.dto.response.messages.DeleteMensajeResponseDto;
import org.springframework.data.domain.Page;


public interface ISucursalesService {

    Page<SucursalesResponseDto> getAllSucursales(int page, int size, String sortBy);

    SucursalesResponseDto getSucursalById(Long id);

    SucursalesResponseDto addSucursal(SucursalesRequestDto sucursalesRequestDto);

    SucursalesResponseDto editSucursal(SucursalesConIdRequestDto sucursalesConIdRequestDto);

    DeleteMensajeResponseDto deleteSucursal(Long id);


    // Funciones extras
    SucursalesResponseDto getSucursalByBarrio(String barrio);

    SucursalesResponseDto getSucursalByDireccion(String direccion);

}
