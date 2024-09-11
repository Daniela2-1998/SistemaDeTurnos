package com.daniela.sistemaDeTurnos.repository;

import com.daniela.sistemaDeTurnos.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAuthRepository extends JpaRepository<Auth, Long> {

    Auth findByMail(String mail);

    Auth findByMailAndContraseña(String nombre, String contraseña);

    List<Auth> findByTipoUsuario(String tipoUsuario);

}
