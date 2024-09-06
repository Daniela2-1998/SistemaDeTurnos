package com.daniela.sistemaDeTurnos.controller;

import com.daniela.sistemaDeTurnos.dto.request.SucursalesConIdRequestDto;
import com.daniela.sistemaDeTurnos.dto.request.SucursalesRequestDto;
import com.daniela.sistemaDeTurnos.dto.response.SucursalesResponseDto;
import com.daniela.sistemaDeTurnos.dto.response.messages.ControllerResponse;
import com.daniela.sistemaDeTurnos.dto.response.messages.DeleteMensajeResponseDto;
import com.daniela.sistemaDeTurnos.service.ISucursalesService;
import com.daniela.sistemaDeTurnos.service.SucursalesServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sucursales")
public class SucursalesController {

    @Autowired
    ISucursalesService sucursalesService;

    // Constructor
    public SucursalesController(SucursalesServiceImp sucursalesService) {
        this.sucursalesService = sucursalesService;
    }


    @CrossOrigin
    @GetMapping("")
    public ResponseEntity<Page<SucursalesResponseDto>> getAllSucursales(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return new ResponseEntity<>(sucursalesService.getAllSucursales(page, size, sortBy), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SucursalesResponseDto> getSucursalById(@PathVariable Long id) {
        return new ResponseEntity<>(sucursalesService.getSucursalById(id), HttpStatus.OK);
    }

    @PostMapping("/agregar")
    public ResponseEntity<ControllerResponse<SucursalesResponseDto>> createSucursal(@RequestBody @Valid SucursalesRequestDto sucursalesRequestDto) {
        try {
            SucursalesResponseDto nuevaSucursal = sucursalesService.addSucursal(sucursalesRequestDto);
            ControllerResponse<SucursalesResponseDto> response = new ControllerResponse<>(true, "Sucursal agregada", nuevaSucursal);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            ControllerResponse<SucursalesResponseDto> response = new ControllerResponse<>(false, "Error al agregar sucursal", null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<ControllerResponse<SucursalesResponseDto>> editSucursal(@RequestBody @Valid SucursalesConIdRequestDto sucursalesConIdRequestDto) {
        try {
            SucursalesResponseDto sucursalActualizada = sucursalesService.editSucursal(sucursalesConIdRequestDto);
            ControllerResponse<SucursalesResponseDto> response = new ControllerResponse<>(true, "Sucursal actualizada", sucursalActualizada);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            ControllerResponse<SucursalesResponseDto> response = new ControllerResponse<>(false, "Error al actualizar sucursal", null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ControllerResponse<DeleteMensajeResponseDto>> deleteSucursal(@PathVariable Long id) {
        try {
            sucursalesService.deleteSucursal(id);
            ControllerResponse<DeleteMensajeResponseDto> response = new ControllerResponse<>(true, "Sucursal eliminado", null);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            ControllerResponse<DeleteMensajeResponseDto> response = new ControllerResponse<>(true, "Error al eliminar sucursal: " + e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Extras
    @GetMapping("/barrio/{barrio}")
    public ResponseEntity<SucursalesResponseDto> getSucursalByBarrio(@PathVariable String barrio) {
        return new ResponseEntity<>(sucursalesService.getSucursalByBarrio(barrio), HttpStatus.OK);
    }

    @GetMapping("/direccion/{direccion}")
    public ResponseEntity<SucursalesResponseDto> getSucursalByDireccion(@PathVariable String direccion) {
        return new ResponseEntity<>(sucursalesService.getSucursalByBarrio(direccion), HttpStatus.OK);
    }
}
