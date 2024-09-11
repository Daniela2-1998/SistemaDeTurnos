package com.daniela.sistemaDeTurnos.controller;

import com.daniela.sistemaDeTurnos.dto.request.DoctoresConIdRequestDto;
import com.daniela.sistemaDeTurnos.dto.request.DoctoresRequestDto;
import com.daniela.sistemaDeTurnos.dto.response.DoctoresResponseDto;
import com.daniela.sistemaDeTurnos.dto.response.messages.ControllerResponse;
import com.daniela.sistemaDeTurnos.dto.response.messages.DeleteMensajeResponseDto;
import com.daniela.sistemaDeTurnos.model.Usuarios;
import com.daniela.sistemaDeTurnos.service.DoctoresServiceImp;
import com.daniela.sistemaDeTurnos.service.IDoctoresService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctores")
public class DoctoresController {

    @Autowired
    IDoctoresService doctoresService;

    // Constructor
    public DoctoresController(DoctoresServiceImp doctoresService) {
        this.doctoresService = doctoresService;
    }


    @CrossOrigin
    @GetMapping("")
    public ResponseEntity<Page<DoctoresResponseDto>> getAllDoctores(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return new ResponseEntity<>(doctoresService.getAllDoctores(page, size, sortBy), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctoresResponseDto> getDoctorById(@PathVariable Long id) {
        return new ResponseEntity<>(doctoresService.getDoctorById(id), HttpStatus.OK);
    }

    @PostMapping("/agregar")
    public ResponseEntity<ControllerResponse<DoctoresResponseDto>> createDoctor(@RequestBody @Valid DoctoresRequestDto doctoresRequestDto) {
        try {
            DoctoresResponseDto nuevoDoctor = doctoresService.addDoctor(doctoresRequestDto);
            ControllerResponse<DoctoresResponseDto> response = new ControllerResponse<>(true, "Doctor agregado", nuevoDoctor);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            ControllerResponse<DoctoresResponseDto> response = new ControllerResponse<>(false, "Error al agregar doctor", null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<ControllerResponse<DoctoresResponseDto>> editDoctor(@RequestBody @Valid DoctoresConIdRequestDto doctoresConIdRequestDto) {
        try {
            DoctoresResponseDto doctorActualizado = doctoresService.editDoctor(doctoresConIdRequestDto);
            ControllerResponse<DoctoresResponseDto> response = new ControllerResponse<>(true, "Doctor actualizado", doctorActualizado);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            ControllerResponse<DoctoresResponseDto> response = new ControllerResponse<>(false, "Error al actualizar doctor", null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ControllerResponse<DeleteMensajeResponseDto>> deleteDoctor(@PathVariable Long id) {
        try {
            doctoresService.deleteDoctor(id);
            ControllerResponse<DeleteMensajeResponseDto> response = new ControllerResponse<>(true, "Doctor eliminado", null);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            ControllerResponse<DeleteMensajeResponseDto> response = new ControllerResponse<>(true, "Error al eliminar doctor: " + e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Extras
    @GetMapping("/mail/{mail}")
    public ResponseEntity<DoctoresResponseDto> getDoctorByMail(@PathVariable String mail) {
        return new ResponseEntity<>(doctoresService.getDoctorByMail(mail), HttpStatus.OK);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<DoctoresResponseDto>> getDoctorByNombre(@PathVariable String nombre) {
        return new ResponseEntity<>(doctoresService.getDoctorByNombre(nombre), HttpStatus.OK);
    }

    @GetMapping("/identificacion/{identificacion}")
    public ResponseEntity<DoctoresResponseDto> getDoctorByIdentificacion(@PathVariable String identificacion) {
        return new ResponseEntity<>(doctoresService.getDoctorByIdentificacion(identificacion), HttpStatus.OK);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<DoctoresResponseDto>> getDoctorByEstado(@PathVariable Usuarios.Estado estado) {
        return new ResponseEntity<>(doctoresService.getDoctorByEstado(estado), HttpStatus.OK);
    }

    @GetMapping("/especializacion/{especializacion}")
    public ResponseEntity<List<DoctoresResponseDto>> getDoctorByEspecializacion(@PathVariable String especializacion) {
        return new ResponseEntity<>(doctoresService.getDoctorByEspecializacion(especializacion), HttpStatus.OK);
    }

    @GetMapping("/sueldo/{sueldo}")
    public ResponseEntity<List<DoctoresResponseDto>> getDoctorBySueldo(@PathVariable Double sueldo){
        return new ResponseEntity<>(doctoresService.getDoctorBySueldo(sueldo), HttpStatus.OK);
    }

    @GetMapping("/celular/{celular}")
    public ResponseEntity<DoctoresResponseDto> getDoctorByCelular(@PathVariable String celular){
        return new ResponseEntity<>(doctoresService.getDoctorByCelular(celular), HttpStatus.OK);
    }

}
