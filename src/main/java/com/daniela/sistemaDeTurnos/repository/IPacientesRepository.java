package com.daniela.sistemaDeTurnos.repository;

import com.daniela.sistemaDeTurnos.model.Pacientes;
import com.daniela.sistemaDeTurnos.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPacientesRepository extends JpaRepository<Pacientes, Long> {

    Pacientes findByMail(String mail);

    List<Pacientes> findByNombre(String nombre);

    List<Pacientes> findByTipo(String tipo);

    Pacientes findByIdentificacion(String identificacion);

    List<Pacientes> findByEstado(Usuarios.Estado estado);


    // Específicos
    Pacientes findByCelular(String celular);

}
