package com.daniela.sistemaDeTurnos.controller;

import com.daniela.sistemaDeTurnos.dto.request.EspecializacionesConIdRequestDto;
import com.daniela.sistemaDeTurnos.dto.request.EspecializacionesRequestDto;
import com.daniela.sistemaDeTurnos.dto.response.EspecializacionesResponseDto;
import com.daniela.sistemaDeTurnos.dto.response.messages.ControllerResponse;
import com.daniela.sistemaDeTurnos.dto.response.messages.DeleteMensajeResponseDto;
import com.daniela.sistemaDeTurnos.service.EspecializacionesServiceImp;
import com.daniela.sistemaDeTurnos.service.IEspecializacionesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/especializaciones")
public class EspecializacionController {

    @Autowired
    IEspecializacionesService especializacionesService;

    // Constructor
    public EspecializacionController(EspecializacionesServiceImp especializacionesService) {
        this.especializacionesService = especializacionesService;
    }


    @CrossOrigin
    @GetMapping("")
    public ResponseEntity<Page<EspecializacionesResponseDto>> getAllEspecializaciones(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return new ResponseEntity<>(especializacionesService.getAllEspecializaciones(page, size, sortBy), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecializacionesResponseDto> getEspecializacionById(@PathVariable Long id){
        return new ResponseEntity<>(especializacionesService.getEspecializacionById(id), HttpStatus.OK);
    }

    @PostMapping("/agregar")
    public ResponseEntity<ControllerResponse<EspecializacionesResponseDto>> createEspecializacion(
            @RequestBody @Valid EspecializacionesRequestDto especializacionesRequestDto) {
        try {
            EspecializacionesResponseDto nuevaEspecializacion = especializacionesService.addEspecializacion(especializacionesRequestDto);
            ControllerResponse<EspecializacionesResponseDto> response = new ControllerResponse<>(true, "Especialización agregada", nuevaEspecializacion);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e){
            ControllerResponse<EspecializacionesResponseDto> response = new ControllerResponse<>(false, "Error al agregar especialización", null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<ControllerResponse<EspecializacionesResponseDto>> editEspecializacion(
            @RequestBody @Valid EspecializacionesConIdRequestDto especializacionesConIdRequestDto){
        try {
            EspecializacionesResponseDto especializacionActualizada = especializacionesService.editEspecializacion(especializacionesConIdRequestDto);
            ControllerResponse<EspecializacionesResponseDto> response = new ControllerResponse<>(true, "Especialización actualizada", especializacionActualizada);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e){
            ControllerResponse<EspecializacionesResponseDto> response = new ControllerResponse<>(false, "Error al actualizar especialización", null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ControllerResponse<DeleteMensajeResponseDto>> deleteEspecializacion (@PathVariable Long id){
        try {
            especializacionesService.deleteEspecializacion(id);
            ControllerResponse<DeleteMensajeResponseDto> response = new ControllerResponse<>(true, "Especialización eliminada", null);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e){
            ControllerResponse<DeleteMensajeResponseDto> response = new ControllerResponse<>(false, "Error al eliminar la especialización", null);
            return ResponseEntity.badRequest().body(response);
        }
    }


    // Extras
    @GetMapping("/especializacion/{nombre}")
    public ResponseEntity<EspecializacionesResponseDto> getEspecializacionByNombre(@PathVariable String nombre) {
        return new ResponseEntity<>(especializacionesService.getEspecializacionByNombre(nombre), HttpStatus.OK);
    }



}
