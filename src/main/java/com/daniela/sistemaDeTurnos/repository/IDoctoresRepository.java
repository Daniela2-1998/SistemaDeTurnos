package com.daniela.sistemaDeTurnos.repository;

import com.daniela.sistemaDeTurnos.model.Doctores;
import com.daniela.sistemaDeTurnos.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDoctoresRepository extends JpaRepository<Doctores, Long>{

    List<Doctores> findByMail(String mail);

    List<Doctores> findByNombre(String nombre);

    List<Doctores> findByTipo(String tipo);

    List<Doctores> findByIdentificacion(String identificacion);

    List<Doctores> findByEstado(Usuarios.Estado estado);


    // Específicos
    List<Doctores> findByEspecializacion(String especializacion);

    List<Doctores> findBySueldo(Double sueldo);

    List<Doctores> findByCelular(String celular);

}
