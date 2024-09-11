package com.daniela.sistemaDeTurnos.service;

import com.daniela.sistemaDeTurnos.dto.request.AuthConIdRequestDto;
import com.daniela.sistemaDeTurnos.dto.request.AuthRequestDto;
import com.daniela.sistemaDeTurnos.dto.response.AuthResponseDto;
import com.daniela.sistemaDeTurnos.dto.response.messages.DeleteMensajeResponseDto;
import com.daniela.sistemaDeTurnos.exception.InsertionDBException;
import com.daniela.sistemaDeTurnos.model.Auth;
import com.daniela.sistemaDeTurnos.repository.IAuthRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImp implements IAuthService{

    @Autowired
    IAuthRepository authRepository;

    ModelMapper mapper = new ModelMapper();



    // Métodos

    //Básicos
    @Override
    public AuthResponseDto getAuthById(Long id) {
        Auth auth = authRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró un usuario con el ID: " + id));
        return mapper.map(auth, AuthResponseDto.class);
    }

    @Override
    public AuthResponseDto addAuth(AuthRequestDto authRequestDto) {
        Auth auth = mapper.map(authRequestDto, Auth.class);
        Auth authPersist = authRepository.save(auth);
        if (authPersist == null){
            throw new InsertionDBException("No se pudo guardar el usuario");
        }
        return mapper.map(authPersist, AuthResponseDto.class);
    }

    @Override
    public AuthResponseDto editAuth(AuthConIdRequestDto authConIdRequestDto) {
        Optional<Auth> authRecuperado = authRepository.findById(authConIdRequestDto.getId());
        if(authRecuperado == null){
            return null;
        } else {
            Auth auth = mapper.map(authConIdRequestDto, Auth.class);
            Auth authPersist = authRepository.save(auth);
            if (authPersist == null) {
                throw new InsertionDBException("No se pudo actualizar el usuario.");
            }

            return mapper.map(authPersist, AuthResponseDto.class);
        }    }

    @Override
    public DeleteMensajeResponseDto deleteAuth(Long id) {
        authRepository.deleteById(id);
        return new DeleteMensajeResponseDto("Se eliminó correctamente al usuario solicitado.");    }


    // Extras
    @Override
    public AuthResponseDto getAuthByMail(String mail) {
        Auth auth = authRepository.findByMail(mail);
        return mapper.map(auth, AuthResponseDto.class);
    }

    @Override
    public AuthResponseDto login(String mail, String contraseña) {
        Auth auth = authRepository.findByMailAndContraseña(mail, contraseña);
        return mapper.map(auth, AuthResponseDto.class);
    }

    @Override
    public List<AuthResponseDto> getAuthByTipoUsuario(String tipoUsuario) {
        List<Auth> usuarios = authRepository.findByTipoUsuario(tipoUsuario);
        return usuarios.stream()
                .map(usuario -> mapper.map(usuario, AuthResponseDto.class))
                .toList();
    }
}
