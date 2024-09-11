package com.daniela.sistemaDeTurnos.controller;

import com.daniela.sistemaDeTurnos.dto.request.AuthConIdRequestDto;
import com.daniela.sistemaDeTurnos.dto.request.AuthRequestDto;
import com.daniela.sistemaDeTurnos.dto.response.AuthResponseDto;
import com.daniela.sistemaDeTurnos.dto.response.messages.ControllerResponse;
import com.daniela.sistemaDeTurnos.dto.response.messages.DeleteMensajeResponseDto;
import com.daniela.sistemaDeTurnos.exception.EntityNotFoundException;
import com.daniela.sistemaDeTurnos.service.AuthServiceImp;
import com.daniela.sistemaDeTurnos.service.IAuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    IAuthService authService;

    // Constructor
    public AuthController(AuthServiceImp authService) {
        this.authService = authService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthResponseDto> getAuthById(@PathVariable Long id){
        return new ResponseEntity<>(authService.getAuthById(id), HttpStatus.OK);
    }

    @PostMapping("/agregar")
    public ResponseEntity<ControllerResponse<AuthResponseDto>> createAuth
            (@RequestBody @Valid AuthRequestDto authRequestDto){
        try {
            AuthResponseDto nuevoUsuario = authService.addAuth(authRequestDto);
            ControllerResponse<AuthResponseDto> response = new ControllerResponse<>(true,
                    "Usuario agregado", nuevoUsuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            ControllerResponse<AuthResponseDto> response = new ControllerResponse<>(false, "Error al " +
                    "crear al usuario: " + e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<ControllerResponse<AuthResponseDto>> updateAuth(
            @RequestBody @Valid AuthConIdRequestDto authConIdRequestDto
    ){
        try {
            AuthResponseDto usuarioActualizado = authService.editAuth(authConIdRequestDto);
            ControllerResponse<AuthResponseDto> response = new ControllerResponse<>(true,
                    "Usuario actualizado", usuarioActualizado);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            ControllerResponse<AuthResponseDto> response = new ControllerResponse<>(false,
                    "No se pudo actualizar al usuario: " + e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ControllerResponse<DeleteMensajeResponseDto>> deleteAuth(@PathVariable Long id){
        try {
            authService.deleteAuth(id);
            ControllerResponse<DeleteMensajeResponseDto> response = new ControllerResponse<>(true, "Usuario eliminado", null);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            ControllerResponse<DeleteMensajeResponseDto> response = new ControllerResponse<>(false,
                    "No se pudo eliminar al usuario: " + e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


    // Extras
    @GetMapping("/mail/{mail}")
    public ResponseEntity<AuthResponseDto> getAuthByMail(@PathVariable String mail) {
        return new ResponseEntity<>(authService.getAuthByMail(mail), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto authRequestDto) {
        AuthResponseDto authResponse = authService.login(authRequestDto.getMail(), authRequestDto.getContraseña());
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<AuthResponseDto>> getAuthByTipoUsuario(@PathVariable String tipoUsuario) {
        return new ResponseEntity<>(authService.getAuthByTipoUsuario(tipoUsuario), HttpStatus.OK);
    }

}
