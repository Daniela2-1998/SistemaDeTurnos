package com.daniela.sistemaDeTurnos.controller;


import com.daniela.sistemaDeTurnos.dto.request.TurnosConIdRequestDto;
import com.daniela.sistemaDeTurnos.dto.request.TurnosRequestDto;
import com.daniela.sistemaDeTurnos.dto.response.TurnosResponseDto;
import com.daniela.sistemaDeTurnos.dto.response.messages.ControllerResponse;
import com.daniela.sistemaDeTurnos.dto.response.messages.DeleteMensajeResponseDto;
import com.daniela.sistemaDeTurnos.model.Doctores;
import com.daniela.sistemaDeTurnos.model.Pacientes;
import com.daniela.sistemaDeTurnos.model.Sucursales;
import com.daniela.sistemaDeTurnos.model.Usuarios;
import com.daniela.sistemaDeTurnos.service.ITurnosService;
import com.daniela.sistemaDeTurnos.service.TurnosServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnosController {

    @Autowired
    ITurnosService turnosService;

    // Constructor
    public TurnosController(TurnosServiceImp turnosService) {
        this.turnosService = turnosService;
    }


    @CrossOrigin
    @GetMapping("")
    public ResponseEntity<Page<TurnosResponseDto>> getAllTurnos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return new ResponseEntity<>(turnosService.getAllTurnos(page, size, sortBy), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnosResponseDto> getTurnoById(@PathVariable Long id) {
        return new ResponseEntity<>(turnosService.getTurnosById(id), HttpStatus.OK);
    }

    @PostMapping("/agregar")
    public ResponseEntity<ControllerResponse<TurnosResponseDto>> createTurno(@RequestBody @Valid TurnosRequestDto turnosRequestDto) {
        try {
            TurnosResponseDto nuevoTurno = turnosService.addTurnos(turnosRequestDto);
            ControllerResponse<TurnosResponseDto> response = new ControllerResponse<>(true, "Turno agregado", nuevoTurno);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            ControllerResponse<TurnosResponseDto> response = new ControllerResponse<>(false, "Error al agregar turno", null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<ControllerResponse<TurnosResponseDto>> editTurno(@RequestBody @Valid TurnosConIdRequestDto turnosConIdRequestDto) {
        try {
            TurnosResponseDto turnoActualizado = turnosService.editTurnos(turnosConIdRequestDto);
            ControllerResponse<TurnosResponseDto> response = new ControllerResponse<>(true, "Turno actualizado", turnoActualizado);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            ControllerResponse<TurnosResponseDto> response = new ControllerResponse<>(false, "Error al actualizar turno", null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ControllerResponse<DeleteMensajeResponseDto>> deleteTurno(@PathVariable Long id) {
        try {
            turnosService.deleteTurnos(id);
            ControllerResponse<DeleteMensajeResponseDto> response = new ControllerResponse<>(true, "Turno eliminado", null);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            ControllerResponse<DeleteMensajeResponseDto> response = new ControllerResponse<>(true, "Error al eliminar turno: " + e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


    // Extras
    @GetMapping("/sucursal/{sucursal}")
    public ResponseEntity<List<TurnosResponseDto>> getTurnosBySucursal(@PathVariable Sucursales sucursales) {
        return new ResponseEntity<>(turnosService.getTurnosBySucursal(sucursales), HttpStatus.OK);
    }

    @GetMapping("/dia/{dia}")
    public ResponseEntity<List<TurnosResponseDto>> getTurnosByDia(@PathVariable LocalDate dia) {
        return new ResponseEntity<>(turnosService.getTurnosByDia(dia), HttpStatus.OK);
    }

    @GetMapping("/hora/{hora}")
    public ResponseEntity<List<TurnosResponseDto>> getTurnosByHora(@PathVariable String hora) {
        return new ResponseEntity<>(turnosService.getTurnosByHora(hora), HttpStatus.OK);
    }

    @GetMapping("/doctor/{doctor}")
    public ResponseEntity<List<TurnosResponseDto>> getTurnosByDoctor(@PathVariable Doctores doctor) {
        return new ResponseEntity<>(turnosService.getTurnosByDoctor(doctor), HttpStatus.OK);
    }

    @GetMapping("/paciente/{paciente}")
    public ResponseEntity<List<TurnosResponseDto>> getTurnosByPaciente(@PathVariable Pacientes paciente) {
        return new ResponseEntity<>(turnosService.getTurnosByPaciente(paciente), HttpStatus.OK);
    }

    @GetMapping("/sucursal-dia/{sucursal}/{dia}")
    public ResponseEntity<List<TurnosResponseDto>> getTurnosBySucursalAndDia(@PathVariable Sucursales sucursal, @PathVariable LocalDate dia){
        return new ResponseEntity<>(turnosService.getTurnosBySucursalAndDia(sucursal, dia), HttpStatus.OK);
    }

    @GetMapping("/dia-y-hora/{dia}/{hora}")
    public ResponseEntity<List<TurnosResponseDto>> getTurnosByDiaAndHora(@PathVariable LocalDate dia, @PathVariable String hora){
        return new ResponseEntity<>(turnosService.getTurnosByDiaAndHora(dia, hora), HttpStatus.OK);
    }

    @GetMapping("/doctor-y-paciente/{doctor}/{paciente}")
    public ResponseEntity<List<TurnosResponseDto>> getTurnosByDoctorAndPaciente(@PathVariable Doctores doctor, @PathVariable Pacientes paciente){
        return new ResponseEntity<>(turnosService.getTurnosByDoctorAndPaciente(doctor, paciente), HttpStatus.OK);
    }

    @GetMapping("/info/{doctor}/{paciente}/{dia}")
    public ResponseEntity<List<TurnosResponseDto>> getTurnosByDoctorAndPacienteAndDia(@PathVariable Doctores doctor, @PathVariable Pacientes paciente, @PathVariable LocalDate dia){
        return new ResponseEntity<>(turnosService.getTurnosByDoctorAndPacienteAndDia(doctor, paciente, dia), HttpStatus.OK);
    }
}
