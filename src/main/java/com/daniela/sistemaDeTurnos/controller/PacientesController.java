package com.daniela.sistemaDeTurnos.controller;

import com.daniela.sistemaDeTurnos.dto.request.PacientesConIdRequestDto;
import com.daniela.sistemaDeTurnos.dto.request.PacientesRequestDto;
import com.daniela.sistemaDeTurnos.dto.response.PacientesResponseDto;
import com.daniela.sistemaDeTurnos.dto.response.messages.ControllerResponse;
import com.daniela.sistemaDeTurnos.dto.response.messages.DeleteMensajeResponseDto;
import com.daniela.sistemaDeTurnos.model.Usuarios;
import com.daniela.sistemaDeTurnos.service.IPacientesService;
import com.daniela.sistemaDeTurnos.service.PacientesServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacientesController {

    @Autowired
    IPacientesService pacientesService;

    // Constructor
    public PacientesController(PacientesServiceImp pacientesService) {
        this.pacientesService = pacientesService;
    }


    @CrossOrigin
    @GetMapping("")
    public ResponseEntity<Page<PacientesResponseDto>> getAllPacientes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return new ResponseEntity<>(pacientesService.getAllPacientes(page, size, sortBy), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacientesResponseDto> getPacienteById(@PathVariable Long id) {
        return new ResponseEntity<>(pacientesService.getPacienteById(id), HttpStatus.OK);
    }

    @PostMapping("/agregar")
    public ResponseEntity<ControllerResponse<PacientesResponseDto>> createPaciente(@RequestBody @Valid PacientesRequestDto pacientesRequestDto) {
        try {
            PacientesResponseDto nuevoPaciente = pacientesService.addPaciente(pacientesRequestDto);
            ControllerResponse<PacientesResponseDto> response = new ControllerResponse<>(true, "Paciente agregado", nuevoPaciente);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            ControllerResponse<PacientesResponseDto> response = new ControllerResponse<>(false, "Error al agregar paciente", null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<ControllerResponse<PacientesResponseDto>> editPaciente(@RequestBody @Valid PacientesConIdRequestDto pacientesConIdRequestDto) {
        try {
            PacientesResponseDto pacienteActualizado = pacientesService.editPaciente(pacientesConIdRequestDto);
            ControllerResponse<PacientesResponseDto> response = new ControllerResponse<>(true, "Paciente actualizado", pacienteActualizado);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            ControllerResponse<PacientesResponseDto> response = new ControllerResponse<>(false, "Error al actualizar paciente", null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ControllerResponse<DeleteMensajeResponseDto>> deleteDoctor(@PathVariable Long id) {
        try {
            pacientesService.deletePaciente(id);
            ControllerResponse<DeleteMensajeResponseDto> response = new ControllerResponse<>(true, "Paciente eliminado", null);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            ControllerResponse<DeleteMensajeResponseDto> response = new ControllerResponse<>(true, "Error al eliminar paciente: " + e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


    // Extras
    @GetMapping("/mail/{mail}")
    public ResponseEntity<PacientesResponseDto> getPacienteByMail(@PathVariable String mail) {
        return new ResponseEntity<>(pacientesService.getPacienteByMail(mail), HttpStatus.OK);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<PacientesResponseDto>> getPacienteByNombre(@PathVariable String nombre) {
        return new ResponseEntity<>(pacientesService.getPacienteByNombre(nombre), HttpStatus.OK);
    }

    @GetMapping("/identificacion/{identificacion}")
    public ResponseEntity<PacientesResponseDto> getPacienteByIdentificacion(@PathVariable String identificacion) {
        return new ResponseEntity<>(pacientesService.getPacienteByIdentificacion(identificacion), HttpStatus.OK);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<PacientesResponseDto>> getPacienteByEstado(@PathVariable Usuarios.Estado estado) {
        return new ResponseEntity<>(pacientesService.getPacienteByEstado(estado), HttpStatus.OK);
    }

    @GetMapping("/celular/{celular}")
    public ResponseEntity<PacientesResponseDto> getPacienteByCelular(@PathVariable String celular) {
        return new ResponseEntity<>(pacientesService.getPacienteByCelular(celular), HttpStatus.OK);
    }


}
