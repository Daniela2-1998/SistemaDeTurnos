package com.daniela.sistemaDeTurnos.controller;


import com.daniela.sistemaDeTurnos.dto.request.AdministrativosConIdRequestDto;
import com.daniela.sistemaDeTurnos.dto.request.AdministrativosRequestDto;
import com.daniela.sistemaDeTurnos.dto.response.AdministrativosResponseDto;
import com.daniela.sistemaDeTurnos.dto.response.messages.ControllerResponse;
import com.daniela.sistemaDeTurnos.dto.response.messages.DeleteMensajeResponseDto;
import com.daniela.sistemaDeTurnos.model.Usuarios;
import com.daniela.sistemaDeTurnos.service.AdministrativosServiceImp;
import com.daniela.sistemaDeTurnos.service.IAdministrativosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administrativos")
public class AdministrativosController {

    @Autowired
    IAdministrativosService administrativosService;

    // Constructor
    public AdministrativosController(AdministrativosServiceImp administrativosService) {
        this.administrativosService = administrativosService;
    }


    @CrossOrigin
    @GetMapping("")
    public ResponseEntity<Page<AdministrativosResponseDto>> getAllAdministrativos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ){
        return new ResponseEntity<>(administrativosService.getAllAdministrativos(page, size, sortBy), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministrativosResponseDto> getAdministrativoById(@PathVariable Long id){
        return new ResponseEntity<>(administrativosService.getAdministrativoById(id), HttpStatus.OK);
    }

    @PostMapping("/agregar")
    public ResponseEntity<ControllerResponse<AdministrativosResponseDto>> createAdministrativo
            (@RequestBody @Valid AdministrativosRequestDto administrativosRequestDto){
        try {
            AdministrativosResponseDto nuevoAdministrativo = administrativosService.addAdministrativo(administrativosRequestDto);
            ControllerResponse<AdministrativosResponseDto> response = new ControllerResponse<>(true,
                    "Administrativo agregado", nuevoAdministrativo);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            ControllerResponse<AdministrativosResponseDto> response = new ControllerResponse<>(false, "Error al " +
                    "crear al administrativo: " + e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<ControllerResponse<AdministrativosResponseDto>> updateAdministrativo(
            @RequestBody @Valid AdministrativosConIdRequestDto administrativosConIdRequestDto
            ){
        try {
            AdministrativosResponseDto administrativoActualizado = administrativosService.editAdministrativo(administrativosConIdRequestDto);
            ControllerResponse<AdministrativosResponseDto> response = new ControllerResponse<>(true,
                    "Administrador actualizado", administrativoActualizado);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            ControllerResponse<AdministrativosResponseDto> response = new ControllerResponse<>(false,
                    "No se pudo actualizar al administrador: " + e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ControllerResponse<DeleteMensajeResponseDto>> deleteAdministrativo(@PathVariable Long id){
        try {
            administrativosService.deleteAdministrativo(id);
            ControllerResponse<DeleteMensajeResponseDto> response = new ControllerResponse<>(true, "Administrativo eliminado", null);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            ControllerResponse<DeleteMensajeResponseDto> response = new ControllerResponse<>(false,
                    "No se pudo eliminar al administrativo: " + e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


    // Extras
    @GetMapping("/mail/{mail}")
    public ResponseEntity<AdministrativosResponseDto> getAdministrativoByMail(@PathVariable String mail) {
        return new ResponseEntity<>(administrativosService.getAdministrativoByMail(mail), HttpStatus.OK);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<AdministrativosResponseDto>> getAdministrativoByNombre(@PathVariable String nombre) {
        return new ResponseEntity<>(administrativosService.getAdministrativoByNombre(nombre), HttpStatus.OK);
    }

    @GetMapping("/identificacion/{identificacion}")
    public ResponseEntity<AdministrativosResponseDto> getAdministrativoByIdentificacion(@PathVariable String identificacion) {
        return new ResponseEntity<>(administrativosService.getAdministrativoByIdentificacion(identificacion), HttpStatus.OK);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<AdministrativosResponseDto>> getAdministrativoByEstado(@PathVariable Usuarios.Estado estado) {
        return new ResponseEntity<>(administrativosService.getAdministrativoByEstado(estado), HttpStatus.OK);
    }

    @GetMapping("/cargo/{cargo}")
    public ResponseEntity<List<AdministrativosResponseDto>> getAdministrativoByCargo(@PathVariable String cargo) {
        return new ResponseEntity<>(administrativosService.getAdministrativoByCargo(cargo), HttpStatus.OK);
    }

    @GetMapping("/horario/{horario}")
    public ResponseEntity<List<AdministrativosResponseDto>> getAdministrativoByHorario(@PathVariable String horario) {
        return new ResponseEntity<>(administrativosService.getAdministrativoByHorario(horario), HttpStatus.OK);
    }

    @GetMapping("/sueldo/{sueldo}")
    public ResponseEntity<List<AdministrativosResponseDto>> getAdministrativoBySueldo(@PathVariable Double sueldo) {
        return new ResponseEntity<>(administrativosService.getAdministrativoBySueldo(sueldo), HttpStatus.OK);
    }

}
